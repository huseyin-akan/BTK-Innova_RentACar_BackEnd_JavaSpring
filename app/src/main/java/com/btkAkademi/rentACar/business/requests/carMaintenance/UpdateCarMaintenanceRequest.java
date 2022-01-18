package com.btkAkademi.rentACar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	private int id;
	
	@NotEmpty
	private String maintenanceReason;
	
	@NotEmpty
	private String maintenanceResult;
	
    private LocalDate startDate; 
    private LocalDate returnDate;

    private int carId;
}
