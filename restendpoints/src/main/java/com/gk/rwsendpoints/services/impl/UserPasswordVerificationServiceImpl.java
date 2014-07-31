package com.gk.rwsendpoints.services.impl;

import com.gk.rwsendpoints.services.api.UserPasswordVerificationServiceAPI;

import javax.inject.Named;

/**
 * Created by greg korenevsky on 7/29/14.
 */
@Named
public class UserPasswordVerificationServiceImpl implements UserPasswordVerificationServiceAPI {

    @Override
    public boolean matchingPassword(String attemptedPassword, String storedPassword) {

        return storedPassword.equals(attemptedPassword);
    }
}
