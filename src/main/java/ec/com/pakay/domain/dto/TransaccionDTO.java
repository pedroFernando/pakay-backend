package ec.com.pakay.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDTO {
	
	private List<TransaccionStore> lista;
	private BigDecimal totalIngreso;
	private BigDecimal totalEgreso;
	private BigDecimal total;
	
	public TransaccionDTO() {
		this.lista = new ArrayList<>();
		this.totalIngreso = BigDecimal.ZERO;
		this.totalEgreso = BigDecimal.ZERO;
		this.total = BigDecimal.ZERO;
	}

	public List<TransaccionStore> getLista() {
		return lista;
	}

	public void setLista(List<TransaccionStore> lista) {
		this.lista = lista;
	}

	public BigDecimal getTotalIngreso() {
		return totalIngreso;
	}

	public void setTotalIngreso(BigDecimal totalIngreso) {
		this.totalIngreso = totalIngreso;
	}

	public BigDecimal getTotalEgreso() {
		return totalEgreso;
	}

	public void setTotalEgreso(BigDecimal totalEgreso) {
		this.totalEgreso = totalEgreso;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
