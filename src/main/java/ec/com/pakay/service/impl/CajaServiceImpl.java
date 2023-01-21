package ec.com.pakay.service.impl;

import ec.com.pakay.exceptions.PakayConflictException;
import ec.com.pakay.exceptions.PakayPreconditionFailedException;
import ec.com.pakay.repository.ICajaDAO;
import ec.com.pakay.repository.IDocumentoDAO;
import ec.com.pakay.repository.ITransaccionDAO;
import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.dto.CajaDTO;
import ec.com.pakay.service.ICajaService;
import ec.com.pakay.util.Respuesta;
import ec.com.pakay.util.Texto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CajaServiceImpl implements ICajaService {

	private final ICajaDAO dao;
	private final ITransaccionDAO trDAO;
	private final IDocumentoDAO docEmpDAO;

	@Override
	public Caja save(Caja caja) {
		if (exists(caja)) {
			throw new PakayPreconditionFailedException(String.format("El nombre de caja %s ya existe"));
		}
		caja = dao.save(caja);
		return caja;
	}
	
	@Override
	@Transactional
	public void transferir(CajaDTO cajaDTO) {
		cajaDTO.getCajaEgreso().setMonto(cajaDTO.getCajaEgreso().getMonto().subtract(cajaDTO.getMonto()));
		cajaDTO.setCajaEgreso(dao.save(cajaDTO.getCajaEgreso()));

		cajaDTO.getCajaIngreso().setMonto(cajaDTO.getCajaIngreso().getMonto().add(cajaDTO.getMonto()));
		cajaDTO.setCajaIngreso(dao.save(cajaDTO.getCajaIngreso()));

		Transaccion transaccionE = new Transaccion();
		transaccionE.setCaja(cajaDTO.getCajaEgreso());
		transaccionE.getDocumento().setId("004");
		transaccionE.setFecha(LocalDate.now());
		transaccionE.setMes(LocalDate.now().getYear()+"/"+ Texto.rellenarCeros(2, String.valueOf(LocalDate.now().getMonthValue())));
		transaccionE.setValor(cajaDTO.getMonto());
		transaccionE.setNumero(docEmpDAO.consultaSiguiente("004"));
		transaccionE.setDescripcion("Egreso " + cajaDTO.getCajaEgreso().getNombre());
		transaccionE = trDAO.save(transaccionE);

		Transaccion transaccionI = new Transaccion();
		transaccionI.setCaja(cajaDTO.getCajaIngreso());
		transaccionI.getDocumento().setId("003");
		transaccionI.setFecha(LocalDate.now());
		transaccionI.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String .valueOf(LocalDate.now().getMonthValue())));
		transaccionI.setValor(cajaDTO.getMonto());
		transaccionI.setNumero(docEmpDAO.consultaSiguiente("003"));
		transaccionI.setDescripcion("Ingreso " + cajaDTO.getCajaIngreso().getNombre());
		transaccionI = trDAO.save(transaccionI);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void egresoIngreso(CajaDTO cajaDTO) {
		if(cajaDTO.getTipo().equals("E")) {
			cajaDTO.getCajaEgreso().setMonto(cajaDTO.getCajaEgreso().getMonto().subtract(cajaDTO.getMonto()));
			cajaDTO.setCajaEgreso(dao.save(cajaDTO.getCajaEgreso()));

			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(cajaDTO.getCajaEgreso());
			transaccion.getDocumento().setId("004");
			transaccion.setFecha(LocalDate.now());
			transaccion.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String.valueOf(LocalDate.now().getMonthValue())));
			transaccion.setValor(cajaDTO.getMonto());
			transaccion.setNumero(docEmpDAO.consultaSiguiente("004"));
			transaccion.setDescripcion("Egreso " + cajaDTO.getCajaEgreso().getNombre() + " " + cajaDTO.getMotivo());
			transaccion = trDAO.save(transaccion);
		}else {
			cajaDTO.getCajaIngreso().setMonto(cajaDTO.getCajaIngreso().getMonto().add(cajaDTO.getMonto()));
			cajaDTO.setCajaIngreso(dao.save(cajaDTO.getCajaIngreso()));

			Transaccion transaccion = new Transaccion();
			transaccion.setCaja(cajaDTO.getCajaIngreso());
			transaccion.getDocumento().setId("003");
			transaccion.setFecha(LocalDate.now());
			transaccion.setMes(LocalDate.now().getYear()+"/"+Texto.rellenarCeros(2, String .valueOf(LocalDate.now().getMonthValue())));
			transaccion.setValor(cajaDTO.getMonto());
			transaccion.setNumero(docEmpDAO.consultaSiguiente("003"));
			transaccion.setDescripcion("Ingreso " + cajaDTO.getCajaIngreso().getNombre() + " " + cajaDTO.getMotivo());
			transaccion = trDAO.save(transaccion);
		}
	}

	@Override
	public void delete(Integer idCaja) {
		Caja caja = dao.findById(idCaja).get();
		dao.delete(caja);
	}

	@Override
	public Caja findById(Integer idCaja) {
		return dao.findById(idCaja).get();
	}

	@Override
	public List<Caja> findAll() {
		return dao.findAll();
	}

	@Override
	public Boolean exists(Caja caja) {
		return dao.existsByNombre(caja.getNombre());
	}
	
	@Override
	public Boolean existsNotId(Caja caja) {
		return dao.existsByNombreAndNotId(caja.getNombre(), caja.getId()) > 0;
	}

}
