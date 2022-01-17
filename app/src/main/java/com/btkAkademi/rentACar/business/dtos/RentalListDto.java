package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.Customer;

import lombok.Data;

@Data
public class RentalListDto implements IDto {
	private int id;
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private int rentedKilometer;
	
	private int returnedKilometer;

	private City rentedCity;

	private City returnedCity;
	
	private Customer customer;
	
    private Car car;
    
    private List<AdditionalService> additionalServices;
    
}
