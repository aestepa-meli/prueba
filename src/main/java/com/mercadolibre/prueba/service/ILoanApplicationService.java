package com.mercadolibre.prueba.service;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;

public interface ILoanApplicationService {
	
	FeeDTO registerLoanApplication(LoanApplicationDTO dto);

}
