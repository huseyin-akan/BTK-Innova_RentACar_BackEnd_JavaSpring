package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.InvoiceListDtoProj;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	Result saveMadeOutInvoice(CreateInvoiceRequest request);
	DataResult<InvoiceListDtoProj> getInvoiceByRentalId(int rentalId);
}
