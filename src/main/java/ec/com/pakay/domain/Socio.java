package ec.com.pakay.domain;

import javax.persistence.*;

@Entity
@Table(name = "socio")
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_socio")
	private Integer id;

	@Column(name = "id_empresa")
	private Integer idEmpresa;
	
	@Column(name = "cedula")
	private String cedula;
	
	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "tipo")
	private String tipo;
	
	public Socio() {
	}

	public Socio(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", idEmpresa:" + idEmpresa + ", cedula:" + cedula + ", nombres:" + nombres
				+ ", apellidos:" + apellidos + ", direccion:" + (direccion==null?"":direccion) + ", "
				+ "telefono:" + (telefono==null?"":telefono) + ", tipo:" + tipo +"}";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
