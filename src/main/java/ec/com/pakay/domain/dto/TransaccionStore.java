package ec.com.pakay.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class TransaccionStore {
	
	private BigInteger id;
	private String numero; 
	private String nombres;
	private String mes;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate fecha;
	private BigDecimal ingreso;
	private BigDecimal egreso;
	private String descripcion;
	private String caja;
	private String documento;
	
	public TransaccionStore(String numero, String nombres, String mes, LocalDate fecha, BigDecimal ingreso,
			BigDecimal egreso, String descripcion, String caja, BigInteger id, String documento) {
		super();
		this.numero = numero;
		this.nombres = nombres;
		this.mes = mes;
		this.fecha = fecha;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.descripcion = descripcion;
		this.caja = caja;
		this.id = id;
		this.documento = documento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getIngreso() {
		return ingreso;
	}
	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}
	public BigDecimal getEgreso() {
		return egreso;
	}
	public void setEgreso(BigDecimal egreso) {
		this.egreso = egreso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCaja() {
		return caja;
	}
	public void setCaja(String caja) {
		this.caja = caja;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}