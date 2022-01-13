package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/colors")
public class ColorsController {
	private final ColorService colorService;

	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}

	@GetMapping("getall")
	public ResponseEntity<DataResult<List<ColorListDto>>> getAll() {
		return ResponseEntity.ok(colorService.getAll());
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateColorRequest createColorRequest){
		var result = colorService.addColor(createColorRequest);
		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
		var result = colorService.updateColor(updateColorRequest);
		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
