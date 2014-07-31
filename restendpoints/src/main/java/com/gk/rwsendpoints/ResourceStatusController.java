package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.ResourceStatus;
import com.gk.rwsendpoints.services.api.ResourceStatusServiceAPI;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg korenevsky on 8/2/14.
 */
@Named
@Path("/rest/v1/resourceStatus")
public class ResourceStatusController {

    List<ResourceStatusServiceAPI> resourceMonitorList;

    public List<ResourceStatusServiceAPI> getResourceMonitorList() {
        return resourceMonitorList;
    }

    @Inject
    public void setResourceMonitorList(List<ResourceStatusServiceAPI> resourceMonitorList) {
        this.resourceMonitorList = resourceMonitorList;
    }

    @GET
    @Produces("application/json")
    public Response getResourceStatuses() {

        List<ResourceStatus> resourceStatuses = new ArrayList<ResourceStatus>();

        for (ResourceStatusServiceAPI resourceMonitor : resourceMonitorList) {
            ResourceStatus nextStatus = resourceMonitor.getResourceStatus();
            resourceStatuses.add(nextStatus);
        }

        return  Response.ok().entity(resourceStatuses).build();
    }
}
