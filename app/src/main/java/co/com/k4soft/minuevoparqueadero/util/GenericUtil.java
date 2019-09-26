package co.com.k4soft.minuevoparqueadero.util;

import java.text.DecimalFormat;

public class GenericUtil {

    public static String  getDecimalFormat(int decimalAmount, Double decimalNumber){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(decimalAmount);
        return df.format(decimalNumber);
    }


    public static String formatoMiles(Double valor) {
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        return formateador.format(valor);
    }
}
