package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest implements IRequest {
	private String email;
	private String password;
	private String companyName;	
	private String taxNumber;
}
