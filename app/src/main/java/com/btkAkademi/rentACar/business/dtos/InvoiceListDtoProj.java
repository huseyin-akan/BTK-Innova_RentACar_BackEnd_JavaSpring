package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

public interface InvoiceListDtoProj extends IDto{
	String getFirstName();
	String getLastName();
	String getEmail();
	LocalDate getRentDate();
	LocalDate getReturnDate();
	int getRentedKilometer();
	double getDailyPrice();
	String getModelYear();
	String getBrandName();
	String getModelName();
	String getImageUrl();
	String getColor();
	String getRentCity();
	String getReturnCity();
	LocalDate getPaymentDate();
	double getTotalSum();
}
