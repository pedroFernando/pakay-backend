package ec.com.pakay.util;

public class Constant {

    //JWT
    public static final String JWT_CLAIM_AUTHORITIES = "authorities";
    public static final String JWT_CLAIM_ID_EMP = "emp";
    public static final String JWT_CLAIM_ID_USER = "usr";
    public static final String JWT_CLAIM_USERNAME = "username";

    public static final String AUTHORIZATION_BAERER = "Bearer ";

    //Valores que se pasan por Header
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_APP = "App";
    public static final String HEADER_ORIGEN = "Origen";

    //Estados de registros
    public static final String ESTADO_ACTIVO = "A";
    public static final String ESTADO_INACTIVO = "I";
    public static final String ESTADO_ELIMINADO = "E";

    //Items de Catalogo
    public static final String CI_TIPO_IDENT_COD_RUC = "04";
    public static final String CI_TIPO_IDENT_COD_CEDULA = "05";
    public static final String CI_TIPO_IDENT_COD_PASAPORTE = "06";
    public static final String CI_TIPO_IDENT_COD_COSUMIDOR_FINAL = "07";
    public static final String CI_TIPO_IDENT_COD_EXTERIOR = "08";

}
