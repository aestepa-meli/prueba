package com.mercadolibre.prueba.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadolibre.prueba.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long>{

}
