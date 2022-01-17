package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;

@Repository
public interface CreditCardInfoDao extends JpaRepository<CreditCardInfo, Integer> {

}
