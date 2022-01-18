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
import com.btkAkademi.rentACar.business.dtos.CarListDtoProj;
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
	
	public DataResult<List<CarListDtoProj>> getAllRentableCars() {
		var carList = this.carDao.getAllAvailableCars();
		if(carList.isEmpty()) {
			return new ErrorDataResult<List<CarListDtoProj>>(Messages.NOCARTOLIST);
		}
		return new SuccessDataResult<List<CarListDtoProj>>(carList.get());
	}
	
	public DataResult<List<CarListDtoProj>> getAllRentableCarsPaged(){
		return getAllRentableCarsPaged(1, 10);
	}
	
	public DataResult<List<CarListDtoProj>> getAllRentableCarsPaged(int pageNo){
		return getAllRentableCarsPaged(pageNo, 10);
	}
	

	public DataResult<List<CarListDtoProj>> getAllRentableCarsPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		var response = this.carDao.getAllAvailableCars(pageable);
		
		if(response.isEmpty()) {
			return new ErrorDataResult<List<CarListDtoProj>>(Messages.NOCARTOLIST);
		}
		
		String message = ((pageNo-1)*pageSize+1) + " ile " + ((pageNo-1)*pageSize+pageSize) 
				+ " arası arabalar başarıyla döndürüldü.";
				
		return new SuccessDataResult<List<CarListDtoProj>>(response.get() , message);
	}

	public Result addCar(CreateCarRequest request) {
		var result= BusinessRules.run(
				checkIfBrandExists(request.getBrandId() ),
				checkIfColorExists(request.getColorId())
				);
		
		if(result != null) {
			return result;
		}
		
		var carToAdd = this.modelMapperService.forRequest().map(request, Car.class);
		System.out.println(carToAdd);
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

	@Override
	public int getFindexScoreById(int id) {
		return this.carDao.getFindexScoreById(id);
	}
	
	@Override
	public int getMinAgeById(int id) {
		return this.carDao.getMinAgeById(id);
	}

	@Override
	public DataResult<Car> getAnAvailableCarByClassId(int classId) {
		
		var newCar = this.carDao.getAnAvailableCarIdByClassId(classId);
		if(newCar.isEmpty() ) {
			return new ErrorDataResult<Car>(Messages.NOCARTORENTINTHISCLASS);
		}
		
		var car = this.carDao.getById( newCar.get() );
		return new SuccessDataResult<Car>(car);
	}
	


}
