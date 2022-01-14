package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	
	private final RentalDao rentalDao;
	private final ModelMapperService modelMapperService;
	private final IndividualCustomerService individualCustomerService;
	private final CorporateCustomerService corporateCustomerService;
	private final CarMaintenanceService carMaintenanceService;
	private final CarService carService;
	
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService
			, CarMaintenanceService carMaintenanceService, CarService carService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
	}

	public Result addRental(CreateRentalRequest request) {
		var result = BusinessRules.run(
				checkIfKmIsValid(request.getRentedKilometer(), request.getReturnedKilometer()),
				checkIfReturnDateIsValid(request.getRentDate(), request.getReturnDate()),
				checkIfCustomerIsValid(request.getCustomerId()),
				checkIfCarExists(request.getCarId() ),
				checkIfCarIsAvailableToRent(request.getCarId())
				);
		
		if(result != null) {
			return result;
		}
		
		var rental = this.modelMapperService.forRequest().map(request, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTALADDED);
	}
	
	private Result checkIfKmIsValid(int rentedKm, int returnedKm) {
		if(rentedKm>returnedKm) {
			return new ErrorResult(Messages.KMINVALID);
		}
		return new SuccessResult();
	}
	
	private Result checkIfReturnDateIsValid(LocalDate rentedDate, LocalDate returnedDate) {
		Period period = Period.between(rentedDate, returnedDate);		
		
		if(period.isNegative()) {
			return new ErrorResult(Messages.DATEINVALID);
		};
		return new SuccessResult();
	}
	
	private Result checkIfCustomerIsValid(int customerId) {
		if(	
			!this.individualCustomerService.getById(customerId).isSuccess() && 
			!this.corporateCustomerService.getById(customerId).isSuccess()) 
		{
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}
			return new SuccessResult(); 
	}
	
	private Result checkIfCarExists(int carId) {
		var car = this.carService.getCarById(carId);
		if(car == null) {
			return new ErrorResult(Messages.CARNOTFOUND);
		}
		return new SuccessResult(); 
	}
	
	private Result checkIfCarIsAvailableToRent(int carId) {
		var result = this.carMaintenanceService.checkIfCarUnderMaintenance(carId);		
		return result.isSuccess() ? new ErrorResult(Messages.CARISINMAINTENANCE)  : new SuccessResult();		
	}
	
	public Result checkIfCarIsRented(int carId) {
		//TODO returned date'e de bak.
		var rental = this.rentalDao.findByCarId(carId);		
		return rental == null ? new SuccessResult() : new ErrorResult(Messages.CARISRENTED);
	}

	@Override
	public DataResult<Car> getCarByRentalId(int id) {
		var car = this.rentalDao.getById(id).getCar();
		return new SuccessDataResult<Car>(car);
	}

	@Override
	public DataResult<Rental> getRentalById(int id) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(id));
	}	
}
