package ec.com.pakay.service.impl;

import ec.com.pakay.domain.*;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.SolicitudStore;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.repository.*;
import ec.com.pakay.service.ISolicitudService;
import ec.com.pakay.util.TablaAmortizacion;
import ec.com.pakay.util.Texto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitudServiceImpl implements ISolicitudService {

	private final ISolicitudDAO dao;

	private final IAmortizacionDAO amDAO;

	private final ICajaDAO cajaDAO;

	private final ITransaccionDAO trDAO;

	private final IDocumentoDAO docEmpDAO;

	@Override
	@Transactional
	public Solicitud save(Solicitud solicitud) {
		
		try {
			solicitud.setNumero("001");
			TablaAmortizacion tb = new TablaAmortizacion();
			List<Amortizacion> lista = tb.crearTablaAmortizacion(solicitud.getAmortizacion().equals("F")?"Francesa":"Alemana",
					solicitud.getTipo().equals("M")?"Meses":solicitud.getTipo().equals("Q")?"Quincenas":"Días", solicitud.getMonto().doubleValue(), solicitud.getInteres().doubleValue(), 
					solicitud.getPlazo(), solicitud.getFechaAprobacion());
			solicitud.setSaldo(new BigDecimal(lista.get(lista.size()-1).getCuota()));
			solicitud = dao.save(solicitud);

			lista.remove(lista.size()-1);
			lista.remove(0);
			for(Amortizacion a : lista) {
				a.setSolicitud(solicitud);
				amDAO.save(a);
			}

			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(solicitud.getCaja());
			transaccion.getDocumento().setId("001");
			transaccion.setFecha(solicitud.getFechaAprobacion());
			transaccion.setMes(Texto.rellenarCeros(2, String.valueOf(solicitud.getFechaAprobacion().getMonthValue()))+"/"+solicitud.getFechaAprobacion().getYear());
			transaccion.setSocio(solicitud.getSocio());
			transaccion.setValor(solicitud.getMonto());
			transaccion.setDescripcion("Préstamo " + solicitud.getNumero());
			transaccion.setNumero(solicitud.getNumero());
			transaccion = trDAO.save(transaccion);

			Caja caja = cajaDAO.findById(transaccion.getCaja().getId()).get();
			caja.setMonto(caja.getMonto().subtract(transaccion.getValor()));
			caja = cajaDAO.save(caja);
		}catch(Exception e) {
			System.out.println("Service error: " + e.getMessage());
		}
		
		return solicitud;
	}
	
	@Override
	public SolicitudDTO filtrar(TransaccionConsulta consulta) {
		SolicitudDTO slDTO = new SolicitudDTO();
		List<Object[]> resp = dao.filtrar(consulta.getIdSocio(),
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
	public void delete(Integer idSolicitud) {
		Solicitud solicitud = dao.findById(idSolicitud).get();
		dao.delete(solicitud);
	}

	@Override
	public Solicitud findById(Integer idSolicitud) {
		return dao.findById(idSolicitud).get();
	}

	@Override
	public List<Solicitud> findAll() {
		return dao.findAll();
	}

}
