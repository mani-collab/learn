package com.springtech.patient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Prescription {
	@Id
	@GeneratedValue
	/**
	 * Prescription Identification
	 */
	private int pid;
	/**
	 * Medicine Generic Name
	 */	
	private String medicineName;
	/**
	 * Medicine form Liquid/Tablets
	 */
	private String form;
	/**
	 * Amount Medicine Quantity to intake
	 */	
	private int quantity;
	/**
	 * Medicine Dosage
	 */	
	private int dosage;
	/**
	 * Refill
	 */	
	private boolean refill;

	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "ppr_fk") private Patient patient;
	 */

}
