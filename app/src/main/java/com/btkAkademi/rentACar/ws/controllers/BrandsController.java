package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/brands")
public class BrandsController {
	private final BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("getall")
	public ResponseEntity<DataResult<List<BrandListDto>>> getAll() {
		return ResponseEntity.ok(brandService.getAll());
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateBrandRequest brandCreateDto) {
		return ResponseEntity.ok(brandService.add(brandCreateDto));
	}

	@PostMapping("update")
	public ResponseEntity<?> udate(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {

		var result = brandService.update(updateBrandRequest);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
