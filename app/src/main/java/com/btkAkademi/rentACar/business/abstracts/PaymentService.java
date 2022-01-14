package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	Result makePayment(CreatePaymentRequest request);
}
