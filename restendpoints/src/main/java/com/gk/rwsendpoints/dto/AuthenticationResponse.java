package com.gk.rwsendpoints.dto;

import java.util.Date;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public class AuthenticationResponse {

    private Date timesStamp;
    private boolean userAuthenticated;
    private String reason;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
        this.timesStamp = new Date();
    }

    public AuthenticationResponse(boolean userAuthenticated, String reason) {
        this.userAuthenticated = userAuthenticated;
        this.reason = reason;
        this.timesStamp = new Date();
    }

    public Date getTimesStamp() {
        return timesStamp;
    }

    public void setTimesStamp(Date timesStamp) {
        this.timesStamp = timesStamp;
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

    public AuthenticationResponse withTimesStamp(Date timesStamp) {
        this.timesStamp = timesStamp;
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
