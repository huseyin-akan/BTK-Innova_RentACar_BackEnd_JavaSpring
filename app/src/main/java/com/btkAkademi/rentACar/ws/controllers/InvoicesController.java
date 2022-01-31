package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;


@RestController
@RequestMapping("api/invoices")
@CrossOrigin
public class InvoicesController {
	
	private final InvoiceService invoiceService;
	
	public InvoicesController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@PostMapping("pay/{rentalId}")
	public ResponseEntity<?> getInvoiceById(@PathVariable int rentalId){
		var result = invoiceService.getInvoiceByRentalId(rentalId);
		if(result.isSuccess() ) {
			return ResponseEntity.ok(result);
		}
		else {
			return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
		}
	}
	
}