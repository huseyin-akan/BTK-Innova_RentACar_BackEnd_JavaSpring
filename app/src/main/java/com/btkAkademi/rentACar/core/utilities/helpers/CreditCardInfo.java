package com.btkAkademi.rentACar.core.utilities.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInfo {
	String creditCard;
	String validDate;
	String cVC;
	String password;
}
