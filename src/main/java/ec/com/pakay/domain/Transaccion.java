package ec.com.pakay.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ec.com.pakay.util.LocalDateConverter;
import ec.com.pakay.util.LocalDateTimeConverter;
import ec.com.pakay.util.Numeros;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaccion")
	private Integer id;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "mes")
	private String mes;
	
	@Column(name = "fecha")
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate fecha;
	
	@Column(name = "creado", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime creado;
	
	@Column(name = "modificado", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime modificado;

	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_socio")
	private Socio socio;
	
	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name = "id_caja")
	private Caja caja;
	
	public Transaccion() {
		documento = new Documento();
	}

	public Transaccion(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", numero:" + numero
				+ ", fecha:" + fecha + ", valor:" + Numeros.getFormatoDecimales(valor.doubleValue()) 
				+ ", descripcion:" + descripcion + ", socio:" + (socio==null?"":socio.getId())
				+ ", documento:" + documento.getId() + ", caja:" + caja.getId() + ", mes:"+ mes +"}";
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
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

}
