package com.mercadolibre.prueba.service.impl;

import static com.mercadolibre.prueba.util.Utils.calculateMonthlyFee;
import static com.mercadolibre.prueba.util.Utils.generateHash;
import static com.mercadolibre.prueba.util.Utils.throwIfTrue;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.converter.impl.LoanApplicationConverter;
import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;
import com.mercadolibre.prueba.service.ILoanApplicationService;

@Service
public class LoanApplicationService implements ILoanApplicationService{
	public static final Logger LOG = LoggerFactory.getLogger(LoanApplicationService.class);
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public FeeDTO registerLoanApplication(LoanApplicationDTO dto) throws ControlException {
		LOG.info("Validate LoanApplication");
		validateLoanDTO(dto);
		LoanApplicationConverter converter = new LoanApplicationConverter();
		LoanApplication payload = converter.fromEntity(dto);
		LOG.info("Generate Monthly Fee");
		Double monthlyFee = calculateMonthlyFee(payload);
		payload.setInstallment(monthlyFee);
		LOG.info("Generate Unique Hash");
		payload.setLoanId(generateHash());
		LOG.info("Saving Loan application ...");
		loanApplicationRepository.save(payload);
		LOG.info("Saved Loan application ..!");
		return new FeeDTO(payload.getLoanId(),monthlyFee);
	}

	@Override
	public List<LoanApplicationDTO> getLoanApplications(Date from, Date to) throws ControlException {
		throwIfTrue(from ==null , "El parametro form es obligatorio");
		throwIfTrue(to ==null , "El parametro to es obligatorio");
		LoanApplicationConverter converter = new LoanApplicationConverter();
		LOG.info("Getting loan apllications");
		return loanApplicationRepository.findLoanAplicationsBetweenDates(from, to).stream()
		.map(loan -> converter.fromDto(loan)).collect(Collectors.toList());
	}

	
	protected void validateLoanDTO(LoanApplicationDTO dto) throws ControlException {
		throwIfTrue(dto.getAmount() == null , "El campo Ammount es obligatorio");
		throwIfTrue(dto.getDate() == null , "El campo Date es obligatorio");
		throwIfTrue(dto.getRate() == null , "El campo Rate es obligatorio");
		throwIfTrue(dto.getTerm() == null, "El campo Term es obligatorio");
	}
}
