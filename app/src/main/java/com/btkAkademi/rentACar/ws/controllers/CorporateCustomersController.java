package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;

@RestController
@RequestMapping("api/corporatecustomers")

public class CorporateCustomersController{
	private final CorporateCustomerService corporateCustomerService;

	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest){
		var result = corporateCustomerService.addCustomer(createCorporateCustomerRequest);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
