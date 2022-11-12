package ec.com.pakay.service.impl;

import ec.com.pakay.repository.IAuditoriaDAO;
import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.service.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService {

	@Autowired
	private IAuditoriaDAO dao;

	@Override
	public List<Auditoria> listByTablaAndIdTabla(String tabla, Long idTabla) {
		return dao.findByTablaAndIdTabla(tabla, idTabla);
	}
	
}
