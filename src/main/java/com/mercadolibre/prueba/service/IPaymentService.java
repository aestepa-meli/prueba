package com.mercadolibre.prueba.service;

import java.util.Date;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.exception.ControlException;

public interface IPaymentService {
	
	public void resiterPayment(PaymentDTO dto) throws ControlException;
	
	public BalanceDTO getBalance(Date date,String loanId) throws ControlException;

}
