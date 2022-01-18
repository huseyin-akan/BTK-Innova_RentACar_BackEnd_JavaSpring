package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CarListDtoProj;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	
	DataResult<List<CarListDto>> getAll();
	DataResult<List<CarListDto>> getAll(int pageNo, int pageSize);
	
	DataResult<List<CarListDtoProj>> getAllRentableCars();
	DataResult<List<CarListDtoProj>> getAllRentableCarsPaged();
	DataResult<List<CarListDtoProj>> getAllRentableCarsPaged(int pageNo);
	DataResult<List<CarListDtoProj>> getAllRentableCarsPaged(int pageNo, int pageSize); 
	
	Result addCar(CreateCarRequest carCreateDto);	
	Result updateCar(UpdateCarRequest updateCarRequest);	
	DataResult<Car> getCarById(int id);
	int getFindexScoreById(int id);
	int getMinAgeById(int id);
	
	DataResult<Car> getAnAvailableCarByClassId(int classId);
}
