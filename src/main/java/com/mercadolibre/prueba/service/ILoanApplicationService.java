package com.mercadolibre.prueba.service;

import java.util.Date;
import java.util.List;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.exception.ControlException;

/**
 * <p>ILoanApplicationService</p>
 * Interfaz que indica que realizaran los servicios de solicitud de prestamo
 * @author Jhon
 *
 */
public interface ILoanApplicationService {
	/**
	 * 
	 * @param dto
	 * @return La informacion asociada a la solicitud de prestamo (id unico y cuota mensual)
	 * @throws ControlException
	 */
	public FeeDTO registerLoanApplication(LoanApplicationDTO dto) throws ControlException;
	/**
	 * 
	 * @param from
	 * @param to
	 * @return Lista de solicitudes de prestamo entre las fechas indicadas
	 * @throws ControlException
	 */
	public List<LoanApplicationDTO> getLoanApplications(Date from, Date to) throws ControlException;

}
