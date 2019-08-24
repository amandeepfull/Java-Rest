package com.foody.payment.services;

import com.foody.payment.entities.Payment;
import com.foody.payment.interfaces.PaymentStategy;

public class CreditCardService implements PaymentStategy {
    @Override
    public Payment proceedForPayment(Payment payment) {
        return null;
    }
}
