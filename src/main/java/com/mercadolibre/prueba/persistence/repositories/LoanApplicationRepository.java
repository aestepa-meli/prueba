package com.mercadolibre.prueba.persistence.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadolibre.prueba.model.LoanApplication;

/**
 * <p>LoanApplicationRepository</p>
 * Repositorio (capa de persistencia) de las solicitudes de pago
 * @author Jhon
 *
 */
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long>{
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return Lista de solicitudes de prestamo entre el rango de fecha indicado
	 */
	@Query("from LoanApplication lap where lap.date between :from and :to")
	List<LoanApplication> findLoanAplicationsBetweenDates(@Param("from")Date from,@Param("to")Date to);

	
	/**
	 * 
	 * @param loanId
	 * @return la solicitud de prestamo asociada al id indicado
	 */
	LoanApplication findByLoanId(String loanId);
}
