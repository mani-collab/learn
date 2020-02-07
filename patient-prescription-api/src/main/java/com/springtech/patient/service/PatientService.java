package com.springtech.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtech.patient.entity.Patient;
import com.springtech.patient.repository.PatientRepository;
@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}


	public Patient getPatientInfo(String patientId) {
	 return patientRepository.findById(Integer.parseInt(patientId)).get();
	}

	public int addPatient(Patient patient) {
		patientRepository.save(patient);
		return patient.getAge();
	}
	

}
