package com.mercadolibre.prueba.service;

import java.util.Date;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;

public interface IPaymentService {
	
	public void resiterPayment(PaymentDTO dto);
	
	public BalanceDTO getBalance(Date date,String loanId);

}
