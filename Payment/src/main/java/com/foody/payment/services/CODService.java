package com.foody.payment.services;

import com.foody.payment.entities.Payment;
import com.foody.payment.enums.PaymentType;
import com.foody.payment.interfaces.PaymentStategy;

public class CODService implements PaymentStategy {
    @Override
    public Payment proceedForPayment(Payment payment) {

        /// cash on delivery has to process here

        return new Payment();
    }
}
