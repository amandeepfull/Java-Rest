package com.foody.payment.endpoints;

import com.foody.payment.baseEndpoints.AbstractBaseEndpoint;
import com.foody.payment.entities.Payment;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1/payment")
public class PaymentEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response payForOrder(Payment payment){


    }
}
