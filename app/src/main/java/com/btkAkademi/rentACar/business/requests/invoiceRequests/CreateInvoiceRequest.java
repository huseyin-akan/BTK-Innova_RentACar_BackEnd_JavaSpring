package com.btkAkademi.rentACar.business.requests.invoiceRequests;

import com.btkAkademi.rentACar.business.requests.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest implements IRequest {
	private int rentalId;
	private String filePath;
}
