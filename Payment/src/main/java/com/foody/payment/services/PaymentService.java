package com.foody.payment.services;

import com.foody.payment.entities.Payment;
import com.foody.payment.enums.PaymentType;

public class PaymentService {

    public Payment proceedForPayment(Payment payment){


        if(payment.getType() ==  PaymentType.COD)
           payment =  new CODService().proceedForPayment(payment);

        if(payment.getType() == PaymentType.CREDIT_CARD)
           payment =  new CreditCardService().proceedForPayment(payment);

        if(payment.getType() == PaymentType.NET_BANKING);
            payment = new NetBankingService().proceedForPayment(payment);

         return payment;
    }
}
