package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

import lombok.AllArgsConstructor;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private final CarMaintenanceDao carMaintenanceDao;
	private final ModelMapperService modelMapperService;
	private final RentalService rentalService;
	private final CarService carService;
	
	@Lazy
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			 RentalService rentalService,  CarService carService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
	}
	

	public Result sendToMaintenance(CreateCarMaintenanceRequest carMaintenanceRequest) {
		
		var car = this.carService.getCarById(carMaintenanceRequest.getCarId()).getData();
		
		var result= BusinessRules.run(
				checkIfPossibleToSendCarToMaintenance(carMaintenanceRequest.getCarId()),
				checkIfCarIsRented(car)
				);
		
		if(result != null) {
			return result;
		}
		
		var maintenanceToAdd = this.modelMapperService.forRequest().map(carMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(maintenanceToAdd);
		return new SuccessResult(Messages.CARSENTTOMAINTENANCE);
	}

	public Result updateCarMaintenance(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		var car = this.carService.getCarById(updateCarMaintenanceRequest.getCarId()).getData();
		
		var result= BusinessRules.run(
				checkIfCarUnderMaintenance(updateCarMaintenanceRequest.getCarId()),
				checkIfCarIsRented(car)
				);
		
		if(result != null) {
			return result;
		}
		
		var maintenanceToAdd = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(maintenanceToAdd);
		return new SuccessResult(Messages.CARMAINTENANCEUPDATED);
	}


	public Result checkIfCarUnderMaintenance(int carId) {		
		return this.carMaintenanceDao.findByCarIdAndReturnDateIsNull(carId) != null ?
				new SuccessResult(Messages.CARISINMAINTENANCE) : new ErrorResult(Messages.CARISNOTINMAINTENANCE);
	}

	public DataResult<List<CarMaintenanceListDto>> getAllCarsInMaintenance() {
		var carMaintenanceList = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response = carMaintenanceList.stream().map(car -> modelMapperService.forDto().map(car, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}
	
	private Result checkIfPossibleToSendCarToMaintenance(int carId) {
		return checkIfCarUnderMaintenance(carId).isSuccess() ?
				new ErrorResult(Messages.CARISINMAINTENANCE) :
					new SuccessResult();
	}
	
	private Result checkIfCarIsRented(Car car) {
		return this.rentalService.checkIfCarIsRented(car);
	}


	

	
}
