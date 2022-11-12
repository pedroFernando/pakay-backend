package ec.com.pakay.controller;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.aut.Aplicacion;
import ec.com.pakay.domain.aut.UsuarioAuditoria;
import ec.com.pakay.domain.dto.CajaDTO;
import ec.com.pakay.service.ICajaService;
import ec.com.pakay.util.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/caja")
public class CajaController {

	@Autowired
	private ICajaService service;

	private final static String TABLA = "caja";
	private final static String ENTIDAD = "Caja";

	@GetMapping(value = "/listar/{idEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Caja>> listByEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
		List<Caja> obj = new ArrayList<>();
		try {
			obj = service.listByEmpresa(idEmpresa);
		} catch (Exception e) {
			return new ResponseEntity<List<Caja>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Caja>>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Caja> findById(@PathVariable("id") Integer id) {
		Caja obj = new Caja();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Caja>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Caja>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/registrar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Caja caja, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			if (service.exists(caja)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe una caja con el nombre " + caja.getNombre()),
						HttpStatus.OK);
			}
			service.save(caja, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.CREATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Caja creada exitósamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/actualizar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Caja caja, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			if (service.existsNotId(caja)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe una caja con el nombre " + caja.getNombre()),
						HttpStatus.OK);
			}
			service.save(caja, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.UPDATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Caja actualizada correctamente"),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/transferir/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> transferir(@RequestBody CajaDTO cajaDTO, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.transferir(cajaDTO, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.UPDATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Caja actualizada correctamente"),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/egreso-ingreso/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> egresoIngreso(@RequestBody CajaDTO cajaDTO, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.egresoIngreso(cajaDTO, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.UPDATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Caja actualizada correctamente"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/eliminar/{id}&{idUser}&{ip}&{codApp}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable Integer id, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.delete(id, new Auditoria(TABLA, ENTIDAD, id.longValue(), Auditoria.DELETE, "{}", ip,
					new Aplicacion(codApp), new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.OK);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance(), HttpStatus.OK);
	}

}
