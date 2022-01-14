package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.helpers.CreditCardInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements IRequest {
	private LocalDate paymentDate;
	private CreditCardInfo cardInfo;
	private int rentalId;
}
