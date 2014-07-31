package com.gk.rwsendpoints.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by greg korenevsky on 7/29/14.
 */
public class UsersListResponse {

    private Date timesStamp;
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

    public Date getTimesStamp() {
        return timesStamp;
    }

    public void setTimesStamp(Date timesStamp) {
        this.timesStamp = timesStamp;
    }

    public UsersListResponse withTimesStamp(Date timesStamp) {
        this.timesStamp = timesStamp;
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
