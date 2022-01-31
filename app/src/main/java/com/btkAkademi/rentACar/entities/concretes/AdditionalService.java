package com.btkAkademi.rentACar.entities.concretes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "additional_services")
public class AdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="service_name")
	private String serviceName;
		
	@Column(name="description")
	private String description;
	
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@Column(name="price")
	private double price;
	
	@Column(name = "service_point")
	private byte servicePoint;
		
	@ManyToMany(mappedBy = "additionalServices")
    private List<Rental> rentals = new ArrayList<>();
	
}
