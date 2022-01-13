package com.btkAkademi.rentACar.business.requests.brandRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.requests.IRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest implements IRequest{	
	
	private int id;
	
	@NotBlank
	@NotNull
	@Size(min=3, max=20, message = Messages.INVALIDBRANDSIZE)
	private String name;
}
