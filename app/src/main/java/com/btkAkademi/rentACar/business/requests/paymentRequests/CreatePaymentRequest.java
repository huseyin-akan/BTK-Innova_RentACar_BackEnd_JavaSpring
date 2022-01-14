package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest implements IRequest {
	private LocalDate paymentDate;
	
	private int rentalId;
}
