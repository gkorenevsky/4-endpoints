package com.gk.rwsendpoints.dao.api;

import java.util.Date;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public interface DBServerStatusDAOAPI extends GenericDAOAPI<Date>  {

    public enum Status {
        OK,
        UNAVAILABLE,
        ERROR
    }

    public Status getDBServerStatus();
}
