package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.Data;

@Data
public class CreateRentalRequest implements IRequest{
	
	private LocalDate rentDate;
	private LocalDate returnDate;	
	private int rentedKilometer;	
	private int returnedKilometer;
	
	private int rentedCityId;
	private int returnedCityId;
	
	private int customerId;
	private int carId;
	
}
