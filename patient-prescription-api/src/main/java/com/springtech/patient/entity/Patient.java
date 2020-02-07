package com.springtech.patient.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

public class Patient {
	@Id
	@GeneratedValue
	/**
	 * Patient Identification
	 */
	private int id;
	/**
	 * Patient First Name
	 */
	private String fName;
	/**
	 * Patient Last Name
	 */	
	private String lName;
	/**
	 * Patient Gender
	 */
	private String sex;
	/**
	 * Patient Age
	 */
	private int age;
	@OneToMany(targetEntity = Prescription.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ppr_fk", referencedColumnName = "id")
	/**
	 * Patient Prescription Details
	 */
	private List<Prescription> prescription;

}
