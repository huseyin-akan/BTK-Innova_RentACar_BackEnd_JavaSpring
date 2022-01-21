package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;

@RestController
@RequestMapping("api/individualcustomer")
@CrossOrigin
public class IndividualCustomersController {
	private final IndividualCustomerService individualCustomerService;

	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addCustomer(@RequestBody CreateIndividualCustomerRequest createIndividualCustomer){
		var result = individualCustomerService.addIndividualCustomer(createIndividualCustomer);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("getbyemail")
	public ResponseEntity<?> getByEmail(@RequestParam String email){
		var result = individualCustomerService.getByEmail(email);
		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
