package com.mercadolibre.prueba.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.service.ILoanApplicationService;


/**
 * <p>LoanApplicationController</p>
 * Controlador encargado de los procesos asociados a Loan Aplication.
 * @author Jhon
 *
 */
@RestController
@RequestMapping("/loanAplication")
public class LoanApplicationController {
	
	public static final Logger LOG = LoggerFactory.getLogger(LoanApplicationController.class);
	
	@Autowired
	private ILoanApplicationService loanApplicationService;
	
	/**
	 * <p>Registro de solicitudes d eprestamo</p>
	 * @param loanApplication
	 * @return Retorna el registro almacenado con identificador unico y su cuota mensual
	 * @throws ControlException
	 */
	@PostMapping("/register")
	public ResponseEntity<FeeDTO> registerLoanApplication(@RequestBody LoanApplicationDTO loanApplication) throws ControlException {
		LOG.info("Recived Loan Application {} ", loanApplication.getDate());
		return new ResponseEntity<>(loanApplicationService.registerLoanApplication(loanApplication), HttpStatus.OK);
	}
	/**
	 * <p>Lista de solicitudes de prestamo en un rango indicado</p>
	 * @param from (Fecha desde)
	 * @param to (Fecha hasta)
	 * @return Retorna un listado de las solicitudes de prestamo
	 * @throws ControlException
	 */
	@GetMapping("/list")
	public ResponseEntity<List<LoanApplicationDTO>> listLoanApplication(@RequestParam @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss") Date from, 
			                                                            @RequestParam @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss") Date to) throws ControlException {
		LOG.info("List Loan Application between {} and {}", from,to);
		return new ResponseEntity<>(loanApplicationService.getLoanApplications(from, to), HttpStatus.OK);
	}

}
