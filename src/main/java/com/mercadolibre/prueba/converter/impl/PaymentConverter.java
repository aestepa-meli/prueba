package com.mercadolibre.prueba.converter.impl;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.converter.IGenericConverter;
import com.mercadolibre.prueba.model.Payment;

/**
 * <p>PaymentConverter<p>
 * Converter de los pagos
 * @author Jhon
 *
 */
public class PaymentConverter implements IGenericConverter<Payment, PaymentDTO> {

	@Override
	public Payment fromEntity(PaymentDTO dto) {
		Payment entity = new Payment();
		entity.setAmount(dto.getAmount());
		entity.setDate(dto.getDate());
		entity.setPaymentType(dto.getPaymentType());
		return entity;
	}

	@Override
	public PaymentDTO fromDto(Payment entity) {
		PaymentDTO dto = new PaymentDTO();
		dto.setAmount(entity.getAmount());
		dto.setDate(entity.getDate());
		dto.setPaymentType(entity.getPaymentType());
		return dto;
	}

}
