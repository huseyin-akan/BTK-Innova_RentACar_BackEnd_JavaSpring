package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CreditCardInfoService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.business.abstracts.PromotionCodeService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;
import com.btkAkademi.rentACar.entities.concretes.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	
	private final PaymentDao paymentDao;
	private final ModelMapperService modelMapperService;
	private final AdditionalServiceService additionalServiceService;
	private final RentalService rentalService;
	private final PosSystemService posSystemService;
	private final CreditCardInfoService creditCardInfoService;
	private final PromotionCodeService promotionCodeService;


	@Override
	public Result makePayment(CreatePaymentRequest request) {
		
		var promotionCode = request.getCode();
		
		var result= BusinessRules.run(
				//TODO burayı düzelt.
				//checkIfCreditCardValid(request.getCreateCreditCardInfoRequest() )
				checkIfPromotionCodeValid(promotionCode)
				);
		
		if(result != null) {
			return result;
		}
		
		if(request.isSaveRequested() ) {
			//var card =this.modelMapperService.forDto().map(request.getCardInfo(), CreateCreditCardInfoRequest.class); 
			//this.creditCardInfoService.saveCard(card);
		}
		
		
		var payment = this.modelMapperService.forRequest().map(request, Payment.class);
		var sum = calculateTotalSum(request.getRentalId());

		
		var promotionCodeObj = this.promotionCodeService.getPromotionCodeByCode(promotionCode).getData();
		System.out.println(promotionCodeObj);
		if(promotionCodeObj != null) {
			byte discountRate = promotionCodeObj.getDiscountRate();
			sum = sum - (sum * discountRate / 100) ;
		}
		
		payment.setTotalSum(sum);
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTSUCCESSFUL);		
	}
	
	//TODO promosyon kod işlemlerini buraya yedirelim.
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
	
	private Result checkIfPromotionCodeValid(String code) {
		
		if(code == null) {
			return new SuccessResult();
		}
		
		var result = this.promotionCodeService.getPromotionCodeByCode(code);
		if(!result.isSuccess()) {
			return result;
		}
		
		var promotionCode = result.getData();
		
		if(!Period.between(promotionCode.getEndDate(), LocalDate.now()).isNegative() ) {
			return new ErrorResult(Messages.CODEEXPIRED);
		}else if( Period.between(promotionCode.getStartDate(), LocalDate.now()).isNegative() ) {
			return new ErrorResult(Messages.CODETIMENOTSTARTED);
		}
		
		return new SuccessResult();
	}
	
}
