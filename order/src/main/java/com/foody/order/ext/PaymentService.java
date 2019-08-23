package com.foody.order.ext;

import com.foody.order.enums.PaymentType;

public class PaymentService {
    public boolean proceedForPayment(String userId, String orderId, double totalAmt, PaymentType paymentType) {

        /// hitting payment endpoint to start payment
        return true;

    }
}
