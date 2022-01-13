package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;

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
	public ResponseEntity<?> sendCarToMaintenance(@RequestBody CreateCarMaintenanceRequest request){
		var result = carMaintenanceService.sendToMaintenance(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("updateCarmaintenance")
	public ResponseEntity<?> updateCarMaintenance(@RequestBody UpdateCarMaintenanceRequest request){
		var result = carMaintenanceService.updateCarMaintenance(request);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
}
