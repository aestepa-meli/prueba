package com.mercadolibre.prueba.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.mercadolibre.prueba.config.api.ApiAuthenticationFilter;
import com.mercadolibre.prueba.config.api.ApiAuthorizationFilter;
import com.mercadolibre.prueba.security.RestAuthenticationEntrtPoint;
import com.mercadolibre.prueba.security.UserDetailsAuthenticationProvider;


/**
 * <p>SecurityConfig<p>
 * Clase de configuracion para la capa de seguridad
 * @author Jhon
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.mercadolibre.prueba.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private RestAuthenticationEntrtPoint restAuthenticationEntrtPoint;
	
	@Autowired
	private UserDetailsAuthenticationProvider provider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final String[] matchersPermitAll = { "/html/**", "/css/**", "/img/**", "/js/**", "/webjars/**"
				,"/webhook","/login","/v3/api-docs","/","/swagger-ui.html","/swagger-ui/*"
				,"/swagger-resources/**","/webjars/**","/swagger-ui/**", "/javainuse-openapi/**","/user/register"};


		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.headers().frameOptions().sameOrigin()
			.and()
				.csrf().disable().cors()
			.and()
				.authorizeRequests().antMatchers(matchersPermitAll).permitAll().anyRequest().authenticated()
			.and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntrtPoint)
			.and()
				.addFilter(new ApiAuthenticationFilter(authenticationManager(), "/login"))
				.addFilter(new ApiAuthorizationFilter(authenticationManager()));
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration confi = new CorsConfiguration();
		confi.setAllowCredentials(true);
		confi.setAllowedOrigins(Arrays.asList("*"));
		confi.addAllowedOrigin("*");
		confi.addAllowedHeader("*");
		confi.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","DELETE","PATCH"));
		source.registerCorsConfiguration("/**", confi);
		return new CorsFilter(source);
	}
}
