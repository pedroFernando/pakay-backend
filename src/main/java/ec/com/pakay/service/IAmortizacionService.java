package ec.com.pakay.service;

import ec.com.pakay.domain.Amortizacion;
import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Solicitud;

import java.util.List;

public interface IAmortizacionService {

	Amortizacion save(Amortizacion amortizacion, Auditoria auditoria);
	
	Amortizacion pagar(Amortizacion amortizacion, Auditoria auditoria);

	void delete(Integer idAmortizacion, Auditoria auditoria);

	Amortizacion findById(Integer idAmortizacion);

	List<Amortizacion> listBySolicitud(Solicitud solicitud);

}