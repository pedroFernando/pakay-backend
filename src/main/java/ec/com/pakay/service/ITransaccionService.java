package ec.com.pakay.service;

import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.domain.dto.TransaccionDTO;
import ec.com.pakay.report.RpSocioEmpresa;

import java.util.List;

public interface ITransaccionService {
	
	Transaccion save(Transaccion transaccion);

	void delete(Integer idTransaccion);
	
	Transaccion findById(Integer idTransaccion);

	List<Transaccion> findAll();
	
	List<Transaccion> listByDocumento(String documento);
	
	TransaccionDTO filtrar(TransaccionConsulta consulta);
	
	List<RpSocioEmpresa> listSocio(Integer idSocio);
	
	List<RpSocioEmpresa> listEmpresa(Integer idEmpresa);

}
