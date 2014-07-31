package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.UserInfo;
import com.gk.rwsendpoints.dto.UsersListResponse;
import com.gk.rwsendpoints.services.api.UserInfoAccessServiceAPI;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by greg korenevsky on 7/28/14.
 */
@Named
@Path("/rest/v1/getUsers")
public class UsersListController {

    private UserInfoAccessServiceAPI usersListService;

    public UserInfoAccessServiceAPI getUsersListService() {
        return usersListService;
    }

    @Inject
    public void setUsersListService(UserInfoAccessServiceAPI usersListService) {
        this.usersListService = usersListService;
    }

    @GET
    @Produces("application/json")
    public Response getUsersList(
            @QueryParam("attributeName") String attributeName,
            @QueryParam("attributeValue") String attributeValue
    ) {
        boolean error = false;
        List<UserInfo> users = null;

        if (!isBlank(attributeName) && !isBlank(attributeValue)) {
            users = usersListService.getUsers(attributeName, attributeValue);
        } else if (isBlank(attributeName) && isBlank(attributeValue)) {
            users = usersListService.getAllUsers();
        } else {
            error = true;
        }

        if (error) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            UsersListResponse usersListResponse = new UsersListResponse()
                    .withUsersList(users)
                    .withUserCount(users.size())
                    .withTimesStamp(new Date());

            return Response.ok().entity(usersListResponse).build();
        }
    }
}
