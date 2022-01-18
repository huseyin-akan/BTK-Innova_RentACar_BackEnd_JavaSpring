package com.btkAkademi.rentACar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest implements IRequest {
	
	@NotEmpty
	private String maintenanceReason;
	
    private LocalDate startDate;       
	
    private int carId;
}
