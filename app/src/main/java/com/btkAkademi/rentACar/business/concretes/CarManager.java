package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private final CarDao carDao;
	private final ModelMapperService modelMapperService;
	private final BrandService brandService;
	private final ColorService colorService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService,
			ColorService colorService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	}

	public DataResult<List<CarListDto>> getAll() {
		var carList = this.carDao.findAll();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
	public DataResult<List<CarListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		String message = ((pageNo-1)*pageSize+1) + " ile " + ((pageNo-1)*pageSize+pageSize)
				+ " arası arabalar başarıyla döndürüldü.";
		
		var response = this.carDao.findAll(pageable).getContent().stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, message);
	}
	
	public DataResult<List<CarListDto>> getAllRentableCars() {
		var carList = this.carDao.getAllRentableCars();
		return new SuccessDataResult<List<CarListDto>>(carList);
	}
	
	public DataResult<List<CarListDto>> getRentableCars(){
		return getRentableCars(1, 10);
	}
	
	public DataResult<List<CarListDto>> getRentableCars(int pageNo){
		return getRentableCars(pageNo, 10);
	}
	
	public DataResult<List<CarListDto>> getRentableCars(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		String message = ((pageNo-1)*pageSize+1) + " ile " + ((pageNo-1)*pageSize+pageSize) 
				+ " arası arabalar başarıyla döndürüldü.";
		
		var response = this.carDao.getAllRentableCars(pageable).stream()
				.map(car -> modelMapperService.forDto()
				.map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response , message);
	}

	public Result addCar(CreateCarRequest carCreateDto) {
		var result= BusinessRules.run(
				checkIfBrandExists(carCreateDto.getBrandId() ),
				checkIfColorExists(carCreateDto.getColorId())
				);
		
		if(result != null) {
			return result;
		}
		
		var carToAdd = this.modelMapperService.forRequest().map(carCreateDto, Car.class);
		this.carDao.save(carToAdd);
		return new SuccessResult(Messages.CARADDSUCCESSFUL);
	}

	public Result updateCar(UpdateCarRequest updateCarRequest) {
		
		var result= BusinessRules.run(
				checkIfCarExists(updateCarRequest.getId() ),
				checkIfBrandExists(updateCarRequest.getBrandId() ),
				checkIfColorExists(updateCarRequest.getColorId() )

				);
		
		if(result != null) {
			return result;
		}
		
		var carToUpdate = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(carToUpdate);
		
		return new SuccessResult(Messages.CARUPDATED);
	}

	public DataResult<Car> getCarById(int id) {
		var car = this.carDao.findById(id);
		return car.isPresent() ? new SuccessDataResult<Car>(car.get() ) : new ErrorDataResult<Car>(Messages.CARNOTFOUND);
	}
	
	private Result checkIfBrandExists(int id) {
		return this.brandService.getBrandById(id).isSuccess() ? new SuccessResult() : new ErrorResult(Messages.BRANDNOTFOUND);
	}
	
	private Result checkIfColorExists(int id) {
		return this.colorService.getColorById(id).isSuccess() ? new SuccessResult() : new ErrorResult(Messages.COLORNOTFOUND);
	}
	
	private Result checkIfCarExists(int id) {
		var car = this.carDao.findById(id);
		return car.isPresent() ? new SuccessResult() : new ErrorResult(Messages.CARNOTFOUND);
	}
	


}
