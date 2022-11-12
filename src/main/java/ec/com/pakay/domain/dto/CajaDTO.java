package ec.com.pakay.domain.dto;

import ec.com.pakay.domain.Caja;

import java.math.BigDecimal;

public class CajaDTO {
	
	private Caja cajaEgreso;
	private Caja cajaIngreso;
	private BigDecimal monto;
	private String motivo;
	private String tipo;
	
	public CajaDTO() {
		monto = BigDecimal.ZERO;
	}

	public Caja getCajaEgreso() {
		return cajaEgreso;
	}

	public void setCajaEgreso(Caja cajaEgreso) {
		this.cajaEgreso = cajaEgreso;
	}

	public Caja getCajaIngreso() {
		return cajaIngreso;
	}

	public void setCajaIngreso(Caja cajaIngreso) {
		this.cajaIngreso = cajaIngreso;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
