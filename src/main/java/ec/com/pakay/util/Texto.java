package ec.com.pakay.util;

public class Texto {
	/**
     *Reemplaza los caracteres especiales por la nomenclatura de html
     * @param strTexto
     * @return
     */
    public static String reemplazarHtml(String strTexto){
        strTexto = strTexto.replace("á", "&aacute;");
        strTexto = strTexto.replace("é", "&eacute;");
        strTexto = strTexto.replace("í", "&iacute;");
        strTexto = strTexto.replace("ó", "&oacute;");
        strTexto = strTexto.replace("ú", "&uacute;");
        strTexto = strTexto.replace("Á", "&Aacute;");
        strTexto = strTexto.replace("É", "&Eacute;");
        strTexto = strTexto.replace("Í", "&Iacute;");
        strTexto = strTexto.replace("Ó", "&Oacute;");
        strTexto = strTexto.replace("Ú", "&Uacute;");
        strTexto = strTexto.replace("ñ", "&ntilde;");
        strTexto = strTexto.replace("Ñ", "&Ntilde;");
        return strTexto;
    }
    
    public static String reemplazarXml(String strTexto){
//        strTexto = strTexto.replace("<", "");
//        strTexto = strTexto.replace(">", "");
//        strTexto = strTexto.replace("&", "&amp;");
        return strTexto;
    }
    
    public static String rellenarCeros(int Longitud, String Cadena){
        if(Cadena.length() >0) {
            Cadena =  new String(new char[Longitud - Cadena.length()]).replace("\0", "0") + Cadena;
        }
        return Cadena;
    }
    
    public static String OcultarCaracteres(String Cadena){
        if(Cadena.length()%2==1){
            return Cadena.replaceAll(Cadena.substring(0, (Cadena.length()+1)/2), new String(new char[(Cadena.length()+1)/2]).replace("\0", "X"));
        }else{
            return Cadena.replaceAll(Cadena.substring(0, (Cadena.length()+1)/2), new String(new char[Cadena.length()/2]).replace("\0", "X"));
        }
    }
    
    public static String capital(String texto){
        texto = texto.trim();
        String nuevacadena = "";
        for (String palabra : texto.split(" "))
            if(palabra.length() == 1)
                nuevacadena += palabra.toUpperCase();
            else if(palabra.length() > 1 )
                nuevacadena += palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase() + " ";
        return nuevacadena.trim();
    }
}
