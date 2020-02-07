package com.springtech.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtech.patient.controller.PatientController;
import com.springtech.patient.entity.Patient;
import com.springtech.patient.model.Response;
import com.springtech.patient.service.PatientService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)

class PatientPrescriptionApiApplicationBackUpTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	PatientService patientService;
	ObjectMapper om = new ObjectMapper();

	@Test
	public void addPatientTest() throws Exception {
		// prepare data and mock's behaviour
		Patient patient = new Patient();
		patient.setAge(22);
		patient.setFName("Thomas");
		patient.setLName("Watson");
		patient.setSex("M");
		patient.setPrescription(null);
		when(patientService.addPatient(any(Patient.class))).thenReturn(patient.getId());

		String jsonRequest = om.writeValueAsString(patient);
		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/addPatient/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonRequest))
				.andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		assertEquals(Boolean.TRUE, response.isCode());

	}
	
	@Test
	public void getPatientTest() throws Exception {
		Patient patient = new Patient();
		patient.setAge(22);
		patient.setFName("Thomas");
		patient.setLName("Watson");
		patient.setSex("M");
		patient.setPrescription(null);
		when(patientService.getPatientInfo("22")).thenReturn(patient);
		
		String jsonRequest = om.writeValueAsString(patient);
		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/patients/22")
				.accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Patient response = om.readValue(resultContent, Patient.class);
		assertEquals("Thomas", response.getFName());
	}
}
