package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.requests.carDamages.CreateCarDamageRequest;


@RestController
@RequestMapping("api/cardamages")
public class CarDamagesController {
	
	private final CarDamageService carDamageService;

	public CarDamagesController(CarDamageService carDamageService) {
		this.carDamageService = carDamageService;
	}
	
	@GetMapping("getcardamages")
	public ResponseEntity<?> getAllCarDamages(){		
		return ResponseEntity.ok(carDamageService.getAllCarDamages());
	}
	
	@PostMapping("adddamage")
	public ResponseEntity<?> addDamage(@RequestBody CreateCarDamageRequest request) {
		return ResponseEntity.ok(carDamageService.addCarDamage(request));
	}
	
}
