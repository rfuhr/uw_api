package br.com.ultraworks.erp.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalHelper {

	public static String toString(BigDecimal valor, int casasDecimais) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(casasDecimais);
        df.setGroupingUsed(false);
        return df.format(valor);
    }
}
