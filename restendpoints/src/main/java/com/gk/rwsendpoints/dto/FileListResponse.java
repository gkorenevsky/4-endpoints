package com.gk.rwsendpoints.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public class FileListResponse extends PagedResponse {

    private Date timeStamp;
    private boolean success;
    private FileInfo directory;
    private int fileCount;

    private List<FileInfo> directoryContents;
    private String diagnosticMessage;

    public String getDiagnosticMessage() {
        return diagnosticMessage;
    }

    public void setDiagnosticMessage(String diagnosticMessage) {
        this.diagnosticMessage = diagnosticMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public FileInfo getDirectory() {
        return directory;
    }

    public void setDirectory(FileInfo directory) {
        this.directory = directory;
    }

    public List<FileInfo> getDirectoryContents() {
        return directoryContents;
    }

    public void setDirectoryContents(List<FileInfo> directoryContents) {
        this.directoryContents = directoryContents;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

}
