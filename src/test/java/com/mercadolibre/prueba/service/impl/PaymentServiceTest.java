package com.mercadolibre.prueba.service.impl;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplication;
import static com.mercadolibre.prueba.util.BuilderTestUtil.newPaymentDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
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
	public void registerPaymentOK() {
		
		paymentService.resiterPayment(paymentDTO);
		
		Mockito.verify(loanApplicationRepository).findByLoanId(loanApplication.getLoanId());
		Mockito.verify(paymentRepository).save(Mockito.any(Payment.class));
	}

}
