package com.mercadolibre.prueba.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.service.IPaymentService;

/**
 * <p>PaymentController</p>
 * Controlador encargadod e los procesos de Pago
 * @author Jhon
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	public static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private IPaymentService paymentService;
	
	/**
	 * <p>Registo de Pago</p>
	 * @param PaymentDTO
	 * @return Un status indicando si la operacion se realizo correctamente
	 * @throws ControlException
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerPayment(@RequestBody PaymentDTO dto) throws ControlException {
		LOG.info("Recived payment status:{}",dto.getPaymentType());
		paymentService.resiterPayment(dto);
		return new ResponseEntity<>("Saved",HttpStatus.OK);
	}
	
	/**
	 * <p>Consulta la deuda pendiente hasta una fecha</p>
	 * @param loanId id unico de prestamo
	 * @param date
	 * @return El balance generado hasta la fecha indicada
	 * @throws ControlException
	 */
	@GetMapping("/pendingDebt/{loanId}")
	public ResponseEntity<BalanceDTO> getPendingDebt(@PathVariable("loanId") String loanId, @RequestParam @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss") Date date ) throws ControlException {
		LOG.info("Recived pending debt date:{}",date);
		return new ResponseEntity<>(paymentService.getBalance(date,loanId),HttpStatus.OK);
	}

}
