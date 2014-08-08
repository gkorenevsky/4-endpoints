package com.gk.rwsendpoints.dto;

/**
 * Created by engineer on 7/31/14.
 */
public abstract class PagedResponse {

    protected int pageNumber;
    protected int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
