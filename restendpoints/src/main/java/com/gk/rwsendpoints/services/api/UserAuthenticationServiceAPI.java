package com.gk.rwsendpoints.services.api;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public interface UserAuthenticationServiceAPI {
    public Pair<Boolean, String> authenticateUser(String userId, String password);
}
