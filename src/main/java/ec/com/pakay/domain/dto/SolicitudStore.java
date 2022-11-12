package ec.com.pakay.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SolicitudStore {
	
	private Integer id;
	private String numero; 
	private String socio;
	private String garante;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate fecha;
	private BigDecimal monto;
	private BigDecimal saldo;
	private String amortizacion;
	private String tipo;
	private Integer plazo;
	
	public SolicitudStore(Integer id, String numero, String socio, String garante, LocalDate fecha, BigDecimal monto,
			BigDecimal saldo, String amortizacion, String tipo, Integer plazo) {
		super();
		this.id = id;
		this.numero = numero;
		this.socio = socio;
		this.garante = garante;
		this.fecha = fecha;
		this.monto = monto;
		this.saldo = saldo;
		this.amortizacion = amortizacion;
		this.tipo = tipo;
		this.plazo = plazo;
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

	public String getSocio() {
		return socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getGarante() {
		return garante;
	}

	public void setGarante(String garante) {
		this.garante = garante;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	
}