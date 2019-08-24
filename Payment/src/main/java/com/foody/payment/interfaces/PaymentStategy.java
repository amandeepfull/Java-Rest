package com.foody.payment.interfaces;

import com.foody.payment.entities.Payment;

public interface PaymentStategy {
    Payment proceedForPayment(Payment payment);
}
