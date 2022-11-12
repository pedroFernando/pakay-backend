package ec.com.pakay.domain.aut;

import javax.persistence.*;

@Entity
@Table(name = "ist_autorizacion.au_usuario")
public class UsuarioAuditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private Integer id;

	@Column(name = "us_usuario")
	private String usuario;

	@Column(name = "us_nombre")
	private String nombre;

	@Column(name = "us_apellido")
	private String apellido;

	public UsuarioAuditoria() {
	}

	public UsuarioAuditoria(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
