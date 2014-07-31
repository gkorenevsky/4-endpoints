package com.gk.rwsendpoints.services.api;

import com.gk.rwsendpoints.dto.UserInfo;

import java.util.List;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public interface UserInfoAccessServiceAPI {

    public List<UserInfo> getUsers(String attributeName, String attributeValue);
    public List<UserInfo> getAllUsers();
//    public List<UserInfo> getUsers(String attributeName, String attributeValue, String groupByAttributeName);
}
