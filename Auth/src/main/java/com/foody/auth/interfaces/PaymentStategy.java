package com.foody.auth.interfaces;

import com.foody.auth.entities.Payment;

public interface PaymentStategy {
    Payment proceedForPayment(Payment payment);
}
