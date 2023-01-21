package ec.com.pakay.service;

import ec.com.pakay.domain.Amortizacion;
import ec.com.pakay.domain.Solicitud;

import java.util.List;

public interface IAmortizacionService {

	Amortizacion save(Amortizacion amortizacion);
	
	Amortizacion pagar(Amortizacion amortizacion);

	void delete(Integer idAmortizacion);

	Amortizacion findById(Integer idAmortizacion);

	List<Amortizacion> listBySolicitud(Solicitud solicitud);

}