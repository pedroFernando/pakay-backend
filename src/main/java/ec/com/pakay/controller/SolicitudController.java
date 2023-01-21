package ec.com.pakay.controller;

import ec.com.pakay.domain.Solicitud;
import ec.com.pakay.domain.dto.SolicitudDTO;
import ec.com.pakay.domain.dto.TransaccionConsulta;
import ec.com.pakay.service.ISolicitudService;
import ec.com.pakay.util.Respuesta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudController {

	private final ISolicitudService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Solicitud>> getAll() {
		log.info("Get all.");
		return  ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Solicitud> findById(@PathVariable Integer id) {
		log.info("Get by id. id=" + id);
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> create(@RequestBody Solicitud solicitud) {
		try {
			service.save(solicitud);
		} catch (Exception e) {
			System.out.println("Error registro prestamo: " +  e.getMessage());
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Préstamo creado exitósamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Solicitud solicitud, @PathVariable Integer id) {
		try {
			service.save(solicitud);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Préstamo actualizado correctamente"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable Integer id) {
		log.info("Delete. id=" + id);
		this.service.delete(id);
		return ResponseEntity.noContent().build();
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
