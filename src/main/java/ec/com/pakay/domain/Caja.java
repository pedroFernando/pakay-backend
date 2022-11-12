package ec.com.pakay.domain;

import ec.com.pakay.util.Numeros;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "caja")
public class Caja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_caja")
	private Integer id;

	@Column(name = "id_empresa")
	private Integer idEmpresa;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "monto")
	private BigDecimal monto;

	public Caja() {
	}

	public Caja(Integer id) {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", idEmpresa;" + idEmpresa + ", nombre:" + nombre 
				+ ", monto:" + Numeros.getFormatoDecimales(monto.doubleValue()) + "}";
	}

}
