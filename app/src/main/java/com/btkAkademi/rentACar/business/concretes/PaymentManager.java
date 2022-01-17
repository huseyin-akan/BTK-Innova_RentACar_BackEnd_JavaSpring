package com.btkAkademi.rentACar.business.concretes;

import java.time.Period;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CreditCardInfoService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;
import com.btkAkademi.rentACar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {
	
	private final PaymentDao paymentDao;
	private final ModelMapperService modelMapperService;
	private final AdditionalServiceService additionalServiceService;
	private final RentalService rentalService;
	private final PosSystemService posSystemService;
	private final CreditCardInfoService creditCardInfoService;



	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
			AdditionalServiceService additionalServiceService, RentalService rentalService,
			PosSystemService posSystemService, CreditCardInfoService creditCardInfoService) {
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.rentalService = rentalService;
		this.posSystemService = posSystemService;
		this.creditCardInfoService = creditCardInfoService;
	}

	@Override
	public Result makePayment(CreatePaymentRequest request) {
		
		var result= BusinessRules.run(
				//TODO burayı düzelt.
				//checkIfCreditCardValid(request.getCreateCreditCardInfoRequest() )
				);
		
		if(result != null) {
			return result;
		}
		
		if(request.isSaveRequested() ) {
			//var card =this.modelMapperService.forDto().map(request.getCardInfo(), CreateCreditCardInfoRequest.class); 
			//this.creditCardInfoService.saveCard(card);
		}
		
		var payment = this.modelMapperService.forRequest().map(request, Payment.class);
		payment.setTotalSum( calculateTotalSum(request.getRentalId() ) );
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTSUCCESSFUL);		
	}
	
	private double calculateTotalSum(int rentalId) {
		var rental = this.rentalService.getRentalById(rentalId).getData();
		var car = rental.getCar();
		
		var dayCount = Period.between(rental.getRentDate(), rental.getReturnDate()).getDays();
		if(dayCount == 0) {dayCount=1;}
		
		var carPrice = car.getDailyPrice();
		var rentalSum = dayCount * carPrice ;
		
		var additionalServices = this.additionalServiceService.getAdditionalServicesByRentalId(rentalId).getData();
		
		double additionalServicesSum = 0;
		for(AdditionalService as : additionalServices) {
			additionalServicesSum+= as.getTotalSum();
		}		
		return rentalSum + additionalServicesSum;
	}	
	
	private Result checkIfCreditCardValid(CreditCardInfo cardInfo) {
		return this.posSystemService.checkIfCreditCardIsValid(cardInfo);
	}
}
