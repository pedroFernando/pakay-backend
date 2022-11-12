package ec.com.pakay.controller;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Socio;
import ec.com.pakay.domain.aut.Aplicacion;
import ec.com.pakay.domain.aut.UsuarioAuditoria;
import ec.com.pakay.service.ISocioService;
import ec.com.pakay.util.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/socio")
public class SocioController {

	@Autowired
	private ISocioService service;

	private final static String TABLA = "socio";
	private final static String ENTIDAD = "Socio";

	@GetMapping(value = "/listar/{idEmpresa}&{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Socio>> listByEmpresa(@PathVariable("idEmpresa") Integer idEmpresa, @PathVariable("tipo") String tipo) {
		List<Socio> obj = new ArrayList<>();
		try {
			obj = service.listByEmpresaAndTipo(idEmpresa, tipo);
		} catch (Exception e) {
			return new ResponseEntity<List<Socio>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Socio>>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Socio> findById(@PathVariable("id") Integer id) {
		Socio obj = new Socio();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Socio>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Socio>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/registrar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Socio socio, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			if (service.exists(socio)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe un socio con la cédula " + socio.getCedula()),
						HttpStatus.OK);
			}
			service.save(socio, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.CREATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Socio creado exitósamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/actualizar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Socio socio, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			if (service.existsNotId(socio)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe un socio con la cédula " + socio.getCedula()),
						HttpStatus.OK);
			}
			service.save(socio, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.UPDATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Socio actualizado correctamente"),
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