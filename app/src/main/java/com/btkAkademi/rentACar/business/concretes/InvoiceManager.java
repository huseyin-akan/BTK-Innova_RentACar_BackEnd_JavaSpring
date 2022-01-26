package com.btkAkademi.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDtoProj;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService{
	private final InvoiceDao invoiceDao;
	private final ModelMapperService modelMapperService;
	private final RentalService rentalService;
	private final IndividualCustomerService individualCustomerService;
	private final CorporateCustomerService corporateCustomerService;
	private final CarService carService;
	private final AdditionalServiceService additionalServiceService;

	@Override
	public DataResult<InvoiceListDtoProj> getInvoiceByRentalId(int rentalId) {
		
		var invoice = this.invoiceDao.getInvoiceByRentalId(rentalId);
		if(invoice.isEmpty() ) {
			return new ErrorDataResult<InvoiceListDtoProj>(Messages.NOINVOICEFOUND);
		}
		var invoiceToReturn = invoice.get();
			return new SuccessDataResult<InvoiceListDtoProj>(invoiceToReturn);
	}

	//TODO daha önce faturası kesilen fatura tekrar kesilemez.
	@Override
	public Result saveMadeOutInvoice(CreateInvoiceRequest request) {
		var result= BusinessRules.run(
				
				);
		
		if(result != null) {
			return result;
		}
		
		var invoiceToMakeOut = this.modelMapperService.forRequest().map(request, Invoice.class);
		this.invoiceDao.save(invoiceToMakeOut);
		return new SuccessResult(Messages.INVOICEADDED);
	}

	public DataResult<InvoiceListDtoProj> makeOutInvoice(int rentalId) {
//		var invoiceListDto = new InvoiceListDto();
//		var rental = this.rentalService.getRentalById(rentalId).getData();
//		var customerId = rental.getCustomer().getId();
//		var car = this.rentalService.getCarByRentalId(rentalId).getData();
//		
//		invoiceListDto.setAdditionalServices(this.additionalServiceService.getAdditionalServicesByRentalId(rentalId).getData());
//		invoiceListDto.setInvoiceDate(LocalDate.now());
//		invoiceListDto.setRental(rental);
//		invoiceListDto.setCustomer(this.individualCustomerService.getById(customerId).getData());
//		invoiceListDto.setCar(car);
//		
//		return new SuccessDataResult<InvoiceListDto>(invoiceListDto);
		return null;
	}
	
	
}
