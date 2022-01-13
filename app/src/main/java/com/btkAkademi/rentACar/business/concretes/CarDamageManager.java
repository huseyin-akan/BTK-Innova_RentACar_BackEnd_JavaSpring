package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamages.CreateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{
	
	private final CarDamageDao carDamageDao;
	private final CarService carService;
	private final ModelMapperService modelMapperService;
	
	
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService, CarService carService) {
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public DataResult<List<CarDamageListDto>> getAllCarDamages() {
		var carDamageList = this.carDamageDao.findAll();
		List<CarDamageListDto> response = carDamageList.stream().map(carDamage -> 
			modelMapperService.forDto().map(carDamage, CarDamageListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageListDto>>(response);
	}

	@Override
	public Result addCarDamage(CreateCarDamageRequest request) {
		var result= BusinessRules.run(
				checkIfCarExists(request.getCarId() )
				);
		
		if(result != null) {
			return result;
		}
		
		var carDamageToAdd = this.modelMapperService.forRequest().map(request, CarDamage.class);
		this.carDamageDao.save(carDamageToAdd);
		return new SuccessResult(Messages.CARDAMAGEADDSUCCESSFUL);
	}
	
	private Result checkIfCarExists(int carId) {
		var result = this.carService.getCarById(carId);
		if(!result.isSuccess()) {
			 return new ErrorResult(Messages.CARNOTFOUND);
		}
		return new SuccessResult();
	}

}
