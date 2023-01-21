package ec.com.pakay.controller;

import ec.com.pakay.domain.Caja;
import ec.com.pakay.domain.dto.CajaDTO;
import ec.com.pakay.service.ICajaService;
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
@RequestMapping("/caja")
public class CajaController {

	private final ICajaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Caja>> findAll() {
		log.info("Get all");
		return ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Caja> findById(@PathVariable("id") Integer id) {
		log.info("Get by id. id=" + id);
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> registrar(@RequestBody Caja caja) {
		log.info("Save. id=" + caja.getId());
		service.save(caja);
		return new ResponseEntity<>(Respuesta.getInstance().setMensaje("Caja creada correctamente"),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Caja caja, @PathVariable("id") Integer id) {
		log.info("Update. id=" + id);
		service.save(caja);
		return new ResponseEntity<>(Respuesta.getInstance().setMensaje("Caja actualizada correctamente"),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/transferir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> transferir(@RequestBody CajaDTO cajaDTO) {
		log.info("Transfer. idIn = " + cajaDTO.getCajaIngreso().getId() + " idOut = " + cajaDTO.getCajaEgreso().getId());
		service.transferir(cajaDTO);
		return new ResponseEntity<>(Respuesta.getInstance().setMensaje("Transferencia realizada correctamente"),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/egreso-ingreso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> egresoIngreso(@RequestBody CajaDTO cajaDTO) {
		log.info("InOut. idIn = " + cajaDTO.getCajaIngreso().getId() + " idOut = " + cajaDTO.getCajaEgreso().getId());
		service.egresoIngreso(cajaDTO);
		return new ResponseEntity<>(Respuesta.getInstance().setMensaje("Caja actualizada correctamente"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable("id") Integer id) {
		log.info("Delete. id=" + id);
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
