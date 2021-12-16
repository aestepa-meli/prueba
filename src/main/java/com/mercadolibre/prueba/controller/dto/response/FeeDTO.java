package com.mercadolibre.prueba.controller.dto.response;

public class FeeDTO {
	private String loanId;
	private Double installment;
	
	public FeeDTO() {
		//Default constructor
	}
	
	public FeeDTO(String loanId, Double installment) {
		super();
		this.loanId = loanId;
		this.installment = installment;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Double getInstallment() {
		return installment;
	}

	public void setInstallment(Double installment) {
		this.installment = installment;
	}
	
	
}
