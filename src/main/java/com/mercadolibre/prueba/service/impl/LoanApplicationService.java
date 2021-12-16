package com.mercadolibre.prueba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.converter.impl.LoanApplicationConverter;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;
import com.mercadolibre.prueba.service.ILoanApplicationService;

import static com.mercadolibre.prueba.util.Utils.calculateMonthlyFee;
import static com.mercadolibre.prueba.util.Utils.generateHash;

@Service
public class LoanApplicationService implements ILoanApplicationService{
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public FeeDTO registerLoanApplication(LoanApplicationDTO dto) {
		LoanApplicationConverter converter = new LoanApplicationConverter();
		LoanApplication payload = converter.fromEntity(dto);
		Double monthlyFee = calculateMonthlyFee(payload);
		payload.setInstallment(monthlyFee);
		payload.setLoanId(generateHash());
		loanApplicationRepository.save(payload);
		return new FeeDTO(payload.getLoanId(),monthlyFee);
	}

}
