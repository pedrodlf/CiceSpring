package com.example.demo.controladores;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Politico;
import com.example.demo.entidades.dao.PoliticoDAO;

import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/politicos")
public class PoliticoApiController {

	private Logger logger = LoggerFactory.getLogger(PoliticoApiController.class);

	@Autowired
	private PoliticoDAO daoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Politico>> getPoliticos() {
		List<Politico> politicos = new ArrayList<Politico>();
		try {
			politicos = daoService.findAll();
		} catch (Exception e) {
			logger.error("DaoServiceException", e);
			return new ResponseEntity<List<Politico>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (politicos.isEmpty() || politicos.size() < 1)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Politico>>(politicos, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Politico> getPoliticoId(
			@ApiParam(value = "Id del politico", required = true) @PathVariable("id") String idEmpleado) {
		Politico p = null;

		try {
			p = daoService.findOne(Long.parseLong(idEmpleado));
		} catch (Exception e) {
			logger.error("DaoServiceException", e);
			return new ResponseEntity<Politico>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (p == null)
			return new ResponseEntity<Politico>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Politico>(p, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> politicosPost(
			@ApiParam(value = "Datos del Politico", required = true) @Valid @RequestBody Politico politico) {

		try {
			daoService.save(politico);
		} catch (Exception e) {
			logger.error("DaoServiceException", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> politicoIdPut(
			@ApiParam(value = "Id del politico", required = true) @PathVariable("id") String idPolitico,
			@ApiParam(value = "Nombre de Politico") @RequestParam(value = "nombre", required = false) String nombre,
			@ApiParam(value = "Apellido de Politico") @RequestParam(value = "apellido", required = false) String apellido,
			@ApiParam(value = "Cargo de Politico") @RequestParam(value = "cargo", required = false) String cargo,
			@ApiParam(value = "Es Corrupto el politico") @RequestParam(value = "corrupto", required = false) Boolean corrupto,
			@ApiParam(value = "Cualidad de Politico") @RequestParam(value = "cualidad", required = false) String cualidad) {
		Politico p = null;
		try {
			p = daoService.getOne(Long.parseLong(idPolitico));

			if (nombre != null)
				p.setNombre(nombre);
			if (apellido != null)
				p.setApellido(apellido);
			if (cargo != null)
				p.setCargo(cargo);
			if (corrupto != null)
				p.setCorrupto(corrupto);
			if (cualidad != null)
				p.setCualidad(cualidad);
			daoService.save(p);
		} catch (Exception e) {
			logger.error("DaoServiceException", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> politicosDelete(
			@ApiParam(value = "Id del politico", required = true) @PathVariable("id") String idPolitico) {
			
		try {
			daoService.delete(Long.parseLong(idPolitico));
		} catch (Exception e) {
			logger.error("DaoServiceException", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
