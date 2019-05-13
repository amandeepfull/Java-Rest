package com.authserver.api.endpoints.apis;

import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.annotations.ApiKeyCheck;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.Contact;
import com.commons.response.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("v1/contact")
public class ContactEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response createContact( Contact contact){

        ApiResponse response = new ApiResponse();

        contact = ContactDaoImpl.getInstance().createContact(contact);

        response.setOk(true);
        response.add("contact", contact);
        return Response.ok(response).build();
    }

    @GET
    @Path("/uname/{uname}")
    public Response getContact (@PathParam("uname") String uname){

        ApiResponse response = new ApiResponse();

        Contact contact = ContactDaoImpl.getInstance().getByEmail(uname);

        if(contact == null)
            throw new NotFoundException("contact not found");

        response.setOk(true);
        response.add("contact", contact);
        return Response.ok(response).build();
    }
}
