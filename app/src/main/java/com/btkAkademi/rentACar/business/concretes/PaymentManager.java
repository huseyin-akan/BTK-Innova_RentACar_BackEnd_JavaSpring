package com.btkAkademi.rentACar.business.concretes;

import java.time.Period;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {
	
	private final PaymentDao paymentDao;
	private final ModelMapperService modelMapperService;
	private final AdditionalServiceService additionalServiceService;
	private final RentalService rentalService;

	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
			AdditionalServiceService additionalServiceService, RentalService rentalService) {
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.rentalService = rentalService;
	}

	@Override
	public Result makePayment(CreatePaymentRequest request) {
		
		var payment = this.modelMapperService.forRequest().map(request, Payment.class);
		payment.setTotalSum( calculateTotalSum(request.getRentalId() ) );
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTSUCCESSFUL);		
	}
	
	private double calculateTotalSum(int rentalId) {
		var rental = this.rentalService.getRentalById(rentalId).getData();
		var car = rental.getCar();
		
		var dayCount = Period.between(rental.getRentDate(), rental.getReturnDate()).getDays() + 1;
		
		var carPrice = car.getDailyPrice();
		var rentalSum = dayCount * carPrice ;
		
		var additionalServices = this.additionalServiceService.getAdditionalServicesByRentalId(rentalId).getData();
		
		double additionalServicesSum = 0;
		for(AdditionalService as : additionalServices) {
			additionalServicesSum+= as.getTotalSum();
		}		
		return rentalSum + additionalServicesSum;
	}	
	
}
