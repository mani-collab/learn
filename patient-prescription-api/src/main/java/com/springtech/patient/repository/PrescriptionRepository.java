package com.springtech.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtech.patient.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

}
