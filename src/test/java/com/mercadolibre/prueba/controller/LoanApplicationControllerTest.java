package com.mercadolibre.prueba.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.GsonBuilder;
import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.service.ILoanApplicationService;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplicationDTO;
import static com.mercadolibre.prueba.util.BuilderTestUtil.newFeeDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class LoanApplicationControllerTest {
	
	@Mock
	private ILoanApplicationService loanApplicationService;
	
	@InjectMocks
	private LoanApplicationController loanApplicationController;
	
	private MockMvc mockMvc;
	
	private GsonBuilder gsonBuilder;
	
	private LoanApplicationDTO loanApplicationDTO;
	
	private FeeDTO feeDto;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loanApplicationController).build();
		loanApplicationDTO = newLoanApplicationDTO();
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd HH:mm:ss");
		feeDto = newFeeDTO();
	}
	
	@Test
	public void registerLoanApplication() throws Exception {
		
		when(loanApplicationService.registerLoanApplication(Mockito.any(LoanApplicationDTO.class))).thenReturn(feeDto);
		
		mockMvc.perform(post("/loanAplication/register")
				.content(gsonBuilder.create().toJson(loanApplicationDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.installment", equalTo(85.61)));
	}
	
	
	@Test
	public void listLoanApplication() throws Exception {
		List<LoanApplicationDTO> list = new ArrayList<>();
		list.add(loanApplicationDTO); 
		
		when(loanApplicationService.getLoanApplications(Mockito.any(Date.class),Mockito.any(Date.class))).thenReturn(list);
		
		mockMvc.perform(get("/loanAplication/list")
				.queryParam("from", "2017-08-05 02:18:18")
				.queryParam("to", "2017-08-05 02:18:18"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

}
