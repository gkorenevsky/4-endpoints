package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.FileInfo;
import com.gk.rwsendpoints.dto.FileListResponse;
import com.gk.rwsendpoints.services.api.FileListServiceAPI;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by greg korenevsky on 7/30/14.
 */
@Path("/rest/v1/fileList")
@Named
public class FileListController {

    private FileListServiceAPI fileListService;

    public FileListServiceAPI getFileListService() {
        return fileListService;
    }

    @Inject
    public void setFileListService(FileListServiceAPI fileListService) {
        this.fileListService = fileListService;
    }

    @GET
    @Produces("application/json")
    public Response getFileList(@QueryParam("directoryPath") String directoryPath) {

        FileListResponse payload = new FileListResponse();
        List<FileInfo> fileInfoList = null;

        try {
            Pair<File, List<File>> fileList = fileListService.getFileList(directoryPath);

            payload.setDirectory(generateFileInfo(fileList.getLeft()));

            fileInfoList = new ArrayList<FileInfo>(fileList.getRight().size());

            for (File nextFile : fileList.getRight()) {
                fileInfoList.add(generateFileInfo(nextFile));
            }

            payload.setSuccess(true);
            payload.setFileCount(fileInfoList.size());
        } catch (Exception ex) {
            payload.setSuccess(false);
            payload.setDiagnosticMessage(ex.getMessage());
            payload.setFileCount(0);
            payload.setDirectory(null);
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
}
