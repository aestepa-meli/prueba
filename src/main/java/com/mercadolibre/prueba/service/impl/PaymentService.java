package com.mercadolibre.prueba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.converter.impl.PaymentConverter;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.model.Payment;
import com.mercadolibre.prueba.persistence.repositories.LoanApplicationRepository;
import com.mercadolibre.prueba.persistence.repositories.PaymentRepository;
import com.mercadolibre.prueba.service.IPaymentService;

@Service
public class PaymentService implements IPaymentService{
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void resiterPayment(PaymentDTO dto) {
		PaymentConverter converter = new PaymentConverter();
		LoanApplication loan = loanApplicationRepository.findByLoanId(dto.getLoanId());
		Payment payment = converter.fromEntity(dto);
		payment.setLoanApplication(loan);
		paymentRepository.save(payment);
	}

}
