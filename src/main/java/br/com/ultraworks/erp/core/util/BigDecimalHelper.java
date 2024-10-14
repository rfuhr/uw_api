package br.com.ultraworks.erp.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigDecimalHelper {

	public static String toString(BigDecimal valor, int casasDecimais) {
		if (valor == null) {
			valor = BigDecimal.ZERO;
		}
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		symbols.setDecimalSeparator('.');

		DecimalFormat df = new DecimalFormat();
		df.setDecimalFormatSymbols(symbols);
		df.setMaximumFractionDigits(casasDecimais);
		df.setMinimumFractionDigits(casasDecimais);
		df.setGroupingUsed(false);
		return df.format(valor);
	}
	
	public static String toCurrencyString(BigDecimal valor) {
        if (valor == null) {
            valor = BigDecimal.ZERO;
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        symbols.setGroupingSeparator('.');  
        symbols.setDecimalSeparator(',');  

        DecimalFormat df = new DecimalFormat("¤ #,##0.00", symbols); 

        return df.format(valor);
    }
}
