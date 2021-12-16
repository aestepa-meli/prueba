package com.mercadolibre.prueba.converter.impl;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.converter.IGenericConverter;
import com.mercadolibre.prueba.model.LoanApplication;

/**
 * <p>LoanApplicationConverter</p>
 * Converter de las solicitudes de prestamo
 * @author Jhon
 *
 */
public class LoanApplicationConverter implements IGenericConverter<LoanApplication, LoanApplicationDTO>{

	@Override
	public LoanApplication fromEntity(LoanApplicationDTO dto) {
		LoanApplication entity = new LoanApplication();
		entity.setAmount(dto.getAmount());
		entity.setDate(dto.getDate());
		entity.setRate(dto.getRate());
		entity.setTerm(dto.getTerm());
		return entity;
	}

	@Override
	public LoanApplicationDTO fromDto(LoanApplication entity) {
		LoanApplicationDTO dto = new LoanApplicationDTO();
		dto.setAmount(entity.getAmount());
		dto.setDate(entity.getDate());
		dto.setRate(entity.getRate());
		dto.setTerm(entity.getTerm());
		dto.setLoanId(entity.getLoanId());
		return dto;
	}
}
