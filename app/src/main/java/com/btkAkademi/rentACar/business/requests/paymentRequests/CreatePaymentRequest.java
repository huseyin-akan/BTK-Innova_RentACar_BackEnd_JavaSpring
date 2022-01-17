package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements IRequest {
	private LocalDate paymentDate;
	private boolean saveRequested;
	private CreateCreditCardInfoRequest createCreditCardInfoRequest;
	private int rentalId;
	private String code;
}
