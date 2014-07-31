package com.gk.rwsendpoints.dao.impl;

import com.gk.rwsendpoints.dao.api.DBServerStatusDAOAPI;
import com.gk.rwsendpoints.entities.UserInfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by greg korenevsky on 7/30/14.
 */
@Named
public class DBServerStatusDAOImpl extends GenericHibernateDAO<Date>
        implements DBServerStatusDAOAPI {

    private String dbServerStatusMonitoringQuery;

    public String getDbServerStatusMonitoringQuery() {
        return dbServerStatusMonitoringQuery;
    }

    @Value(value = "${db_server.status_monitoring_query}")
    public void setDbServerStatusMonitoringQuery(String dbServerStatusMonitoringQuery) {
        this.dbServerStatusMonitoringQuery = dbServerStatusMonitoringQuery;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public Status getDBServerStatus() {

        SQLQuery query = createSQLQuery(dbServerStatusMonitoringQuery);

        try {
            List<Object[]> resultSet = query.list();
            if (resultSet.size() == 1) {
                return Status.OK;
            } else {
                return Status.UNAVAILABLE;
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Error while obtaining db server status: " + e.getMessage(), e);
        }
    }
}
