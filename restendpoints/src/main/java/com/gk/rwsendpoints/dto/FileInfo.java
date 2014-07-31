package com.gk.rwsendpoints.dto;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public class FileInfo {

    public enum Type {
        DIRECTORY
        , FILE
        , LINK
    }

    private String absolutePath;
    private String type;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public FileInfo withAbsolutePath(String absolutePath) {
        setAbsolutePath(absolutePath);
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type.toString();
    }

    public FileInfo withType(Type type) {
        setType(type);
        return this;
    }
}
