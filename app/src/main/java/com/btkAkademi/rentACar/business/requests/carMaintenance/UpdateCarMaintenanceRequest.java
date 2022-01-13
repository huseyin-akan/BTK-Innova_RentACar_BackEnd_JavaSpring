package com.btkAkademi.rentACar.business.requests.carMaintenance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	private String maintenanceReason;
    private LocalDate startDate; 
    private LocalDate returnDate;

    private int carId;
}
