package com.mercadolibre.prueba.controller;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newBalanceDTO;
import static com.mercadolibre.prueba.util.BuilderTestUtil.newPaymentDTO;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.GsonBuilder;
import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.service.IPaymentService;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {
	
	@Mock
	private IPaymentService paymentService;
	
	@InjectMocks
	private PaymentController paymentController;
	
	private MockMvc mockMvc;
	
	private GsonBuilder gsonBuilder;
	
	private PaymentDTO paymentDTO;
	
	private BalanceDTO balanceDTO;
	
	@Before
	public void setUp() {
		mockMvc =  MockMvcBuilders.standaloneSetup(paymentController).build();
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd HH:mm:ss");
		paymentDTO = newPaymentDTO();
		balanceDTO = newBalanceDTO();
	}
	
	@Test
	public void registerPayment() throws Exception {
		
		mockMvc.perform(post("/payment/register")
				.content(gsonBuilder.create().toJson(paymentDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());		
	}
	
	@Test
	public void getPendingDebt() throws Exception {
		when(paymentService.getBalance(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18"), "xckSmealjY1639631149838")).thenReturn(balanceDTO);
		mockMvc.perform(get("/payment/pendingDebt/{loanId}","xckSmealjY1639631149838")
				.queryParam("date", "2017-08-05 02:18:18"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.balance", equalTo(40.0)));		
	}

}
