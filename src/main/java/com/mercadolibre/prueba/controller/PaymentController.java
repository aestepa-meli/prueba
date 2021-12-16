package com.mercadolibre.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.service.IPaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private IPaymentService paymentService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerPayment(@RequestBody PaymentDTO dto) {
		paymentService.resiterPayment(dto);
		return new ResponseEntity<>("Saved",HttpStatus.OK);
	}

}
