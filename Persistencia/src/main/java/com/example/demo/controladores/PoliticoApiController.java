package com.example.demo.controladores;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Politico;
import com.example.demo.entidades.dao.PoliticoDAO;

import org.springframework.web.bind.annotation.RequestMethod;
@RestController
@RequestMapping("/politicos")
public class PoliticoApiController {

	private Logger logger = LoggerFactory.getLogger(PoliticoApiController.class);
	
	@Autowired
	private PoliticoDAO daoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Politico>>getPoliticos(){
		List<Politico> politicos = new ArrayList<Politico>();
		try {
			 politicos = daoService.findAll();
		} catch (Exception e) {
			logger.error("DaoServiceException",e);
			return new ResponseEntity<List<Politico>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Politico>>(politicos,HttpStatus.OK);
	}
	
}
