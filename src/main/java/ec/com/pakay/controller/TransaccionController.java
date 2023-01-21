package ec.com.pakay.controller;

import ec.com.pakay.domain.Transaccion;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.domain.dto.TransaccionDTO;
import ec.com.pakay.report.RpSocioEmpresa;
import ec.com.pakay.service.ITransaccionService;
import ec.com.pakay.util.Respuesta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/transaccion")
public class TransaccionController {

	private final ITransaccionService service;
	
	@GetMapping()
	public ResponseEntity<List<Transaccion>> getAll() {
		log.info("Get all.");
		return ResponseEntity.ok(this.service.findAll());
	}
	
	@GetMapping(value = "/reporte-socio/{idSocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RpSocioEmpresa>> reporteSocio(@PathVariable("idSocio") Integer idSocio) {
		log.info("Reporte Socio.");
		return ResponseEntity.ok(this.service.listSocio(idSocio));
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

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaccion> findById(@PathVariable("id") Integer id) {
		Transaccion obj = new Transaccion();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Transaccion>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Transaccion>(obj, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar-doc/{cod}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaccion>> listByDoc(@PathVariable("cod") String cod) {
		List<Transaccion> obj = new ArrayList<>();
		try {
			obj = service.listByDocumento(cod);
		} catch (Exception e) {
			return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Transaccion>>(obj, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Respuesta> registrar(@RequestBody Transaccion transaccion) {
		try {
			service.save(transaccion);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Transacción creada exitósamente"),
				HttpStatus.OK);
	}


	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable Integer id) {
		try {
			service.delete(id);
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