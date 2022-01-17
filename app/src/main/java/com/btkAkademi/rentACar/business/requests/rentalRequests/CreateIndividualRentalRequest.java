package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualRentalRequest implements IRequest {
	private LocalDate rentDate;
	private LocalDate returnDate;	
	private int rentedKilometer;	
	private int returnedKilometer;
	
	private int rentedCityId;
	private int returnedCityId;
	
	private int customerId;
	private int carId;
}
