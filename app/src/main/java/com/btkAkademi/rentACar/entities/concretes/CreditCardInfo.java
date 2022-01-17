package com.btkAkademi.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="creditcardinfos")
public class CreditCardInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="credit_card")
	private String creditCard;
	
	@Column(name="valid_date")
	private String validDate;
	
	@Column(name="cvc")
	private String cVC;	
	
	@Column(name="card_holder")
	private String cardHolder;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
