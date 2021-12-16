package com.mercadolibre.prueba.service.impl;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplication;
import static com.mercadolibre.prueba.util.BuilderTestUtil.newPaymentDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.model.Payment;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;
import com.mercadolibre.prueba.persistence.repositories.PaymentRepository;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
	
	@Mock
	private LoanApplicationRepository loanApplicationRepository;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@InjectMocks
	private PaymentService paymentService;

	private LoanApplication loanApplication;
	private PaymentDTO paymentDTO;
	
	@Before
	public void setUp() {
		paymentDTO = newPaymentDTO();
		loanApplication = newLoanApplication();
	}
	
	@Test
	public void registerPaymentOK() throws ControlException {
		
		paymentService.resiterPayment(paymentDTO);
		
		Mockito.verify(loanApplicationRepository).findByLoanId(loanApplication.getLoanId());
		Mockito.verify(paymentRepository).save(Mockito.any(Payment.class));
	}
	
	@Test
	public void getBalance() throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18");
		when(paymentRepository.balance(date, "xckSmealjY1639631149838")).thenReturn(40.0);
		BalanceDTO result = paymentService.getBalance(date, "xckSmealjY1639631149838");
		Double expected = 40.0;
		assertEquals(result.getBalance(),expected);
	}
	
	@Test
	public void getBalanceNull() throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18");
		when(paymentRepository.balance(date, "xckSmealjY1639631149838")).thenReturn(null);
		BalanceDTO result = paymentService.getBalance(date, "xckSmealjY1639631149838");
		Double expected = 0.0;
		assertEquals(result.getBalance(),expected);
	}

}
