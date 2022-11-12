package ec.com.pakay.service.impl;

import ec.com.pakay.repository.IAuditoriaDAO;
import ec.com.pakay.repository.ISocioDAO;
import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Socio;
import ec.com.pakay.service.ISocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocioServiceImpl implements ISocioService {

	@Autowired
	private ISocioDAO dao;

	@Autowired
	private IAuditoriaDAO auDAO;

	@Override
	@Transactional
	public Socio save(Socio socio, Auditoria auditoria) {
		socio = dao.save(socio);
		auditoria.setIdTabla(socio.getId().longValue());
		auditoria.setRegistro(socio.toString());
		auDAO.save(auditoria);
		return socio;
	}

	@Override
	@Transactional
	public void delete(Integer idSocio, Auditoria auditoria) {
		Socio socio = dao.findById(idSocio).get();
		dao.delete(socio);
		auDAO.save(auditoria);
	}

	@Override
	public Socio findById(Integer idSocio) {
		return dao.findById(idSocio).get();
	}

	@Override
	public List<Socio> listByEmpresaAndTipo(Integer idEmpresa, String tipo) {
		return dao.findByIdEmpresaAndTipo(idEmpresa, tipo);
	}

	@Override
	public Boolean exists(Socio socio) {
		return dao.existsByIdEmpresaAndCedula(socio.getIdEmpresa(), socio.getCedula());
	}
	
	@Override
	public Boolean existsNotId(Socio socio) {
		return dao.existsByIdEmpresaAndCedulaAndNotId(socio.getIdEmpresa(), socio.getCedula(), socio.getId()) > 0;
	}
	
}