package com.mercadolibre.prueba.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LOAN_APLICATION")
@EntityListeners(AuditingEntityListener.class)
public class LoanApplication extends AbstractEntity{

	private static final long serialVersionUID = 1600134338696073487L;
	@Id
	@Column(name = "ID",updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name ="LOAN_ID",nullable = false, unique = true)
	private String loanId;
	@Column(name = "INSTALLMENT")
	private Double installment;
	@Column(name="AMOUNT")
	private Long amount;
	@Column(name="TERM")
	private Long term;
	@Column(name="RATE")
	private Double rate;
	@Column(name="DATE")
	@DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
	private Date date;
	
	@OneToMany(mappedBy = "loanApplication")
	private Set<Payment> payments;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
}
