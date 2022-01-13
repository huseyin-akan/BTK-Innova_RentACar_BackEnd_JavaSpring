package com.btkAkademi.rentACar.business.requests.carRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest implements IRequest{

	private int id;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private int kilometer;
	private int findexScore;
	
	private int brandId;
	private int colorId;
}
