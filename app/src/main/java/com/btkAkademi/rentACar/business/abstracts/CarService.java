package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	
	DataResult<List<CarListDto>> getAll();
	DataResult<List<CarListDto>> getAll(int pageNo, int pageSize);
	
	DataResult<List<CarListDto>> getAllRentableCars();
	DataResult<List<CarListDto>> getRentableCars(int pageNo, int pageSize);
	DataResult<List<CarListDto>> getRentableCars(int pageNo);
	DataResult<List<CarListDto>> getRentableCars();
	
	Result addCar(CreateCarRequest carCreateDto);	
	Result updateCar(UpdateCarRequest updateCarRequest);	
	DataResult<Car> getCarById(int id);
}
