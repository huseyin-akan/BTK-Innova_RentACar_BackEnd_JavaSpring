package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer>{
	
}
