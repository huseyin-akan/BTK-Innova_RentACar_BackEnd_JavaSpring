package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalListDto implements IDto {
	private int id;
	
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private int rentedKilometer;
	
	private int returnedKilometer;    
}
