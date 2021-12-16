package com.mercadolibre.prueba.service.impl;

import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplication;
import static com.mercadolibre.prueba.util.BuilderTestUtil.newLoanApplicationDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {
	
	@Mock
	private LoanApplicationRepository loanApplicationRepository;
	
	@InjectMocks
	private LoanApplicationService loanApplicationService;
	
	private LoanApplicationDTO loanApplicationDTO;
	private LoanApplication loanApplication;
	
	
	@Before
	public void setUp() {
		loanApplicationDTO = newLoanApplicationDTO();
		loanApplication = newLoanApplication();
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
	@Test
	public void getLoanApplicationsOk() throws ParseException {
		List<LoanApplication> list = Arrays.asList(loanApplication); 
		List<LoanApplicationDTO> list2 = new ArrayList<>();
		list2.add(loanApplicationDTO); 
		Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18");
		Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18");
		when(loanApplicationRepository.findLoanAplicationsBetweenDates(from, to)).thenReturn(list);
		List<LoanApplicationDTO> result = loanApplicationService.getLoanApplications(from, to);
		assertNotNull(result);
		assertEquals(result.get(0).getLoanId(),list2.get(0).getLoanId());
	}

}
