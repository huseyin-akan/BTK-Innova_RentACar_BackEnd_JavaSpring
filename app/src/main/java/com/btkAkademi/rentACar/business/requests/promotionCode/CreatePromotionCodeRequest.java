package com.btkAkademi.rentACar.business.requests.promotionCode;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionCodeRequest implements IRequest {
	private String code;
	private byte discountRate;
	private LocalDate startDate;
	private LocalDate endDate;
}
