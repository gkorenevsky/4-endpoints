package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.UserInfo;
import com.gk.rwsendpoints.dto.UsersListResponse;
import com.gk.rwsendpoints.services.api.UserInfoAccessServiceAPI;
import com.gk.rwsendpoints.util.impl.PagerImpl;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by greg korenevsky on 7/28/14.
 */
@Named
@Path("/rest/v1/users")
@Api(value = "/users", description = "Obtain a list of users")
public class UsersListController {

    private UserInfoAccessServiceAPI usersListService;

    public UserInfoAccessServiceAPI getUsersListService() {
        return usersListService;
    }

    @Inject
    public void setUsersListService(UserInfoAccessServiceAPI usersListService) {
        this.usersListService = usersListService;
    }

    private int defaultPageSize;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    @Value(value = "${rws.endpoint.default.pageSize}")
    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    private static final PagerImpl<UserInfo> userPager = new PagerImpl<UserInfo>();

    @GET
    @Produces("application/json")
    @ApiOperation(value = "Obtain a list of users, optionally filtered by specific attribute value", response = UsersListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid input")})
    public Response getUsersList(
            @ApiParam(value = "Attribute name to be used in filtering user list. If omitted, the entire list of users is returned", required = false)
            @QueryParam("attributeName")
            String attributeName
            , @ApiParam(value = "Attribute value to be used in filtering user list. Must be present if attribute name is used"
            , required = false)
            @QueryParam("attributeValue")
            String attributeValue
            , @ApiParam(value = "page number used to retrieve result as a series of pages", required = false)
            @DefaultValue("0") @QueryParam("page")
            int pageNumber
            , @ApiParam(value = "page size, i.e. number or entries in a result page", required = false)
            @DefaultValue("0") @QueryParam("pageSize")
            int pageSize
    ) {
        boolean pagingRequested = false;
        boolean error = false;
        List<UserInfo> users = null;

        if (!validPaging(pageNumber, pageSize)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (pageNumber > 0) {
            pagingRequested = true;

            if (pageSize == 0) {
                pageSize = defaultPageSize;
            }
        }

        if (!isBlank(attributeName) && !isBlank(attributeValue)) {
            users = usersListService.getUsers(attributeName, attributeValue);
        } else if (isBlank(attributeName) && isBlank(attributeValue)) {
            users = usersListService.getAllUsers();
        } else {
            error = true;
        }

        // Get a page
        if (pagingRequested) {
            users = userPager.getPage(users, pageNumber, pageSize);
        }

        if (error) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        UsersListResponse usersListResponse = new UsersListResponse()
                .withUsersList(users)
                .withUserCount(users.size())
                .withTimeStamp(new Date());

        usersListResponse.setPageNumber(0);
        usersListResponse.setPageSize(0);

        if (pagingRequested) {
            usersListResponse.setPageSize(pageSize);
            usersListResponse.setPageNumber(pageNumber);
        }

        return Response.ok().entity(usersListResponse).build();
    }

    private boolean validPaging(int pageNumber, int pageSize) {

        if (pageNumber < 0 || pageSize < 0) {
            return false;
        }

        if (pageNumber == 0 && pageSize != 0) {
            return false;
        }

        return true;
    }
}
