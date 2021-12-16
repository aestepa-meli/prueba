package com.mercadolibre.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.service.ILoanApplicationService;

@RestController
@RequestMapping("/loanAplication")
public class LoanApplicationController {
	
	@Autowired
	private ILoanApplicationService loanApplicationService;
	
	
	@PostMapping("/register")
	public ResponseEntity<FeeDTO> registerLoanApplication(@RequestBody LoanApplicationDTO loanApplication) {
		return new ResponseEntity<FeeDTO>(loanApplicationService.registerLoanApplication(loanApplication), HttpStatus.OK);
	}

}
