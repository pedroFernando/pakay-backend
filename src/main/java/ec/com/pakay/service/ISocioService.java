package ec.com.pakay.service;

import ec.com.pakay.domain.Socio;

import java.util.List;

public interface ISocioService {

	Socio save(Socio socio);

	void delete(Integer idSocio);

	Socio findById(Integer idSocio);

	List<Socio> findAll();

	Boolean exists(Socio socio);
	
	Boolean existsNotId(Socio socio);

}
