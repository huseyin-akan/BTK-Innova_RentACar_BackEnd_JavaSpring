package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarClassService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/carclasses")
@AllArgsConstructor
@CrossOrigin
public class CarClassesController {
	private final CarClassService carClassService;
	
	@GetMapping("getall")
	public ResponseEntity<?> getAll(){
		var result = carClassService.getAllCarClasses();
		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
