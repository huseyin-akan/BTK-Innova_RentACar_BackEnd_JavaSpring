package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateCorporateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateIndividualRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
	
	private final RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@PostMapping("rentforcorporate")
	public ResponseEntity<?> rentForCorporate(@RequestBody CreateCorporateRentalRequest request){
		var result = rentalService.rentForCorporateCustomer(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("rentforindividual")
	public ResponseEntity<?> rentForIndividual(@RequestBody CreateIndividualRentalRequest request){
		var result = rentalService.rentForIndividualCustomer(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("getrentalbyid")
	public ResponseEntity<?> getRentalById(int rentalId){
		var result = rentalService.getRentalById(rentalId);
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);		
	}
	
}
