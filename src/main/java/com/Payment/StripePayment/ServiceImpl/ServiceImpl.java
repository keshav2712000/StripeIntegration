package com.Payment.StripePayment.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.Payment.StripePayment.Service.PaymentService;
import com.Payment.StripePayment.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;
@Service
public class ServiceImpl implements PaymentService {

//	@Value("${stripe.api.key}")
//	private String stripeSecretKey;

	@Override
	public PaymentResponse createPaymentLink(double amount) throws StripeException {
		// TODO Auto-generated method stub

		Stripe.apiKey = "sk_test_51PdaowF1phHQphmCPANJ3ClKbx2A2mzzFH2AyLShldwSeOrFqp9cvzM1yfwO1CHEZdCtmsQIUqdhr30LdNL5netA0083o6kofI";
		List<SessionCreateParams.PaymentMethodType> lst = new ArrayList<>();
		lst.add(PaymentMethodType.CARD);
		SessionCreateParams params = SessionCreateParams.builder().addAllPaymentMethodType(lst)
				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl("http://localhost:8080/payment/success").setCancelUrl("http://localhost:8080/payment/fail")
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("eur").setUnitAmountDecimal(BigDecimal.valueOf(amount*100))
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("NOBEL").build())
								.build())
						.build())
				.build();
		
		Session session=Session.create(params);
		
		PaymentResponse res=new PaymentResponse();
		res.setPayment_url(session.getUrl());
		
		return res;
		

	}

}
