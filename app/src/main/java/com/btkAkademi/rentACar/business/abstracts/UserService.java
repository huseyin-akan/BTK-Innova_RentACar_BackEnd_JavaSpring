package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.User;

public interface UserService {
	Result checkIfUserExistByEmail(String email);
	DataResult<User> getByMail(String mail);
}
