package com.btkAkademi.rentACar.business.requests.carDamages;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest implements IRequest{
	private String description;
	private int carId;
}
