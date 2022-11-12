package ec.com.pakay.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ec.com.pakay.util.LocalDateConverter;
import ec.com.pakay.util.Numeros;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "solicitud")
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer id;
	
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    
    @Column(name = "fecha_solicitud")
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDate fechaSolicitud;
    
    @Column(name = "fecha_aprobacion")
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDate fechaAprobacion;
    
    @Column(name = "monto")
    private BigDecimal monto;
    
    @Column(name = "plazo")
    private int plazo;
    
    @Column(name = "interes")
    private BigDecimal interes;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "amortizacion")
    private String amortizacion;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "saldo")
    private BigDecimal saldo;
    
    @Column(name = "saldo_favor")
    private BigDecimal saldoFavor;
    
    @Column(name = "saldo_contra")
    private BigDecimal saldoContra;
    
    @Column(name = "numero")
    private String numero;
    
    @ManyToOne
	@JoinColumn(name = "id_caja")
	private Caja caja;
    
    @ManyToOne
	@JoinColumn(name = "id_socio")
	private Socio socio;
    
    @ManyToOne
	@JoinColumn(name = "id_garante")
    private Socio garante;

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

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(LocalDate fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getSaldoFavor() {
		return saldoFavor;
	}

	public void setSaldoFavor(BigDecimal saldoFavor) {
		this.saldoFavor = saldoFavor;
	}

	public BigDecimal getSaldoContra() {
		return saldoContra;
	}

	public void setSaldoContra(BigDecimal saldoContra) {
		this.saldoContra = saldoContra;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Socio getGarante() {
		return garante;
	}

	public void setGarante(Socio garante) {
		this.garante = garante;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", idEmpresa:" + idEmpresa + ", fechaSolicitud:" + fechaSolicitud
				+ ", fechaAprobacion:" + fechaAprobacion + ", monto:" + Numeros.getFormatoDecimales(monto.doubleValue()) 
				+ ", plazo:" + plazo + ", interes:" + Numeros.getFormatoDecimales(interes.doubleValue()) + ", tipo:" + tipo 
				+ ", amortizacion:" + amortizacion + ", estado:" + estado + ", saldo:" + Numeros.getFormatoDecimales(saldo.doubleValue()) 
				+ ", saldoFavor:" + Numeros.getFormatoDecimales(saldoFavor.doubleValue()) + ", saldoContra:" 
				+ Numeros.getFormatoDecimales(saldoContra.doubleValue()) + ", caja:" + caja.getId() + ", socio:"
				+ socio.getId() + ", garante:" + garante.getId() + "}";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
