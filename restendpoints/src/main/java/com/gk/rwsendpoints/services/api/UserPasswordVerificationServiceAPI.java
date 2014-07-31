package com.gk.rwsendpoints.services.api;

/**
 * Created by greg korenevsky on 7/29/14.
 */
public interface UserPasswordVerificationServiceAPI {

    public boolean matchingPassword( String attemptedPassword, String storedPassword);
}
