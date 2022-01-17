package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Customer;
import com.btkAkademi.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto implements IDto{
	private LocalDate invoiceDate;
	private Customer customer;
	private Rental rental;
	private Car car;
	private List<AdditionalService> additionalServices;
	private double totalSum;	
}
