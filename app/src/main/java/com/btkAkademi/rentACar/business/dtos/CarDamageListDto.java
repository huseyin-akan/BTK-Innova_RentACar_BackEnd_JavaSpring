package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto implements IDto{
	private int id;
	private String description;
	
	private int carId;
	private int carKilometer;
	private int carModelYear;
	private String carDescription;
}
