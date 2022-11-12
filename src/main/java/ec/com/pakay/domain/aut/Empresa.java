package ec.com.pakay.domain.aut;

import ec.com.pakay.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ist_autorizacion.au_empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "em_id")
	private Integer id;

	@Column(name = "em_ruc")
	private String ruc;

	@Column(name = "em_razon_social")
	private String razonSocial;

	@Column(name = "em_nombre_comercial")
	private String nombreComercial;

	@Column(name = "em_dir_matriz")
	private String dirMatriz;

	@Column(name = "em_contribuyente_especial")
	private String contribuyenteEspecial;

	@Column(name = "em_obligado_contabilidad")
	private Boolean obligadoContabilidad;

	@Column(name = "em_ambiente")
	private String ambiente;

	@Column(name = "em_creado", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime creado;

	@Column(name = "em_modificado", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modificado;

	public Empresa() {

	}

	public Empresa(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{Ruc: '" + ruc + "', Razón Social: '" + razonSocial + "', Nombre Comercial: '" + nombreComercial
				+ "', Dir Matriz: '" + dirMatriz + "', Contribuyente Especial: '" + contribuyenteEspecial
				+ "', Obligado Contabilidad: '" + obligadoContabilidad + "'}";
	}

	public LocalDateTime getCreado() {
		return creado;
	}

	public void setCreado(LocalDateTime creado) {
		this.creado = creado;
	}

	public LocalDateTime getModificado() {
		return modificado;
	}

	public void setModificado(LocalDateTime modificado) {
		this.modificado = modificado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getDirMatriz() {
		return dirMatriz;
	}

	public void setDirMatriz(String dirMatriz) {
		this.dirMatriz = dirMatriz;
	}

	public String getContribuyenteEspecial() {
		return contribuyenteEspecial;
	}

	public void setContribuyenteEspecial(String contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}

	public Boolean getObligadoContabilidad() {
		return obligadoContabilidad;
	}

	public void setObligadoContabilidad(Boolean obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

}
