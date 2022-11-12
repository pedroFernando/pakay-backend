package ec.com.pakay.service.impl;

import ec.com.pakay.repository.IAuditoriaDAO;
import ec.com.pakay.repository.ICajaDAO;
import ec.com.pakay.repository.IDocumentoEmpresaDAO;
import ec.com.pakay.repository.ITransaccionDAO;
import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.dto.CajaDTO;
import ec.com.pakay.service.ICajaService;
import ec.com.pakay.util.Texto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CajaServiceImpl implements ICajaService {

	@Autowired
	private ICajaDAO dao;
	
	@Autowired
	private ITransaccionDAO trDAO;

	@Autowired
	private IAuditoriaDAO auDAO;
	
	@Autowired
	private IDocumentoEmpresaDAO docEmpDAO;

	@Override
	@Transactional
	public Caja save(Caja caja, Auditoria auditoria) {
		caja = dao.save(caja);
		auditoria.setIdTabla(caja.getId().longValue());
		auditoria.setRegistro(caja.toString());
		auDAO.save(auditoria);
		return caja;
	}
	
	@Override
	@Transactional
	public void transferir(CajaDTO cajaDTO, Auditoria auditoria) {
		cajaDTO.getCajaEgreso().setMonto(cajaDTO.getCajaEgreso().getMonto().subtract(cajaDTO.getMonto()));
		cajaDTO.setCajaEgreso(dao.save(cajaDTO.getCajaEgreso()));
		auditoria.setIdTabla(cajaDTO.getCajaEgreso().getId().longValue());
		auditoria.setRegistro(cajaDTO.getCajaEgreso().toString());
		auDAO.save(auditoria);
		
		Auditoria aud = new Auditoria();
		cajaDTO.getCajaIngreso().setMonto(cajaDTO.getCajaIngreso().getMonto().add(cajaDTO.getMonto()));
		cajaDTO.setCajaIngreso(dao.save(cajaDTO.getCajaIngreso()));
		aud.setAccion(auditoria.getAccion());
		aud.setAplicacion(auditoria.getAplicacion());
		aud.setEntidad(auditoria.getEntidad());
		aud.setIp(auditoria.getIp());
		aud.setTabla(auditoria.getTabla());
		aud.setUsuario(auditoria.getUsuario());
		aud.setIdTabla(cajaDTO.getCajaIngreso().getId().longValue());
		aud.setRegistro(cajaDTO.getCajaIngreso().toString());
		auDAO.save(aud);
		
		Auditoria audTrE = new Auditoria();
		Transaccion transaccionE = new Transaccion();
		transaccionE.setCaja(cajaDTO.getCajaEgreso());
		transaccionE.getDocumento().setId("004");
		transaccionE.setFecha(LocalDate.now());
		transaccionE.setIdEmpresa(cajaDTO.getCajaEgreso().getIdEmpresa());
		transaccionE.setMes(LocalDate.now().getYear()+"/"+ Texto.rellenarCeros(2, String.valueOf(LocalDate.now().getMonthValue())));
		transaccionE.setValor(cajaDTO.getMonto());
		transaccionE.setNumero(docEmpDAO.consultaSiguiente(cajaDTO.getCajaEgreso().getIdEmpresa(), "004"));
		transaccionE.setDescripcion("Egreso " + cajaDTO.getCajaEgreso().getNombre());
		transaccionE = trDAO.save(transaccionE);
		audTrE.setEntidad("Transaccion");
		audTrE.setTabla("transaccion");
		audTrE.setAccion("U");
		audTrE.setIdTabla(transaccionE.getId().longValue());
		audTrE.setRegistro(transaccionE.toString());
		audTrE.setAplicacion(auditoria.getAplicacion());
		audTrE.setIp(auditoria.getIp());
		audTrE.setUsuario(auditoria.getUsuario());
		auDAO.save(audTrE);
		
		Auditoria audTrI = new Auditoria();
		Transaccion transaccionI = new Transaccion();
		transaccionI.setCaja(cajaDTO.getCajaIngreso());
		transaccionI.getDocumento().setId("003");
		transaccionI.setFecha(LocalDate.now());
		transaccionI.setIdEmpresa(cajaDTO.getCajaIngreso().getIdEmpresa());
		transaccionI.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String .valueOf(LocalDate.now().getMonthValue())));
		transaccionI.setValor(cajaDTO.getMonto());
		transaccionI.setNumero(docEmpDAO.consultaSiguiente(cajaDTO.getCajaIngreso().getIdEmpresa(), "003"));
		transaccionI.setDescripcion("Ingreso " + cajaDTO.getCajaIngreso().getNombre());
		transaccionI = trDAO.save(transaccionI);
		audTrI.setEntidad("Transaccion");
		audTrI.setTabla("transaccion");
		audTrI.setAccion("U");
		audTrI.setIdTabla(transaccionI.getId().longValue());
		audTrI.setRegistro(transaccionI.toString());
		audTrI.setAplicacion(auditoria.getAplicacion());
		audTrI.setIp(auditoria.getIp());
		audTrI.setUsuario(auditoria.getUsuario());
		auDAO.save(audTrI);
	}
	
	@Override
	@Transactional
	public void egresoIngreso(CajaDTO cajaDTO, Auditoria auditoria) {
		if(cajaDTO.getTipo().equals("E")) {
			cajaDTO.getCajaEgreso().setMonto(cajaDTO.getCajaEgreso().getMonto().subtract(cajaDTO.getMonto()));
			cajaDTO.setCajaEgreso(dao.save(cajaDTO.getCajaEgreso()));
			auditoria.setIdTabla(cajaDTO.getCajaEgreso().getId().longValue());
			auditoria.setRegistro(cajaDTO.getCajaEgreso().toString());
			auDAO.save(auditoria);
			Auditoria audTr = new Auditoria();
			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(cajaDTO.getCajaEgreso());
			transaccion.getDocumento().setId("004");
			transaccion.setFecha(LocalDate.now());
			transaccion.setIdEmpresa(cajaDTO.getCajaEgreso().getIdEmpresa());
			transaccion.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String.valueOf(LocalDate.now().getMonthValue())));
			transaccion.setValor(cajaDTO.getMonto());
			transaccion.setNumero(docEmpDAO.consultaSiguiente(cajaDTO.getCajaEgreso().getIdEmpresa(), "004"));
			transaccion.setDescripcion("Egreso " + cajaDTO.getCajaEgreso().getNombre() + " " + cajaDTO.getMotivo());
			transaccion = trDAO.save(transaccion);
			audTr.setEntidad("Transaccion");
			audTr.setTabla("transaccion");
			audTr.setAccion("U");
			audTr.setIdTabla(transaccion.getId().longValue());
			audTr.setRegistro(transaccion.toString());
			audTr.setAplicacion(auditoria.getAplicacion());
			audTr.setIp(auditoria.getIp());
			audTr.setUsuario(auditoria.getUsuario());
			auDAO.save(audTr);
		}else {
			cajaDTO.getCajaIngreso().setMonto(cajaDTO.getCajaIngreso().getMonto().add(cajaDTO.getMonto()));
			cajaDTO.setCajaIngreso(dao.save(cajaDTO.getCajaIngreso()));
			auditoria.setIdTabla(cajaDTO.getCajaIngreso().getId().longValue());
			auditoria.setRegistro(cajaDTO.getCajaIngreso().toString());
			auDAO.save(auditoria);
			Auditoria audTr = new Auditoria();
			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(cajaDTO.getCajaIngreso());
			transaccion.getDocumento().setId("003");
			transaccion.setFecha(LocalDate.now());
			transaccion.setIdEmpresa(cajaDTO.getCajaIngreso().getIdEmpresa());
			transaccion.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String .valueOf(LocalDate.now().getMonthValue())));
			transaccion.setValor(cajaDTO.getMonto());
			transaccion.setNumero(docEmpDAO.consultaSiguiente(cajaDTO.getCajaIngreso().getIdEmpresa(), "003"));
			transaccion.setDescripcion("Ingreso " + cajaDTO.getCajaIngreso().getNombre() + " " + cajaDTO.getMotivo());
			transaccion = trDAO.save(transaccion);
			audTr.setEntidad("Transaccion");
			audTr.setTabla("transaccion");
			audTr.setAccion("U");
			audTr.setIdTabla(transaccion.getId().longValue());
			audTr.setRegistro(transaccion.toString());
			audTr.setAplicacion(auditoria.getAplicacion());
			audTr.setIp(auditoria.getIp());
			audTr.setUsuario(auditoria.getUsuario());
			auDAO.save(audTr);
		}
	}

	@Override
	@Transactional
	public void delete(Integer idCaja, Auditoria auditoria) {
		Caja caja = dao.findById(idCaja).get();
		dao.delete(caja);
		auDAO.save(auditoria);
	}

	@Override
	public Caja findById(Integer idCaja) {
		return dao.findById(idCaja).get();
	}

	@Override
	public List<Caja> listByEmpresa(Integer idEmpresa) {
		return dao.findByIdEmpresa(idEmpresa);
	}

	@Override
	public Boolean exists(Caja caja) {
		return dao.existsByIdEmpresaAndNombre(caja.getIdEmpresa(), caja.getNombre());
	}
	
	@Override
	public Boolean existsNotId(Caja caja) {
		return dao.existsByIdEmpresaAndNombreAndNotId(caja.getIdEmpresa(), caja.getNombre(), caja.getId()) > 0;
	}

}
