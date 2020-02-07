package com.springtech.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication 
public class PatientPrescriptionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientPrescriptionApiApplication.class, args);
	}

}
