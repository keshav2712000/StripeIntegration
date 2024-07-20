package com.Payment.StripePayment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Payment.StripePayment.Service.PaymentService;
import com.Payment.StripePayment.response.PaymentResponse;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<PaymentResponse> getPaymentUrl(@RequestParam double amount) throws StripeException{
		return new ResponseEntity<>(paymentService.createPaymentLink(amount),HttpStatus.OK);
	}
	
	@GetMapping("/success")
	public String paymentSuccess() {
		return "Payment SuccessFull";
	}
	
	@GetMapping("/fail")
	public String paymentFail() {
		return "Payment Failed";
	}
	
}
