package ec.com.pakay.domain;

import javax.persistence.*;

@Entity
@Table(name = "parametro")
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_parametro")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;
	
	public Parametro() {
	}

	public Parametro(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
