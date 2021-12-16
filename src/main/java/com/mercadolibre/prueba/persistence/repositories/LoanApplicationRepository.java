package com.mercadolibre.prueba.persistence.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadolibre.prueba.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long>{
	
	@Query("from LoanApplication lap where lap.date between :from and :to")
	List<LoanApplication> findLoanAplicationsBetweenDates(@Param("from")Date from,@Param("to")Date to);

}
