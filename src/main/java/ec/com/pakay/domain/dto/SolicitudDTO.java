package ec.com.pakay.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDTO {
	
	private List<SolicitudStore> lista;
	private BigDecimal totalMonto;
	private BigDecimal totalSaldo;
	
	public SolicitudDTO() {
		this.lista = new ArrayList<>();
		this.totalMonto = BigDecimal.ZERO;
		this.totalSaldo = BigDecimal.ZERO;
	}

	public List<SolicitudStore> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudStore> lista) {
		this.lista = lista;
	}

	public BigDecimal getTotalMonto() {
		return totalMonto;
	}

	public void setTotalMonto(BigDecimal totalMonto) {
		this.totalMonto = totalMonto;
	}

	public BigDecimal getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(BigDecimal totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	
}
