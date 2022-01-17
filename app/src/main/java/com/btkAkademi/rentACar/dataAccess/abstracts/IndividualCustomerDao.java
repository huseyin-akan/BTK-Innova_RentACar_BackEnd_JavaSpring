package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer>{
	Optional<IndividualCustomer> findByEmail(String mail);
	String findNationalIdById(int customerId);
	LocalDate findBirthDateById(int customerId);
}
