package com.foody.payment.endpoints;

import com.foody.payment.annotations.AccessTokenCheck;
import com.foody.payment.baseEndpoints.AbstractBaseEndpoint;
import com.foody.payment.entities.Payment;
import com.foody.payment.response.ApiResponse;
import com.foody.payment.services.PaymentService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@AccessTokenCheck
@Path("/v1/payment")
public class PaymentEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response payForOrder(Payment payment){
        ApiResponse response = new ApiResponse();

        payment = new PaymentService().proceedForPayment(payment);
        response.add("payment", payment);
        response.setOk(true);

        return Response.ok(response).build();
    }
}
