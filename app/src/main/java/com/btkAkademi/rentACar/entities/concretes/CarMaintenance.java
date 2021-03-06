package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;

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
@Table(name="car_maintenance")
public class CarMaintenance {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;   

    @Column(name="maintenance_reason")
    private String maintenanceReason; 
    
    @Column(name="maintenance_result")
    private String maintenanceResult;

    @Column(name="start_date")
    private LocalDate startDate;   

    @Column(name="end_date")
    private LocalDate returnDate; 
    
    @ManyToOne
    @JoinColumn(name = "car_id" )
    private Car car;
}
