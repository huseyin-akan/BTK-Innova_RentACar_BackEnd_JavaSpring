package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.PromotionCode;

@Repository
public interface PromotionCodeDao extends JpaRepository<PromotionCode, Integer>{
	Optional<PromotionCode> findByCode(String code);
}
