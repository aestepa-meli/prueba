package com.mercadolibre.prueba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mercadolibre.prueba.controller.dto.LoanApplicationDTO;
import com.mercadolibre.prueba.controller.dto.PaymentDTO;
import com.mercadolibre.prueba.controller.dto.response.BalanceDTO;
import com.mercadolibre.prueba.controller.dto.response.FeeDTO;
import com.mercadolibre.prueba.model.LoanApplication;
import com.mercadolibre.prueba.model.Payment;
import com.mercadolibre.prueba.model.enumerator.PaymentType;

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
			obj.setLoanId("xckSmealjY1639631149838");
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
			obj.setLoanId("xckSmealjY1639631149838");
		} catch (ParseException e) {
			return null;
		}
		return obj;
	}
	
	public static FeeDTO newFeeDTO() {
		LoanApplication ap = newLoanApplication();
		return new FeeDTO(generateHash(),calculateMonthlyFee(ap));
	}
	
	
	public static Payment newPayment() {
		Payment obj = new Payment();
		try {
			obj.setAmount(85.60);
			obj.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18"));
			obj.setLoanApplication(newLoanApplication());
			obj.setPaymentType(PaymentType.MADE);
		} catch (ParseException e) {
			return null;
		}
		return obj;
	}
	
	public static PaymentDTO newPaymentDTO() {
		PaymentDTO obj = new PaymentDTO();
		try {
			obj.setAmount(85.60);
			obj.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-08-05 02:18:18"));
			obj.setLoanId("xckSmealjY1639631149838");
			obj.setPaymentType(PaymentType.MADE);
		} catch (ParseException e) {
			return null;
		}
		return obj;
	}
	
	public static BalanceDTO newBalanceDTO() {
		BalanceDTO obj = new BalanceDTO();
		obj.setBalance(40.0);
		return obj;
	}
}
