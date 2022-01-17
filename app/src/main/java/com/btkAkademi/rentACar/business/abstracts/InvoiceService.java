package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	Result saveMadeOutInvoice(CreateInvoiceRequest request);
	DataResult<InvoiceListDto> getInvoiceByRentalId(int rentalId);
	DataResult<InvoiceListDto> makeOutInvoice(int rentalId);
}
