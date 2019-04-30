package com.authserver.api.endpoints.apis;

import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.Contact;
import com.commons.response.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
