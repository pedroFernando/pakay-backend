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
	private ICajaDAO cajaDAO;
	
	@Autowired
	private ITransaccionDAO trDAO;
	
	@Autowired
	private IDocumentoDAO docEmpDAO;
	
	@Autowired
	private ISolicitudDAO solDAO;

	@Override
	@Transactional
	public Amortizacion save(Amortizacion amortizacion) {
		amortizacion = dao.save(amortizacion);
		return amortizacion;
	}
	
	@Override
	@Transactional
	public Amortizacion pagar(Amortizacion amortizacion) {
		Caja caja = amortizacion.getCaja();
		amortizacion = dao.save(amortizacion);

		amortizacion.getSolicitud().setSaldo(new BigDecimal(amortizacion.getSaldo()));
		amortizacion.setSolicitud(solDAO.save(amortizacion.getSolicitud()));

		Transaccion transaccion = new Transaccion();
		transaccion.setCaja(caja);
		transaccion.getDocumento().setId("002");
		transaccion.setFecha(amortizacion.getFechaPago());
		transaccion.setMes(Texto.rellenarCeros(2, String.valueOf(amortizacion.getFecha().getMonthValue()))+"/"+amortizacion.getFecha().getYear());
		transaccion.setSocio(amortizacion.getSolicitud().getSocio());
		transaccion.setValor(new BigDecimal(amortizacion.getCuota()));
		transaccion.setNumero(docEmpDAO.consultaSiguiente("001"));
		transaccion.setDescripcion("Cuota " + transaccion.getNumero());
		transaccion = trDAO.save(transaccion);

		caja.setMonto(caja.getMonto().add(transaccion.getValor()));
		caja = cajaDAO.save(caja);

		return amortizacion;
	}

	@Override
	@Transactional
	public void delete(Integer idAmortizacion) {
		Amortizacion a = dao.findById(idAmortizacion).get();
		dao.delete(a);
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
