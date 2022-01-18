package com.btkAkademi.rentACar.ws.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("api/carmaintenances")
public class CarMaintenancesController {
	private final CarMaintenanceService carMaintenanceService;

	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping("getallcarsinmaintenance")
	public ResponseEntity<?> getAllCarsInMaintenance(){
		return ResponseEntity.ok(carMaintenanceService.getAllCarsInMaintenance());
	}
	
	@PostMapping("sendcartomaintenance")
	public ResponseEntity<?> sendCarToMaintenance(@RequestBody @Valid CreateCarMaintenanceRequest request){
		var result = carMaintenanceService.sendToMaintenance(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("updateCarmaintenance")
	public ResponseEntity<?> updateCarMaintenance(@RequestBody @Valid UpdateCarMaintenanceRequest request){
		var result = carMaintenanceService.updateCarMaintenance(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
		return errors;
	}
}
