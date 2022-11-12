package ec.com.pakay.service;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Socio;

import java.util.List;

public interface ISocioService {

	Socio save(Socio socio, Auditoria auditoria);

	void delete(Integer idSocio, Auditoria auditoria);

	Socio findById(Integer idSocio);

	List<Socio> listByEmpresaAndTipo(Integer idEmpresa, String tipo);

	Boolean exists(Socio socio);
	
	Boolean existsNotId(Socio socio);

}
