package com.gk.rwsendpoints;

import com.gk.rwsendpoints.dto.AuthenticationRequest;
import com.gk.rwsendpoints.dto.AuthenticationResponse;
import com.gk.rwsendpoints.services.api.UserAuthenticationServiceAPI;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by greg korenevsky on 7/26/14.
 */
@Named
@Path("/rest/v1/authenticate")
public class UserAuthenticationController {

    private UserAuthenticationServiceAPI userAuthenticationService;

    public UserAuthenticationServiceAPI getUserAuthenticationService() {
        return userAuthenticationService;
    }

    @Inject
    public void setUserAuthenticationService(UserAuthenticationServiceAPI userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
/*    @Path("/authenticate")*/
    public Response authenticateUser(AuthenticationRequest input) {

        if (input == null || isBlank(input.getUserId()) || isBlank(input.getPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Pair<Boolean, String> authenticationResult =
                userAuthenticationService.authenticateUser(input.getUserId(), input.getPassword());

        AuthenticationResponse payLoad = new AuthenticationResponse(authenticationResult.getLeft());

        if (!payLoad.isUserAuthenticated()) {
            payLoad.setReason(authenticationResult.getRight());
        }

        return Response.ok().entity(payLoad).build();
    }
}
