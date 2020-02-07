package com.springtech.patient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtech.patient.entity.Patient;
import com.springtech.patient.entity.Prescription;
import com.springtech.patient.model.Response;
import com.springtech.patient.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@PostMapping("/addPatient")
	public Response addPatient(@RequestBody Patient patient) {
		int pid = patientService.addPatient(patient);
		return new Response(pid+" is inserted",Boolean.TRUE);
	}
	
	@GetMapping("/patients")
	public List<Patient> getPatients() {

		List<Patient> patientList = new ArrayList<Patient>();
		patientList = patientService.getAllPatients();
		return patientList;
	}

	@GetMapping("/patients/{patientId}")
	public Patient getPatient(@PathVariable String patientId) {
		return patientService.getPatientInfo(patientId);
	}
	
	@GetMapping("/patients/{patientId}/prescription/{prescriptionId}")
	public Patient getPatient(@PathVariable("patientId") String patientId, @PathVariable("prescriptionId") String prescriptionId) {
		Patient patient = new Patient();
		List<Prescription> presList = new ArrayList<Prescription>();
		patient = patientService.getPatientInfo(patientId);
		presList = patient.getPrescription();
		System.out.println("++++++++++++ prescriptionId is :"+prescriptionId);
		System.out.println("+++++++ Before Filter +++++++");
		presList.forEach(System.out::println);
		Stream<Prescription> presStream = presList.stream();
		presList = presStream.filter(line ->prescriptionId.equals(Integer.toString(line.getPid()))).collect(Collectors.toList());
		System.out.println("+++++++ After Filter +++++++");
		presList.forEach(System.out::println);
		patient.setPrescription(presList);
		return  patient;
	}

}
