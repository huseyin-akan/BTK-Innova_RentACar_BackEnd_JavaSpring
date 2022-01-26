package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("api/payments")
@CrossOrigin
public class PaymentsController {
	
	private final PaymentService paymentService;
	private final InvoiceService invoiceService;
	
	public PaymentsController(PaymentService paymentService, InvoiceService invoiceService) {
		this.paymentService = paymentService;
		this.invoiceService = invoiceService;
	}
	@PostMapping("pay")
	public ResponseEntity<?> makePayment(@RequestBody CreatePaymentRequest request){
		System.out.println(request);
		var result = paymentService.makePayment(request);
		if(result.isSuccess() ) {
			var invoice = this.invoiceService.getInvoiceByRentalId(request.getRentalId() );
			return ResponseEntity.ok(invoice);
		}
		else {
			return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
		}
	}
	
}
