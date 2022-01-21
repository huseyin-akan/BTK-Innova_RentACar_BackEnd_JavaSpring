package com.btkAkademi.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.UserDao;
import com.btkAkademi.rentACar.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService{
	private final UserDao userDao;

	@Override
	public Result checkIfUserExistByEmail(String email) {
		var result = this.userDao.findByEmail(email);
		if(result.isEmpty()) {
			return new ErrorResult(Messages.USERNOTFOUND);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<User> getByMail(String mail) {
		var result = this.userDao.findByEmail(mail);
		if(result.isEmpty()) {
			return new ErrorDataResult(Messages.USERNOTFOUND);
		}
		return new SuccessDataResult<User>(result.get());
	}
}
