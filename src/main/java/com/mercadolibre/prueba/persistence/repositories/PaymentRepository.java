package com.mercadolibre.prueba.persistence.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadolibre.prueba.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Long>{
	
	@Query(nativeQuery = true,
		 value = "Select loa.amount-SUM(p.amount) "+
	             "from payment p inner join loan_aplication loa " +
				 "ON p.id_loan_application = loa.id "+
	             "where payment_type = 'MADE' and p.date <= :date and loa.loan_id = :loanId " +
				 "Group by loa.amount")
	Double balance(@Param("date") Date date, @Param("loanId") String loanId);

}
