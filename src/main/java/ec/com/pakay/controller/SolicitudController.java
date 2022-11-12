package ec.com.pakay.controller;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Solicitud;
import ec.com.pakay.domain.aut.Aplicacion;
import ec.com.pakay.domain.aut.UsuarioAuditoria;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.service.ISolicitudService;
import ec.com.pakay.util.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {

	@Autowired
	private ISolicitudService service;

	private final static String TABLA = "solicitud";
	private final static String ENTIDAD = "Solicitud";

	@GetMapping(value = "/listar/{idEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Solicitud>> listByEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
		List<Solicitud> obj = new ArrayList<>();
		try {
			obj = service.listByEmpresa(idEmpresa);
		} catch (Exception e) {
			return new ResponseEntity<List<Solicitud>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Solicitud>>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Solicitud> findById(@PathVariable("id") Integer id) {
		Solicitud obj = new Solicitud();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Solicitud>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Solicitud>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/registrar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Solicitud solicitud, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.save(solicitud, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.CREATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			System.out.println("Error registro prestamo: " +  e.getMessage());
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Préstamo creado exitósamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/actualizar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Solicitud solicitud, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.save(solicitud, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.UPDATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Préstamo actualizado correctamente"),
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
	
	@PostMapping(value = "/filtrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SolicitudDTO> filtrar(@RequestBody TransaccionConsulta consulta) {
		SolicitudDTO obj = new SolicitudDTO();
		try {
			obj = service.filtrar(consulta);
		} catch (Exception e) {
			System.out.println("Transaccion controller: " + e.getMessage());
			return new ResponseEntity<SolicitudDTO>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SolicitudDTO>(obj, HttpStatus.OK);
	}

}
