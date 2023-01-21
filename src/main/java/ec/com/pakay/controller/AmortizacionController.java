package ec.com.pakay.controller;

import ec.com.pakay.domain.Amortizacion;
import ec.com.pakay.domain.Solicitud;
import ec.com.pakay.domain.dto.AmortizacionConsulta;
import ec.com.pakay.service.IAmortizacionService;
import ec.com.pakay.util.Respuesta;
import ec.com.pakay.util.TablaAmortizacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/amortizacion")
public class AmortizacionController {

	private final IAmortizacionService service;

	@GetMapping(value = "/solicitud/{idSolicitud}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Amortizacion>> listBySolicitud(@PathVariable("idSolicitud") Integer idSolicitud) {
		List<Amortizacion> obj = new ArrayList<>();
		try {
			Solicitud sol = new Solicitud();
			sol.setId(idSolicitud);
			obj = service.listBySolicitud(sol);
		} catch (Exception e) {
			return new ResponseEntity<List<Amortizacion>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Amortizacion>>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Amortizacion> findById(@PathVariable("id") Integer id) {
		Amortizacion obj = new Amortizacion();
		try {
			obj = service.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Amortizacion>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Amortizacion>(obj, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> actualizar(@RequestBody Amortizacion amortizacion, @PathVariable("id") Integer idAmortizacion,
			@PathVariable("ip") String ip, @PathVariable("codApp") String codApp) {
		try {
			service.save(amortizacion);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Amortizacion actualizada correctamente"),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/pagar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> pagar(@RequestBody Amortizacion amortizacion) {
		try {
			service.pagar(amortizacion);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance().setMensaje("Amortizacion actualizada correctamente"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminar(@PathVariable Integer id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<Respuesta>(Respuesta.getInstance().setCodigo(Respuesta.ERROR_INTERNO),
					HttpStatus.OK);
		}
		return new ResponseEntity<Respuesta>(Respuesta.getInstance(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/simular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Amortizacion>> filtrar(@RequestBody AmortizacionConsulta consulta) {
		List<Amortizacion> obj = new ArrayList<>();
		try {
			TablaAmortizacion tabla = new TablaAmortizacion();
			obj = tabla.crearTablaAmortizacion(consulta.getClase(), consulta.getTipo(), consulta.getMonto(), consulta.getInteres(), consulta.getPlazo(), consulta.getFecha());
		} catch (Exception e) {
			System.out.println("Amortizacion controller simular: " + e.getMessage());
			return new ResponseEntity<List<Amortizacion>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Amortizacion>>(obj, HttpStatus.OK);
	}

}
