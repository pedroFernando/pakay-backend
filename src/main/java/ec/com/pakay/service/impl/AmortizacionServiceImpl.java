package ec.com.pakay.service.impl;

import ec.com.pakay.domain.*;
import ec.com.pakay.repository.*;
import ec.com.pakay.service.IAmortizacionService;
import ec.com.pakay.util.Texto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AmortizacionServiceImpl implements IAmortizacionService {

	@Autowired
	private IAmortizacionDAO dao;

	@Autowired
	private IAuditoriaDAO auDAO;
	
	@Autowired
	private ICajaDAO cajaDAO;
	
	@Autowired
	private ITransaccionDAO trDAO;
	
	@Autowired
	private IDocumentoEmpresaDAO docEmpDAO;
	
	@Autowired
	private ISolicitudDAO solDAO;

	@Override
	@Transactional
	public Amortizacion save(Amortizacion amortizacion, Auditoria auditoria) {
		amortizacion = dao.save(amortizacion);
		auditoria.setIdTabla(amortizacion.getId().longValue());
		auditoria.setRegistro(amortizacion.toString());
		auDAO.save(auditoria);
		return amortizacion;
	}
	
	@Override
	@Transactional
	public Amortizacion pagar(Amortizacion amortizacion, Auditoria auditoria) {
		Caja caja = amortizacion.getCaja();
		amortizacion = dao.save(amortizacion);
		auditoria.setIdTabla(amortizacion.getId().longValue());
		auditoria.setRegistro(amortizacion.toString());
		auDAO.save(auditoria);
		
		Auditoria auSl = new Auditoria();
		amortizacion.getSolicitud().setSaldo(new BigDecimal(amortizacion.getSaldo()));
		amortizacion.setSolicitud(solDAO.save(amortizacion.getSolicitud()));
		auSl.setEntidad("Solicitud");
		auSl.setTabla("solicitud");
		auSl.setAccion("U");
		auSl.setIdTabla(amortizacion.getSolicitud().getId().longValue());
		auSl.setRegistro(amortizacion.getSolicitud().toString());
		auSl.setAplicacion(auditoria.getAplicacion());
		auSl.setIp(auditoria.getIp());
		auSl.setUsuario(auditoria.getUsuario());
		auDAO.save(auSl);
		
		Auditoria audTr = new Auditoria();
		Transaccion transaccion = new Transaccion();
		transaccion.setCaja(caja);
		transaccion.getDocumento().setId("002");
		transaccion.setFecha(amortizacion.getFechaPago());
		transaccion.setIdEmpresa(amortizacion.getSolicitud().getIdEmpresa());
		transaccion.setMes(Texto.rellenarCeros(2, String.valueOf(amortizacion.getFecha().getMonthValue()))+"/"+amortizacion.getFecha().getYear());
		transaccion.setSocio(amortizacion.getSolicitud().getSocio());
		transaccion.setValor(new BigDecimal(amortizacion.getCuota()));
		transaccion.setNumero(docEmpDAO.consultaSiguiente(amortizacion.getSolicitud().getIdEmpresa(), "001"));
		transaccion.setDescripcion("Cuota " + transaccion.getNumero());
		transaccion = trDAO.save(transaccion);
		audTr.setEntidad("Transaccion");
		audTr.setTabla("transaccion");
		audTr.setAccion("C");
		audTr.setIdTabla(transaccion.getId().longValue());
		audTr.setRegistro(transaccion.toString());
		audTr.setAplicacion(auditoria.getAplicacion());
		audTr.setIp(auditoria.getIp());
		audTr.setUsuario(auditoria.getUsuario());
		auDAO.save(audTr);
		
		Auditoria audCj = new Auditoria();
		caja.setMonto(caja.getMonto().add(transaccion.getValor()));
		caja = cajaDAO.save(caja);
		audCj.setEntidad("Caja");
		audCj.setTabla("caja");
		audCj.setAccion("U");
		audCj.setIdTabla(caja.getId().longValue());
		audCj.setRegistro(caja.toString());
		audCj.setAplicacion(auditoria.getAplicacion());
		audCj.setIp(auditoria.getIp());
		audCj.setUsuario(auditoria.getUsuario());
		auDAO.save(audCj);
		return amortizacion;
	}

	@Override
	@Transactional
	public void delete(Integer idAmortizacion, Auditoria auditoria) {
		Amortizacion a = dao.findById(idAmortizacion).get();
		dao.delete(a);
		auDAO.save(auditoria);
	}

	@Override
	public Amortizacion findById(Integer idAmortizacion) {
		return dao.findById(idAmortizacion).get();
	}

	@Override
	public List<Amortizacion> listBySolicitud(Solicitud solicitud) {
		return dao.findBySolicitud(solicitud);
	}

}
