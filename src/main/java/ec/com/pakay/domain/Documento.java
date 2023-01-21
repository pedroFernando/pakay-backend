package ec.com.pakay.domain;

import javax.persistence.*;

@Entity
@Table(name = "documento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento")
	private String id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "tipo")
	private String tipo;

	@Column(name = "secuencial")
	private Integer secuencial;

	public Documento() {
	}

	public Documento(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", nombre:" + nombre + ", tipo:" + tipo + "}";
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}

}
