package com.mercadolibre.prueba.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadolibre.prueba.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Long>{

}
