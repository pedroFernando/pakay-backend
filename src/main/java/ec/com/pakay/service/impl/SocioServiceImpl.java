package ec.com.pakay.service.impl;

import ec.com.pakay.repository.ISocioDAO;
import ec.com.pakay.domain.Socio;
import ec.com.pakay.service.ISocioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SocioServiceImpl implements ISocioService {

	private final ISocioDAO dao;

	@Override
	public Socio save(Socio socio) {
		socio = dao.save(socio);
		return socio;
	}

	@Override
	public void delete(Integer idSocio) {
		Socio socio = dao.findById(idSocio).get();
		dao.delete(socio);
	}

	@Override
	public Socio findById(Integer idSocio) {
		return dao.findById(idSocio).get();
	}

	@Override
	public List<Socio> findAll() {
		return dao.findAll();
	}

	@Override
	public Boolean exists(Socio socio) {
		return dao.existsByCedula(socio.getCedula());
	}
	
	@Override
	public Boolean existsNotId(Socio socio) {
		return dao.existsByCedulaAndNotId(socio.getCedula(), socio.getId()) > 0;
	}
	
}