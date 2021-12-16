package com.mercadolibre.prueba.util;

import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.model.LoanApplication;
import static java.lang.Math.pow;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * <p>Utils</p>
 * Clase de Utilidades
 * @author Jhon
 *
 */
public class Utils {
	
	/**
	 * Constructor privado pues no se deben crear instancias de esta clase
	 */
	private Utils() {
		throw new UnsupportedOperationException("This class is an utility class");
	}
	
	/**
	 * 
	 * @param payload
	 * @return retorna el valor de la cuota mensual
	 */
	public static Double calculateMonthlyFee(LoanApplication payload) {
		Double r = payload.getRate() / 12.0;
		BigDecimal bd = new BigDecimal(Double.toString((r + (r /(pow(1.0 + r,payload.getTerm())-1))) * payload.getAmount()));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	/**
	 * 
	 * @return un hash unico el cual sera utilizado como clave alternativa en consultas
	 */
	public static String generateHash() {
		return RandomStringUtils.randomAlphabetic(10) + System.currentTimeMillis();
	}
	
	/**
	 * Metodo para el control de errores, lanza una excepcion si la condicion de entrada es verdadera, la cual sera capturada posteriormente
	 * @param conditionValue
	 * @param message
	 * @throws ControlException
	 */
	public static void throwIfTrue(boolean conditionValue, String message) throws ControlException {
		if(conditionValue) {
			throw new ControlException(message);
		}
	}

}
