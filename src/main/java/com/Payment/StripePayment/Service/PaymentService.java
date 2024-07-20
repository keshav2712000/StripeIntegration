package com.Payment.StripePayment.Service;

import com.Payment.StripePayment.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(double amount) throws StripeException;

}
