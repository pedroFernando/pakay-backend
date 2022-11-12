package ec.com.pakay.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ec.com.pakay.util.LocalDateConverter;
import ec.com.pakay.util.Numeros;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "amortizacion")
public class Amortizacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amortizacion")
    private Integer id;
	
	@Column(name = "fecha")
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDate fecha;
	
    @Column(name = "capital")
    private Double capital;
    
    @Column(name = "interes")
    private Double interes;
    
    @Column(name = "cuota")
    private Double cuota;
    
    @Column(name = "saldo")
    private Double saldo;
    
    @Column(name = "fecha_pago")
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDate fechaPago;
    
    @Column(name = "estado")
    private String estado;
    
    @ManyToOne
	@JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;
    
    @Transient
    private Boolean inactivar;
    @Transient
    private int numero;
    @Transient
    private Caja caja;
    
    public Amortizacion() {
    }

    public Amortizacion(Integer id) {
        this.id = id;
    }

    public Amortizacion(Integer id, LocalDate fecha, Double interes, Double cuota, Double saldo, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.interes = interes;
        this.cuota = cuota;
        this.saldo = saldo;
        this.estado = estado;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Boolean getInactivar() {
		return inactivar;
	}

	public void setInactivar(Boolean inactivar) {
		this.inactivar = inactivar;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", fecha:" + fecha + ", capital:" + Numeros.getFormatoDecimales(capital) 
				+ ", interes:" + Numeros.getFormatoDecimales(interes)+ ", cuota:" + Numeros.getFormatoDecimales(cuota) 
				+ ", saldo:" + Numeros.getFormatoDecimales(saldo) + ", fechaPago:" + fechaPago + ", estado:" + estado
				+ ", solicitud:" + solicitud.getId() + "}";
	}
    
}
