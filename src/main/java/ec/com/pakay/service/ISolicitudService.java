package ec.com.pakay.service;

import ec.com.pakay.domain.Solicitud;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.TransaccionConsulta;

import java.util.List;

public interface ISolicitudService {

	Solicitud save(Solicitud solicitud);

	void delete(Integer idSolicitud);

	Solicitud findById(Integer idSolicitud);

	List<Solicitud> findAll();
	
	SolicitudDTO filtrar(TransaccionConsulta consulta);
	
}
