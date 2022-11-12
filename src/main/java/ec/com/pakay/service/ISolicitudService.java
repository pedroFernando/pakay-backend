package ec.com.pakay.service;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Solicitud;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.TransaccionConsulta;

import java.util.List;

public interface ISolicitudService {

	Solicitud save(Solicitud solicitud, Auditoria auditoria);

	void delete(Integer idSolicitud, Auditoria auditoria);

	Solicitud findById(Integer idSolicitud);

	List<Solicitud> listByEmpresa(Integer idEmpresa);
	
	SolicitudDTO filtrar(TransaccionConsulta consulta);
	
}
