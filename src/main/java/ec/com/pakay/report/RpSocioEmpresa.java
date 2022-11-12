package ec.com.pakay.report;

public class RpSocioEmpresa {
	
	private String nombre;
	private Integer cantidad;
	private Double valor;
	
	public RpSocioEmpresa(String nombre, Integer cantidad, Double valor) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
    
}
