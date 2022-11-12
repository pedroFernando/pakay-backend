package ec.com.pakay.util;

public class Respuesta {

	public static final String OK = "100000";
	public static final String ERROR_REGISTRO_DUPLICADO = "100001";
	public static final String ERROR_REGISTRO_NO_ENCONTRADO = "100002";
	public static final String ERROR_REGISTRO_EN_USO = "100003";
	public static final String ERROR_INTERNO = "100500";
	public static final String ERROR_CANTIDAD_NO_VALIDA = "101001";
	public static final String ERROR_CANTIDAD_NO_DISPONIBLE = "101002";

	private String codigo;
	private String mensaje;

	private Respuesta() {
		this.codigo = OK;
		this.mensaje = "";
	}

	public static Respuesta getInstance() {
		return new Respuesta();
	}

	public String getCodigo() {
		return codigo;
	}

	public Respuesta setCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Respuesta setMensaje(String mensaje) {
		this.mensaje = mensaje;
		return this;
	}

}
