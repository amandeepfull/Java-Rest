package com.foody.auth.endpoints;

import com.foody.auth.entities.Login;
import com.foody.auth.response.ApiResponse;
import com.foody.auth.services.LoginService;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/outh/login")
public class LoginEndpoint {

    public Response login(Login login){

        ApiResponse response = new ApiResponse();

       new LoginService().login(login);

        response.setOk(true);

        return Response.ok(response).build();


    }

}
