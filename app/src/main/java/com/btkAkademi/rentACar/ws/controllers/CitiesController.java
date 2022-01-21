package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/cities")
@CrossOrigin
public class CitiesController {
	private final CityService cityService;

	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("getall")
	public ResponseEntity<DataResult<List<CityListDto>>> getAll() {
		return ResponseEntity.ok(cityService.getAllCities());
	}
}
