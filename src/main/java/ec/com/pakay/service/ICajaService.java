package ec.com.pakay.service;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.dto.CajaDTO;

import java.util.List;

public interface ICajaService {

	Caja save(Caja caja, Auditoria auditoria);
	
	void egresoIngreso(CajaDTO cajaDTO, Auditoria auditoria);
	
	void transferir(CajaDTO cajaDTO, Auditoria auditoria);

	void delete(Integer idCaja, Auditoria auditoria);

	Caja findById(Integer idCaja);

	List<Caja> listByEmpresa(Integer idEmpresa);

	Boolean exists(Caja caja);
	
	Boolean existsNotId(Caja caja);

}
