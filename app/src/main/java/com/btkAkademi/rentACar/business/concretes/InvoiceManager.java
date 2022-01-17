package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{
	private final InvoiceDao invoiceDao;
	private final ModelMapperService modelMapperService;
	private final RentalService rentalService;
	private final IndividualCustomerService individualCustomerService;
	private final CorporateCustomerService corporateCustomerService;
	private final CarService carService;
	private final AdditionalServiceService additionalServiceService;

	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RentalService rentalService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			CarService carService, AdditionalServiceService additionalServiceService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.carService = carService;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	//TODO burayı mapleyerek dönmeliyiz.
	public DataResult<InvoiceListDto> getInvoiceByRentalId(int rentalId) {
		var asd = this.invoiceDao.findById(rentalId).get();
		return new SuccessDataResult<InvoiceListDto>();
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

	@Override
	public DataResult<InvoiceListDto> makeOutInvoice(int rentalId) {
		var invoiceListDto = new InvoiceListDto();
		var rental = this.rentalService.getRentalById(rentalId).getData();
		var customerId = rental.getCustomer().getId();
		var car = this.rentalService.getCarByRentalId(rentalId).getData();
		
		invoiceListDto.setAdditionalServices(this.additionalServiceService.getAdditionalServicesByRentalId(rentalId).getData());
		invoiceListDto.setInvoiceDate(LocalDate.now());
		invoiceListDto.setRental(rental);
		invoiceListDto.setCustomer(this.individualCustomerService.getById(customerId).getData());
		invoiceListDto.setCar(car);
		
		return new SuccessDataResult<InvoiceListDto>(invoiceListDto);
	}
	
	
}
