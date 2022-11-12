package ec.com.pakay.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parametro_empresa")
public class ParametroEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_parametro_empresa")
	private Integer id;
	
	@Column(name = "id_empresa")
	private Integer idEmpresa;
	
	@ManyToOne
	@JoinColumn(name = "id_parametro")
	private Parametro parametro;

	@Column(name = "valor")
	private BigDecimal valor;

	public ParametroEmpresa() {
	}

	public ParametroEmpresa(Integer id) {
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

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", idEmpresa:" + idEmpresa + ", parametro:" + parametro==null?"":parametro.getId() + ", valor:"
				+ valor + "}";
	}

}