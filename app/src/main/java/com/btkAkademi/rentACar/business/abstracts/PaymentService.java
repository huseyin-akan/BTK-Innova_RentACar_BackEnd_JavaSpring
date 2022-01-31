package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.InvoiceListDtoProj;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface PaymentService {
	DataResult<InvoiceListDtoProj> makePayment(CreatePaymentRequest request);
}
