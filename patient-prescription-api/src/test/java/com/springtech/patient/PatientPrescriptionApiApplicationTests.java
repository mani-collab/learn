package com.springtech.patient;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtech.patient.entity.Patient;
import com.springtech.patient.model.Response;
import com.springtech.patient.service.PatientService;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")

class PatientPrescriptionApiApplicationTests {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	@MockBean
	PatientService patientService;
	ObjectMapper objectMapper = new ObjectMapper();
	Patient patient = null;
	Response response = null;
	String jsonRequest;
	String jsonResponse;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation)
			throws JsonProcessingException {
		patient = new Patient(4, "Thomas", "Watson", "M", 22, null);
		response = new Response("0 is inserted", Boolean.TRUE);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
				.alwaysDo(MockMvcRestDocumentation.document("{method-name}", Preprocessors.preprocessRequest(),
						Preprocessors.preprocessResponse(ResponseModifyingPreprocessors.replaceBinaryContent(),
								ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
								Preprocessors.prettyPrint())))
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(8080).and().snippets()
						.withDefaults(CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(),
								HttpDocumentation.httpResponse(), AutoDocumentation.requestFields(),
								AutoDocumentation.responseFields(), AutoDocumentation.pathParameters(),
								AutoDocumentation.requestParameters(), AutoDocumentation.description(),
								AutoDocumentation.methodAndPath(), AutoDocumentation.section()))
				.build();
		jsonRequest = objectMapper.writeValueAsString(patient);
		jsonResponse = objectMapper.writeValueAsString(response);
	}

	@Test
	public void addPatientTest() throws Exception {
		mockMvc.perform(post("/api/addPatient/").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(jsonResponse));

	}

	@Test
	public void getPatientTest() throws Exception {
		System.out.println("Json Req is :" + jsonRequest);
		mockMvc.perform(get("/api/patients/22").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
		// .andExpect(MockMvcResultMatchers.content().json(jsonRequest));
	}
}
