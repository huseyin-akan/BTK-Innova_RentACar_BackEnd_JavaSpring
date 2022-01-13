package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
	private int id;
	private String maintenanceReason; 
	private LocalDate startDate;
	private LocalDate returnDate;
	
	private int carId;
	private int carKilometer;
	private int carModelYear;
	private String carDescription;
}
