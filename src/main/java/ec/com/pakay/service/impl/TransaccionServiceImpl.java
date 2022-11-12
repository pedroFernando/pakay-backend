package ec.com.pakay.service.impl;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.Documento;
import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.domain.dto.TransaccionDTO;
import ec.com.pakay.domain.dto.TransaccionStore;
import ec.com.pakay.report.RpSocioEmpresa;
import ec.com.pakay.repository.*;
import ec.com.pakay.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

	@Autowired
	private ITransaccionDAO dao;
	
	@Autowired
	private IAuditoriaDAO auDAO;
	
	@Autowired
	private IDocumentoDAO docDAO;
	
	@Autowired
	private IDocumentoEmpresaDAO docEmpDAO;
	
	@Autowired
	private ICajaDAO cajaDAO;
	
	@Override
	public Transaccion findById(Integer idTransaccion) {
		return dao.findById(idTransaccion).get();
	}

	@Override
	public List<Transaccion> listByEmpresa(Integer idEmpresa) {
		return dao.findByIdEmpresa(idEmpresa);
	}
	
	@Override
	public List<Transaccion> listByEmpresaAndDocumento(Integer idEmpresa, String doc) {
		List<Documento> documento = docDAO.findById(doc);
		return dao.findByIdEmpresaAndDocumento(idEmpresa, documento.get(0));
	}
	
	@Override
	public TransaccionDTO filtrar(TransaccionConsulta consulta) {
		TransaccionDTO trDTO = new TransaccionDTO();
		List<Object[]> resp = dao.filtrar(consulta.getIdEmpresa(), consulta.getIdSocio(), consulta.getCodDocumento(),
				Optional.ofNullable(consulta.getFechaDesde()).map(Date::valueOf).orElse(null),
				Optional.ofNullable(consulta.getFechaHasta()).map(Date::valueOf).orElse(null));
		try {
			resp.forEach(x -> {
				if (((Character) x[6]).equals('I')) {
					trDTO.setTotalIngreso(trDTO.getTotalIngreso().add((BigDecimal) x[4]));
					trDTO.setTotal(trDTO.getTotal().add((BigDecimal) x[4]));
					trDTO.getLista()
					.add(new TransaccionStore((String) x[0], (String) x[1], (String) x[2],
							Optional.ofNullable((Date) x[3]).map(Date::toLocalDate).orElse(null),
							(BigDecimal) x[4], BigDecimal.ZERO, (String) x[5], 
							(String) x[7], (BigInteger) x[8], (String) x[9]));
				} else {
					trDTO.setTotalEgreso(trDTO.getTotalEgreso().add((BigDecimal) x[4]));
					trDTO.setTotal(trDTO.getTotal().subtract((BigDecimal) x[4]));
					trDTO.getLista()
					.add(new TransaccionStore((String) x[0], (String) x[1], (String) x[2],
							Optional.ofNullable((Date) x[3]).map(Date::toLocalDate).orElse(null),
							BigDecimal.ZERO, (BigDecimal) x[4], (String) x[5], (String) x[7], 
							(BigInteger) x[8], (String) x[9]));
				}
			});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return trDTO;
	}
	
	@Override
	public List<RpSocioEmpresa> listSocio(Integer idSocio) {
		List<RpSocioEmpresa> lista = new ArrayList<>();
		List<Object[]> resp = dao.reporteSocio(idSocio);
		try {
			resp.forEach(x -> {
				lista.add(new RpSocioEmpresa((String) x[0], (Integer) x[1], (Double) x[2]));
			});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	@Override
	public List<RpSocioEmpresa> listEmpresa(Integer idEmpresa) {
		List<RpSocioEmpresa> lista = new ArrayList<>();
		List<Object[]> resp = dao.reporteEmpresa(idEmpresa);
		try {
			resp.forEach(x -> {
				lista.add(new RpSocioEmpresa((String) x[0], (Integer) x[1], (Double) x[2]));
			});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	@Override
	@Transactional
	public Transaccion save(Transaccion transaccion, Auditoria auditoria) {
		try {
			transaccion.setNumero(docEmpDAO.consultaSiguiente(transaccion.getIdEmpresa(), transaccion.getDocumento().getId()));
			transaccion = dao.save(transaccion);
			auditoria.setIdTabla(transaccion.getId().longValue());
			auditoria.setRegistro(transaccion.toString());
			auDAO.save(auditoria);
			
			Auditoria aud = new Auditoria();
			Documento doc = docDAO.findById(transaccion.getDocumento().getId()).get(0);
			Caja caja = cajaDAO.findById(transaccion.getCaja().getId()).get();
			if(doc.getTipo().equals("I")) {
				caja.setMonto(caja.getMonto().add(transaccion.getValor()));
			} else {
				caja.setMonto(caja.getMonto().subtract(transaccion.getValor()));
			}
			caja = cajaDAO.save(caja);
			aud.setEntidad("Caja");
			aud.setTabla("caja");
			aud.setAccion("U");
			aud.setIdTabla(caja.getId().longValue());
			aud.setRegistro(caja.toString());
			aud.setAplicacion(auditoria.getAplicacion());
			aud.setIp(auditoria.getIp());
			aud.setUsuario(auditoria.getUsuario());
			auDAO.save(aud);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return transaccion;
	}

	@Override
	@Transactional
	public void delete(Integer idTransaccion, Auditoria auditoria) {
		Transaccion transaccion = dao.findById(idTransaccion).get();
		dao.delete(transaccion);
		auDAO.save(auditoria);
	}

}