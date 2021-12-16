package com.mercadolibre.prueba.controller;

import java.util.Date;

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

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private IPaymentService paymentService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerPayment(@RequestBody PaymentDTO dto) throws ControlException {
		paymentService.resiterPayment(dto);
		return new ResponseEntity<>("Saved",HttpStatus.OK);
	}
	
	@GetMapping("/pendingDebt/{loanId}")
	public ResponseEntity<BalanceDTO> getPendingDebt(@PathVariable("loanId") String loanId, @RequestParam @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss") Date date ) throws ControlException {
		return new ResponseEntity<>(paymentService.getBalance(date,loanId),HttpStatus.OK);
	}

}
