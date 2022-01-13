package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
	
	private final RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addRental(@RequestBody CreateRentalRequest request){
		var result = rentalService.addRental(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
}
