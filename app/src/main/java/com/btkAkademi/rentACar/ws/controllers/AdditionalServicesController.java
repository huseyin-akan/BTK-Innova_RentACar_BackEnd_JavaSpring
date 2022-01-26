package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;

@RestController
@RequestMapping("api/additionalservices")
@CrossOrigin
public class AdditionalServicesController {
	private final AdditionalServiceService additionalServiceService;
	
	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}

	@PostMapping("add")
	public ResponseEntity<?> addAdditionalService(@RequestBody CreateAdditionalServiceRequest request){
		return ResponseEntity.ok(additionalServiceService.add(request));
	}
	
	@GetMapping("getall")
	public ResponseEntity<?> getAllAdditionalServices(){
		return ResponseEntity.ok(additionalServiceService.getAllAdditionalServices() );
	}
	
}
