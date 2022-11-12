package ec.com.pakay.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ec.com.pakay.domain.aut.Aplicacion;
import ec.com.pakay.domain.aut.UsuarioAuditoria;
import ec.com.pakay.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tabla")
	private String tabla;

	@Column(name = "entidad")
	private String entidad;

	@Column(name = "id_tabla")
	private Long idTabla;

	@Column(name = "accion")
	private String accion;

	@Column(name = "registro")
	private String registro;

	@Column(name = "ip")
	private String ip;

	@ManyToOne
	@JoinColumn(name = "cod_aplicacion")
	private Aplicacion aplicacion;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioAuditoria usuario;

	@Column(name = "fecha", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;

	public static final String CREATE = "C";
	public static final String DELETE = "D";
	public static final String UPDATE = "U";

	public Auditoria() {
	}

	public Auditoria(String tabla, String entidad, Long idTabla, String accion, String registro, String ip,
			Aplicacion aplicacion, UsuarioAuditoria usuario) {
		super();
		this.tabla = tabla;
		this.entidad = entidad;
		this.idTabla = idTabla;
		this.accion = accion;
		this.registro = registro;
		this.ip = ip;
		this.aplicacion = aplicacion;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Long getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(Long idTabla) {
		this.idTabla = idTabla;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public UsuarioAuditoria getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioAuditoria usuario) {
		this.usuario = usuario;
	}

}