package com.mercadolibre.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mercadolibre.prueba.util.AuditingDateTimeProvider;
import com.mercadolibre.prueba.util.UsernameAuditorAware;


/**
 * </p>PersistenceConfig</p>
 * Configuracion de persistencia de la aplicacion
 * @author Jhon
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages =  "com.mercadolibre.prueba.model")
@EnableJpaRepositories("com.mercadolibre.prueba.persistence.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditingProvider", dateTimeProviderRef = "dateTimeProvider")
public class PersistenceConfig {
	
	/**
	 * 
	 * @return proveedor de auditoria para el usuario
	 */
	@Bean
	public UsernameAuditorAware auditingProvider() {
		return new UsernameAuditorAware();
	}
	
	/**
	 * 
	 * @return proveedor de auditoria para la fecha
	 */
	@Bean
	public AuditingDateTimeProvider dateTimeProvider() {
		return new AuditingDateTimeProvider();
	}
	
}
