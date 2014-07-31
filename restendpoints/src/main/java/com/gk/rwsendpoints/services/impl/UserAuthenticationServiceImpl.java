package com.gk.rwsendpoints.services.impl;

import com.gk.rwsendpoints.dto.UserInfo;
import com.gk.rwsendpoints.services.api.UserAuthenticationServiceAPI;
import com.gk.rwsendpoints.services.api.UserInfoAccessServiceAPI;
import com.gk.rwsendpoints.services.api.UserPasswordVerificationServiceAPI;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by greg korenevsky on 7/28/14.
 */
@Named
public class UserAuthenticationServiceImpl implements UserAuthenticationServiceAPI {

    //    private UserAuthenticationAccessServiceAPI userAuthenticationAccessService;
    private static final String DEFAULT_USER_ID_ATTRIBUTE_NAME = "userId";

    private String userIdAttributeName = DEFAULT_USER_ID_ATTRIBUTE_NAME;
    private UserInfoAccessServiceAPI userInfoAccessService;
    private UserPasswordVerificationServiceAPI userPasswordVerificationService;

    public String getUserIdAttributeName() {
        return userIdAttributeName;
    }

    public void setUserIdAttributeName(String userIdAttributeName) {
        this.userIdAttributeName = userIdAttributeName;
    }

    public UserPasswordVerificationServiceAPI getUserPasswordVerificationService() {
        return userPasswordVerificationService;
    }

    @Inject
    public void setUserPasswordVerificationService(UserPasswordVerificationServiceAPI userPasswordVerificationService) {
        this.userPasswordVerificationService = userPasswordVerificationService;
    }

    public UserInfoAccessServiceAPI getUserInfoAccessService() {
        return userInfoAccessService;
    }

    @Inject
    public void setUserInfoAccessService(UserInfoAccessServiceAPI userInfoAccessService) {
        this.userInfoAccessService = userInfoAccessService;
    }

    @Override
    public Pair<Boolean, String> authenticateUser(String userId, String password) {
//        return Pair.of(true, null);

        List<UserInfo> userList = userInfoAccessService.getUsers(DEFAULT_USER_ID_ATTRIBUTE_NAME, userId);

        if (userList.size() == 1) {
            UserInfo userInfo = userList.get(0);

            if (userPasswordVerificationService.matchingPassword(password, userInfo.getEncryptedPassword())) {
                return Pair.of(true, null);
            } else {
                return Pair.of(false, "Password mismatch");
            }
        } else if (userList.isEmpty()) {
            return Pair.of(false, "User not found with matching userId");
        } else {
            return Pair.of(false, "Multiple users found with the same userId");
        }
    }
}
