package ec.com.pakay.domain.aut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ist_autorizacion.au_aplicacion")
@AllArgsConstructor
public class Aplicacion {

	@Id
	@Column(name = "ap_cod")
	private String cod;

	@Column(name = "ap_nombre")
	private String nombre;

	@JsonIgnore
	@Column(name = "ap_descripcion")
	private String descripcion;

	public Aplicacion() {
	}
	
	public Aplicacion(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
