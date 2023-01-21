package ec.com.pakay.controller;

import ec.com.pakay.domain.Socio;
import ec.com.pakay.service.ISocioService;
import ec.com.pakay.util.Respuesta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/socio")
public class SocioController {

	@Autowired
	private ISocioService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Socio>> getAll() {
		log.info("Get all.");
		return ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Socio> getById(@PathVariable("id") Integer id) {
		log.info("Get by id. id=" + id);
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Socio socio) {
		try {
			if (service.exists(socio)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe un socio con la cédula " + socio.getCedula()),
						HttpStatus.OK);
			}
			service.save(socio);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Socio creado exitósamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Socio socio, @PathVariable Integer id) {
		try {
			if (service.existsNotId(socio)) {
				return new ResponseEntity<Respuesta>(
						Respuesta.getInstance().setCodigo(Respuesta.ERROR_REGISTRO_DUPLICADO)
								.setMensaje("Ya existe un socio con la cédula " + socio.getCedula()),
						HttpStatus.OK);
			}
			service.save(socio);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Socio actualizado correctamente"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable Integer id) {
		log.info("Delete. id=" + id);
		this.service.delete(id);
		return ResponseEntity.ok(Respuesta.getInstance().setMensaje("Eliminado correctamente"));
	}

}