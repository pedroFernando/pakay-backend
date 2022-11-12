package ec.com.pakay.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author eduardo
 */
public class Numeros {
    private static DecimalFormat decimalFormat;
    private static final DecimalFormatSymbols SIMBOLO = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
    public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    
    private static BigDecimal bigDecimal;
    /**
     *Da el formato a una cantidad con dos decimales 
     * Ejemplo:
     *          123.20
     *          0.05
     *          23.25
     * @param cantidad
     * @return
     */
    public static String getFormatoDecimales(Double cantidad){
        return getFormatoDecimales(cantidad, "#0.00");
    }
    
    /**
     * Da el formato a una cantidad dependiendo del formato que se envíe
     * comoparametro
     * @param cantidad
     * @param strFormato
     * @return
     */
    public static String getFormatoDecimales(Double cantidad, String strFormato){
        if(cantidad == null)
            return "0.0";
        decimalFormat = new DecimalFormat(strFormato, SIMBOLO);
        return decimalFormat.format(cantidad);
    }
    
    public static Double redondearDouble(Double valor, Integer digitos){
        if(valor == null)
            return 0.0;
        if(digitos == null)
            digitos = 2;
        bigDecimal = new BigDecimal(valor);
        bigDecimal = bigDecimal.setScale(digitos, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
    
    public static Double redondearDouble(Double valor){
        return redondearDouble(valor,2);
    }
    
}

