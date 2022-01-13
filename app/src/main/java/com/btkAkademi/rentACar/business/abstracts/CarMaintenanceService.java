package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarMaintenanceService {
	Result sendToMaintenance(CreateCarMaintenanceRequest carMaintenanceRequest);
	Result updateCarMaintenance(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result checkIfCarUnderMaintenance(int carId);
	DataResult<List<CarMaintenanceListDto>> getAllCarsInMaintenance();
	
}
