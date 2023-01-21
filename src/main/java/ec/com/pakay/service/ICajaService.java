package ec.com.pakay.service;

import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.dto.CajaDTO;

import java.util.List;

public interface ICajaService {

	Caja save(Caja caja);
	
	void egresoIngreso(CajaDTO cajaDTO);
	
	void transferir(CajaDTO cajaDTO);

	void delete(Integer idCaja);

	Caja findById(Integer idCaja);

	List<Caja> findAll();

	Boolean exists(Caja caja);
	
	Boolean existsNotId(Caja caja);

}
