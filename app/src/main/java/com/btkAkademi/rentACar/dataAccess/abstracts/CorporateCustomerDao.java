package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Repository
public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer>{
	Optional<CorporateCustomer> findByCompanyName(String companyName);
	Optional<CorporateCustomer> findByEmail(String email);
	int findTaxNumberById(int customerId);
}
