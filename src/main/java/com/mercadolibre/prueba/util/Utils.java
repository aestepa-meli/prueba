package com.mercadolibre.prueba.util;

import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.model.LoanApplication;
import static java.lang.Math.pow;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.RandomStringUtils;
public class Utils {
	
	private Utils() {
		throw new UnsupportedOperationException("This class is an utility class");
	}
	
	
	public static Double calculateMonthlyFee(LoanApplication payload) {
		Double r = payload.getRate() / 12.0;
		BigDecimal bd = new BigDecimal(Double.toString((r + (r /(pow(1.0 + r,payload.getTerm())-1))) * payload.getAmount()));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public static String generateHash() {
		return RandomStringUtils.randomAlphabetic(10) + System.currentTimeMillis();
	}
	
	public static void throwIfTrue(boolean conditionValue, String message) throws ControlException {
		if(conditionValue) {
			throw new ControlException(message);
		}
	}

}
