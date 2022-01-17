package com.btkAkademi.rentACar.business.requests.creditCardInfoRequest;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardInfoRequest implements IRequest{

	private String creditCard;
	private String validDate;	
	private String cVC;	
	private String cardHolder;
	private int customerId;
}
