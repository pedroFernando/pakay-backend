package ec.com.pakay.controller;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.aut.Aplicacion;
import ec.com.pakay.domain.aut.UsuarioAuditoria;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.domain.dto.TransaccionDTO;
import ec.com.pakay.report.RpSocioEmpresa;
import ec.com.pakay.service.ITransaccionService;
import ec.com.pakay.util.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

	@Autowired
	private ITransaccionService service;

	private final static String TABLA = "transaccion";
	private final static String ENTIDAD = "Transaccion";
	
	@GetMapping(value = "/listar/{idEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaccion>> listByEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
		List<Transaccion> obj = new ArrayList<>();
		try {
			obj = service.listByEmpresa(idEmpresa);
		} catch (Exception e) {
			return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.OK);
	}
	
	@GetMapping(value = "/reporte-socio/{idSocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RpSocioEmpresa>> reporteSocio(@PathVariable("idSocio") Integer idSocio) {
		List<RpSocioEmpresa> obj = new ArrayList<>();
		try {
			obj = service.listSocio(idSocio);
		} catch (Exception e) {
			return new ResponseEntity<List<RpSocioEmpresa>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<RpSocioEmpresa>>(obj, HttpStatus.OK);
	}
	
	@GetMapping(value = "/reporte-empresa/{idEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RpSocioEmpresa>> reporteEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
		List<RpSocioEmpresa> obj = new ArrayList<>();
		try {
			obj = service.listEmpresa(idEmpresa);
		} catch (Exception e) {
			return new ResponseEntity<List<RpSocioEmpresa>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<RpSocioEmpresa>>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaccion> findById(@PathVariable("id") Integer id) {
		Transaccion obj = new Transaccion();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Transaccion>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Transaccion>(obj, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar-doc/{idEmpresa}&{cod}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaccion>> listByDoc(@PathVariable("idEmpresa") Integer idEmpresa, @PathVariable("cod") String cod) {
		List<Transaccion> obj = new ArrayList<>();
		try {
			obj = service.listByEmpresaAndDocumento(idEmpresa, cod);
		} catch (Exception e) {
			return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.OK);
	}

	@PostMapping(value = "/registrar/{idUser}&{ip}&{codApp}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Transaccion transaccion, @PathVariable("idUser") Integer idUser,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.save(transaccion, new Auditoria(TABLA, ENTIDAD, 0L, Auditoria.CREATE, "{}", ip, new Aplicacion(codApp),
					new UsuarioAuditoria(idUser)));
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Transacción creada exitósamente"),
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
	public ResponseEntity<TransaccionDTO> filtrar(@RequestBody TransaccionConsulta consulta) {
		TransaccionDTO obj = new TransaccionDTO();
		try {
			obj = service.filtrar(consulta);
		} catch (Exception e) {
			System.out.println("Transaccion controller: " + e.getMessage());
			return new ResponseEntity<TransaccionDTO>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<TransaccionDTO>(obj, HttpStatus.OK);
	}

}