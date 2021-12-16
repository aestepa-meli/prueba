package com.mercadolibre.prueba.service;

import java.util.Date;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.exception.ControlException;

/**
 * <p>IPaymentService</p>
 * Interfaz que indica que servicios rfealizaran los pagos
 * @author Jhon
 *
 */
public interface IPaymentService {
	
	/**
	 * Registro de pago
	 * @param dto
	 * @throws ControlException
	 */
	public void resiterPayment(PaymentDTO dto) throws ControlException;
	
	/**
	 * Consulta del balance de la deuda
	 * @param date
	 * @param loanId
	 * @return Balance de la deuda
	 * @throws ControlException
	 */
	public BalanceDTO getBalance(Date date,String loanId) throws ControlException;

}
