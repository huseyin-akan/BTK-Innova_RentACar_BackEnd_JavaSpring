package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PromotionCodeService;
import com.btkAkademi.rentACar.business.requests.promotionCode.CreatePromotionCodeRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/promotioncodes")
@AllArgsConstructor
public class PromotionCodesController {
	
	private final PromotionCodeService promotionCodeService;
	
	@PostMapping("add")
	public ResponseEntity<?> addPromotionCode(@RequestBody CreatePromotionCodeRequest request){
		return ResponseEntity.ok(promotionCodeService.addPromotionCode(request));	
	}
}
