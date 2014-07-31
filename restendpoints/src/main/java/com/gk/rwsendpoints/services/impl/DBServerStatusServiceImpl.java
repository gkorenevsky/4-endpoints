package com.gk.rwsendpoints.services.impl;

import com.gk.rwsendpoints.dao.api.DBServerStatusDAOAPI;
import com.gk.rwsendpoints.dao.impl.DBServerStatusDAOImpl;
import com.gk.rwsendpoints.dto.ResourceStatus;
import com.gk.rwsendpoints.services.api.ResourceStatusServiceAPI;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by greg korenevsky on 7/30/14.
 */
@Named
public class DBServerStatusServiceImpl implements ResourceStatusServiceAPI {

    private DBServerStatusDAOAPI dbServerStatusDAO;
    private String dataSourceName;
    private String resourceDescription;

    public String getDataSourceName() {
        return dataSourceName;
    }

    @Value(value = "${hibernate.data_source}")
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    @Value(value = "${db_server.description}")
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public DBServerStatusDAOAPI getDbServerStatusDAO() {
        return dbServerStatusDAO;
    }

    @Inject
    public void setDbServerStatusDAO(DBServerStatusDAOAPI dbServerStatusDAO) {
        this.dbServerStatusDAO = dbServerStatusDAO;
    }

    @Override
    public ResourceStatus getResourceStatus() {

        ResourceStatus result = new ResourceStatus()
                                .withResourceName(dataSourceName)
                                .withDescription(resourceDescription)
                                .withTimeStamp(new Date())
                                ;

        try {
            DBServerStatusDAOAPI.Status status = dbServerStatusDAO.getDBServerStatus();

            switch (status) {
                case OK: result.setStatus(ResourceStatus.Status.OK); break;
                case UNAVAILABLE: result.setStatus(ResourceStatus.Status.UNAVAILABLE); break;
                default: result.setStatus(ResourceStatus.Status.UNAVAILABLE); break;
            }
        } catch (Exception ex) {
            result.setStatus(ResourceStatus.Status.ERROR);
            result.setDiagnosticMessage(ex.getMessage());
        }

        return result;
    }
}
