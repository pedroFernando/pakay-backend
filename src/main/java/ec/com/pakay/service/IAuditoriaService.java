package ec.com.pakay.service;

import ec.com.pakay.domain.Auditoria;

import java.util.List;

public interface IAuditoriaService {

	List<Auditoria> listByTablaAndIdTabla(String tabla, Long idTabla);
}
