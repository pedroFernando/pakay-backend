package ec.com.pakay.service.impl;

import ec.com.pakay.domain.*;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.SolicitudStore;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.repository.*;
import ec.com.pakay.service.ISolicitudService;
import ec.com.pakay.util.TablaAmortizacion;
import ec.com.pakay.util.Texto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements ISolicitudService {

	@Autowired
	private ISolicitudDAO dao;
	
	@Autowired
	private IAmortizacionDAO amDAO;
	
	@Autowired
	private IAuditoriaDAO auDAO;
	
	@Autowired
	private ICajaDAO cajaDAO;
	
	@Autowired
	private ITransaccionDAO trDAO;
	
	@Autowired
	private IDocumentoEmpresaDAO docEmpDAO;

	@Override
	@Transactional
	public Solicitud save(Solicitud solicitud, Auditoria auditoria) {
		
		try {
			solicitud.setNumero(docEmpDAO.consultaSiguiente(solicitud.getIdEmpresa(), "001"));
			TablaAmortizacion tb = new TablaAmortizacion();
			List<Amortizacion> lista = tb.crearTablaAmortizacion(solicitud.getAmortizacion().equals("F")?"Francesa":"Alemana",
					solicitud.getTipo().equals("M")?"Meses":solicitud.getTipo().equals("Q")?"Quincenas":"Días", solicitud.getMonto().doubleValue(), solicitud.getInteres().doubleValue(), 
					solicitud.getPlazo(), solicitud.getFechaAprobacion());
			solicitud.setSaldo(new BigDecimal(lista.get(lista.size()-1).getCuota()));
			solicitud = dao.save(solicitud);
			auditoria.setIdTabla(solicitud.getId().longValue());
			auditoria.setRegistro(solicitud.toString());
			auDAO.save(auditoria);
			lista.remove(lista.size()-1);
			lista.remove(0);
			for(Amortizacion a : lista) {
				a.setSolicitud(solicitud);
				amDAO.save(a);
				Auditoria audAm = new Auditoria();
				audAm.setEntidad("Amortizacion");
				audAm.setTabla("amortizacion");
				audAm.setAccion("C");
				audAm.setIdTabla(a.getId().longValue());
				audAm.setRegistro(a.toString());
				audAm.setAplicacion(auditoria.getAplicacion());
				audAm.setIp(auditoria.getIp());
				audAm.setUsuario(auditoria.getUsuario());
				auDAO.save(audAm);
			}
			
			Auditoria audTr = new Auditoria();
			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(solicitud.getCaja());
			transaccion.getDocumento().setId("001");
			transaccion.setFecha(solicitud.getFechaAprobacion());
			transaccion.setIdEmpresa(solicitud.getIdEmpresa());
			transaccion.setMes(Texto.rellenarCeros(2, String.valueOf(solicitud.getFechaAprobacion().getMonthValue()))+"/"+solicitud.getFechaAprobacion().getYear());
			transaccion.setSocio(solicitud.getSocio());
			transaccion.setValor(solicitud.getMonto());
			transaccion.setDescripcion("Préstamo " + solicitud.getNumero());
			transaccion.setNumero(solicitud.getNumero());
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
			Caja caja = cajaDAO.findById(transaccion.getCaja().getId()).get();
			caja.setMonto(caja.getMonto().subtract(transaccion.getValor()));
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
		}catch(Exception e) {
			System.out.println("Service error: " + e.getMessage());
		}
		
		return solicitud;
	}
	
	@Override
	public SolicitudDTO filtrar(TransaccionConsulta consulta) {
		SolicitudDTO slDTO = new SolicitudDTO();
		List<Object[]> resp = dao.filtrar(consulta.getIdEmpresa(), consulta.getIdSocio(),
				Optional.ofNullable(consulta.getFechaDesde()).map(Date::valueOf).orElse(null),
				Optional.ofNullable(consulta.getFechaHasta()).map(Date::valueOf).orElse(null));
		try {
			resp.forEach(x -> {
				slDTO.setTotalMonto(slDTO.getTotalMonto().add((BigDecimal) x[5]));
				slDTO.setTotalSaldo(slDTO.getTotalSaldo().add((BigDecimal) x[6]));
				slDTO.getLista()
				.add(new SolicitudStore((Integer) x[0], (String) x[1], (String) x[2], (String) x[3],
						Optional.ofNullable((Date) x[4]).map(Date::toLocalDate).orElse(null),
						(BigDecimal) x[5], (BigDecimal) x[6], (String) x[7], (String) x[8], (Integer) x[9]));
			});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return slDTO;
	}

	@Override
	@Transactional
	public void delete(Integer idSolicitud, Auditoria auditoria) {
		Solicitud solicitud = dao.findById(idSolicitud).get();
		dao.delete(solicitud);
		auDAO.save(auditoria);
	}

	@Override
	public Solicitud findById(Integer idSolicitud) {
		return dao.findById(idSolicitud).get();
	}

	@Override
	public List<Solicitud> listByEmpresa(Integer idEmpresa) {
		return dao.findByIdEmpresa(idEmpresa);
	}

}
