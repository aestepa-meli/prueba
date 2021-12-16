package com.mercadolibre.prueba.service.impl;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplicationDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {
	
	@Mock
	private LoanApplicationRepository loanApplicationRepository;
	
	@InjectMocks
	private LoanApplicationService loanApplicationService;
	
	private LoanApplicationDTO loanApplicationDTO;
	
	@Before
	public void setUp() {
		loanApplicationDTO = newLoanApplicationDTO();
	}
	
	@Test
	public void registerLoanApplicationTestOk() {
		Double expectedResult = 85.61;
		FeeDTO result =loanApplicationService.registerLoanApplication(loanApplicationDTO);
		assertNotNull(result.getLoanId());
		assertEquals(result.getInstallment(),expectedResult);
	}
	
	@Test(expected = NullPointerException.class)
	public void registerLoanApplicationTestEmpyTerm() {
		loanApplicationDTO.setTerm(null);
		loanApplicationService.registerLoanApplication(loanApplicationDTO);
	}

}
