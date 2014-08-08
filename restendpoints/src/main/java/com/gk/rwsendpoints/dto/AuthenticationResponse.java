package com.gk.rwsendpoints.dto;

import java.util.Date;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public class AuthenticationResponse {

    private Date timeStamp;
    private boolean userAuthenticated;
    private String reason;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
        this.timeStamp = new Date();
    }

    public AuthenticationResponse(boolean userAuthenticated, String reason) {
        this.userAuthenticated = userAuthenticated;
        this.reason = reason;
        this.timeStamp = new Date();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AuthenticationResponse withTimeStamp(Date timesStamp) {
        this.timeStamp = timesStamp;
        return this;
    }

    public AuthenticationResponse withUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
        return this;
    }

    public AuthenticationResponse withReason(String reason) {
        this.reason = reason;
        return this;
    }

}
