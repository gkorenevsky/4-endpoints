package com.gk.rwsendpoints.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by greg korenevsky on 7/29/14.
 */
public class UsersListResponse extends PagedResponse {

    private Date timeStamp;
    private int userCount;
    private List<UserInfo> usersList;

    public List<UserInfo> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserInfo> usersList) {
        this.usersList = usersList;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public UsersListResponse withTimeStamp(Date timesStamp) {
        this.timeStamp = timesStamp;
        return this;
    }

    public UsersListResponse withUserCount(int userCount) {
        this.userCount = userCount;
        return this;
    }

    public UsersListResponse withUsersList(List<UserInfo> usersList) {
        this.usersList = usersList;
        return this;
    }

}
