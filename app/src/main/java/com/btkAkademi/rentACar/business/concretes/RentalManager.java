package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.FindexScoreService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.PromotionCodeService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateCorporateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateIndividualRentalRequest;
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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService{
	
	private final RentalDao rentalDao;
	private final ModelMapperService modelMapperService;
	private final IndividualCustomerService individualCustomerService;
	private final CorporateCustomerService corporateCustomerService;
	private final CarMaintenanceService carMaintenanceService;
	private final CarService carService;
	private final FindexScoreService findexScoreService;
	
	public Result addRental(CreateRentalRequest request) {
		return null;
	}
	
	@Override
	public Result rentForIndividualCustomer(CreateIndividualRentalRequest request) {
		
		var car = this.carService.getCarById(request.getCarId() ).getData();
		
		var result = BusinessRules.run(
				checkIfCustomerIsValid(request.getCustomerId()),
				checkIfCarExists(request.getCarId() ),
				checkIfIndividualFindexScoreIsEnough(request.getCustomerId(), request.getCarId() ),
				checkIfAgeIsEnoughToRent(request.getCustomerId(), request.getCarId())
				);
		
		if(result != null) {
			return result;
		}
		
		var rental = this.modelMapperService.forRequest().map(request, Rental.class);
		
		//if car is not available then give another car in the same class
		System.out.println(checkIfCarIsAvailableToRent(car).isSuccess());
		System.out.println(checkIfCarIsRented(car).isSuccess());
		
		if(	!checkIfCarIsAvailableToRent(car).isSuccess() || !checkIfCarIsRented(car).isSuccess()) {
			System.out.println("müsaiit değil araba.");
			
			var newCarToRent = this.carService.getAnAvailableCarByClassId(car.getCarClass().getId()).getData();
			rental.setCar(newCarToRent);
		}	
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTALADDED);
	}

	@Override
	public Result rentForCorporateCustomer(CreateCorporateRentalRequest request) {
		var car = this.carService.getCarById(request.getCarId() ).getData();
		
		var result = BusinessRules.run(
				checkIfCustomerIsValid(request.getCustomerId()),
				checkIfCarExists(request.getCarId() ),
				checkIfCarIsAvailableToRent(car),
				checkIfCorporateFindexScoreIsEnough(request.getCustomerId(), request.getCarId() )
				);
		
		if(result != null) {
			return result;
		}
		
		var rental = this.modelMapperService.forRequest().map(request, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTALADDED);
	}
	
	
	private Result checkIfAgeIsEnoughToRent(int customerId, int carId) {
		var customerBirthDate = this.individualCustomerService.getById(customerId).getData().getBirthDate();
		var period = Period.between(customerBirthDate, LocalDate.now() );
		var age = period.getYears();
		
		var minAge = this.carService.getCarById(carId).getData().getMinAge();
		
		if(age<minAge) {
			return new ErrorResult(Messages.CUSTOMERAGEISNOTENOUGH);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCorporateFindexScoreIsEnough(int customerId, int carId) {
		
		var customerTaxNo = this.corporateCustomerService.getTaxNumberById(customerId).getData();
		var carFindexScore = this.carService.getCarById(carId).getData().getFindexScore();
		
		return this.findexScoreService.getCorporateFindexScore(customerTaxNo).getData() <carFindexScore ?
				new ErrorResult(Messages.FINDEXSCORENOTENOUGH) :
					new SuccessResult();
	}
	
	private Result checkIfIndividualFindexScoreIsEnough(int customerId, int carId) {
		
		var customerTcNo = this.individualCustomerService.getNationalIdById(customerId);
		var carFindexScore = this.carService.getCarById(carId).getData().getFindexScore();
		
		return  this.findexScoreService.getIndividualFindexScore(customerTcNo).getData() <carFindexScore ?
				new ErrorResult(Messages.FINDEXSCORENOTENOUGH) :
					new SuccessResult();
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
	
	private Result checkIfCarIsAvailableToRent(Car car) {
		var result = this.carMaintenanceService.checkIfCarUnderMaintenance(car.getId());		
		return result.isSuccess() ? new ErrorResult(Messages.CARISINMAINTENANCE)  : new SuccessResult();		
	}
	
	public Result checkIfCarIsRented(Car car) {
		var rental = this.rentalDao.findByCarIdAndReturnedDateIsNull(car.getId());		
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
