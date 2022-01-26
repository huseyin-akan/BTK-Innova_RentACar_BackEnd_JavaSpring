package com.btkAkademi.rentACar.ws.controllers;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateCorporateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateIndividualRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.EndICRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@RestController
@RequestMapping("api/rentals")
@CrossOrigin
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
	@Transactional
	public ResponseEntity<?> rentForIndividual(@RequestBody CreateIndividualRentalRequest request){
		var result = rentalService.rentForIndividualCustomer(request);
		if(result.isSuccess() ) {
			var rental = rentalService.getLastRentalByCustomerId(request.getCustomerId() );
			return ResponseEntity.ok(rental);
		}
		var rentalResult = new ErrorDataResult<Rental>(result.getMessage() );
		return ResponseEntity.badRequest().body(rentalResult);
	}
	
	@PostMapping("endrentalforic")
	public ResponseEntity<?> endRentalForIC(@RequestBody EndICRentalRequest request){
		var result = rentalService.endRentalProcessForIC(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("getrentalbyid")
	public ResponseEntity<?> getRentalById(int rentalId){
		var result = rentalService.getRentalById(rentalId);
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);		
	}
	
}
