package com.stefanini.libraryapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.libraryapi.dto.DashboardDTO;
import com.stefanini.libraryapi.repositories.AutorRepository;
import com.stefanini.libraryapi.repositories.ObraRepository;

@RestController
@RequestMapping(value="/dashboard")
public class DashboardResource {
	
	@Autowired
	private AutorRepository autores;	

	@Autowired
	private ObraRepository obras;	


	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DashboardDTO> dashboard() {
		
		DashboardDTO dash = new DashboardDTO();
		dash.setTotalAutores(autores.count());
		dash.setTotalObras(obras.count());
		
		return  ResponseEntity.ok().body(dash);
	}
	
}
