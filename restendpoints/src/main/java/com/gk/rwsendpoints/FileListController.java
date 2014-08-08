package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.FileInfo;
import com.gk.rwsendpoints.dto.FileListResponse;
import com.gk.rwsendpoints.services.api.FileListServiceAPI;
import com.gk.rwsendpoints.util.impl.PagerImpl;
import com.wordnik.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by greg korenevsky on 7/30/14.
 */
@Named
@Path("/rest/v1/files")
@Api(value = "/files", description = "Obtain a list of files")
public class FileListController {

    private FileListServiceAPI fileListService;

    public FileListServiceAPI getFileListService() {
        return fileListService;
    }
    @Inject
    public void setFileListService(FileListServiceAPI fileListService) {
        this.fileListService = fileListService;
    }

    private int defaultPageSize;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    @Value(value = "${rws.endpoint.default.pageSize}")
    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    private static final PagerImpl<File> filePager = new PagerImpl<File>();

    @GET
    @Produces("application/json")
    @ApiOperation(value = "Obtain a list of files in a given directory", response = FileListResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid input") })
    public Response getFileList(
            @ApiParam(value = "absolute path to the directory whose contents need to be listed", required = true)
            @QueryParam("directoryPath") String directoryPath
            ,@ApiParam(value = "page number used to retrieve result as a series of pages", required = false)
             @DefaultValue("0") @QueryParam("page")
             int pageNumber
            ,@ApiParam(value = "page size, i.e. number or entries in a result page", required = false)
             @DefaultValue("0") @QueryParam("pageSize")
             int pageSize
    ) {

        boolean pagingRequested = false;

        if (isBlank(directoryPath)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (!validPaging(pageNumber, pageSize)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (pageNumber > 0) {
            pagingRequested = true;

            if (pageSize == 0) {
               pageSize = defaultPageSize;
            }
        }

        FileListResponse payload = new FileListResponse();
        List<FileInfo> fileInfoList = null;

        try {
            Pair<File, List<File>> directoryInfo = fileListService.getFileList(directoryPath);

            payload.setDirectory(generateFileInfo(directoryInfo.getLeft()));

            List<File> directoryFiles = directoryInfo.getRight();

            // Get a page
            if (pagingRequested) {
                directoryFiles = filePager.getPage(directoryFiles, pageNumber, pageSize);
            }

            fileInfoList = new ArrayList<FileInfo>(directoryFiles.size());

            for (File nextFile : directoryFiles) {
                fileInfoList.add(generateFileInfo(nextFile));
            }

            payload.setSuccess(true);
            payload.setFileCount(fileInfoList.size());
            payload.setPageNumber(0);
            payload.setPageSize(0);

            if (pagingRequested) {
                payload.setPageNumber(pageNumber);
                payload.setPageSize(pageSize);
            }
        } catch (Exception ex) {
            payload.setSuccess(false);
            payload.setDiagnosticMessage(ex.getMessage());
            payload.setFileCount(0);
            payload.setDirectory(null);
            payload.setPageNumber(0);
            payload.setPageSize(0);
        }

        payload.setTimeStamp(new Date());
        payload.setDirectoryContents(fileInfoList);
        return Response.ok().entity(payload).build();
    }

    private FileInfo generateFileInfo(File file) {

        FileInfo result = new FileInfo().withAbsolutePath(file.getAbsolutePath());

        if (file.isDirectory()) {
            try {
                if (FileUtils.isSymlink(file)) {
                    result.setType(FileInfo.Type.LINK);
                } else {
                    result.setType(FileInfo.Type.DIRECTORY);
                }
            } catch (IOException e) {
                result.setType(FileInfo.Type.DIRECTORY);
            }
        } else {
            result.setType(FileInfo.Type.FILE);
        }

        return result;
    }

    private boolean validPaging(int pageNumber, int pageSize) {

        if (pageNumber < 0 || pageSize < 0) {
            return false;
        }

        if (pageNumber == 0 && pageSize != 0) {
            return false;
        }

        return true;
    }
}
