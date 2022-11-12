package ec.com.pakay.controller;

import ec.com.pakay.domain.Auditoria;
import ec.com.pakay.service.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

	@Autowired
	private IAuditoriaService service;

	@GetMapping(value = "/listar/{tabla}&{idTabla}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Auditoria>> listByEmpresa(@PathVariable("tabla") String tabla,
			@PathVariable("idTabla") Long idTabla) {
		List<Auditoria> obj = new ArrayList<>();
		try {
			obj = service.listByTablaAndIdTabla(tabla, idTabla);
		} catch (Exception e) {
			return new ResponseEntity<List<Auditoria>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Auditoria>>(obj, HttpStatus.OK);
	}
}
