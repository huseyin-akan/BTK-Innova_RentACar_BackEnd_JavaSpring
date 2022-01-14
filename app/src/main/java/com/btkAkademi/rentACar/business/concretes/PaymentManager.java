package com.btkAkademi.rentACar.business.concretes;

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
		payment.setTotalSum( calculateTotalSum(request.getRentalId(), request.getAdditionalServiceId()) );
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTSUCCESSFUL);		
	}
	
	private double calculateTotalSum(int rentalId, int additionalServiceId) {
		//TODO burayı DBden al.
		var day = 3;
		var car = this.rentalService.getCarByRentalId(rentalId).getData();
		
		
		var carPrice = car.getDailyPrice();
		var rentalSum = day * carPrice ;
		var additionalServiceSum = this.additionalServiceService.getSumByAdditionalServiceId(additionalServiceId);
		
		return rentalSum + additionalServiceSum;
	}	
	
}
