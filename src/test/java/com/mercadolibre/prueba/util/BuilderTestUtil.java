package com.mercadolibre.prueba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.model.LoanApplication;

import static com.mercadolibre.prueba.util.Utils.generateHash;
import static com.mercadolibre.prueba.util.Utils.calculateMonthlyFee;

public class BuilderTestUtil {
	
	public static LoanApplication newLoanApplication() {
		LoanApplication obj = new LoanApplication();
		try {
			
			obj.setAmount(1000L);
			obj.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18"));
			obj.setTerm(12L);
			obj.setRate(0.05);
		} catch (ParseException e) {
			return null;
		}
		return obj;
	}
	
	public static LoanApplicationDTO newLoanApplicationDTO() {
		LoanApplicationDTO obj = new LoanApplicationDTO();
		try {
			obj.setAmount(1000L);
			obj.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18"));
			obj.setTerm(12L);
			obj.setRate(0.05);
		} catch (ParseException e) {
			return null;
		}
		return obj;
	}
	
	public static FeeDTO newFeeDTO() {
		LoanApplication ap = newLoanApplication();
		return new FeeDTO(generateHash(),calculateMonthlyFee(ap));
	}
}
