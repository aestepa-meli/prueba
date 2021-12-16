package com.mercadolibre.prueba.service;

import java.util.Date;
import java.util.List;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.exception.ControlException;

public interface ILoanApplicationService {
	
	public FeeDTO registerLoanApplication(LoanApplicationDTO dto) throws ControlException;
	public List<LoanApplicationDTO> getLoanApplications(Date from, Date to) throws ControlException;

}
