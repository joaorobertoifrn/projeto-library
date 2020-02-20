package com.stefanini.libraryapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stefanini.libraryapi.dto.AutorDTO;
import com.stefanini.libraryapi.dto.AutorNewDTO;
import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.services.AutorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/autores")
public class AutorResource {

	@Autowired
	private AutorService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna um registro do Autor", notes = "Retorna um Autor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Registro retornado com sucesso")
    })
	public ResponseEntity<Autor> find(@PathVariable Integer id) {
		Autor obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Autor> find(@RequestParam(value = "value") String email) {
		Autor obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Criar um novo Autor", notes = "Criar novo Autor", response = Autor.class )
	@ApiResponses({
        @ApiResponse(code = 201, message = "Autor inserido com sucesso")
    })
	public ResponseEntity<Void> insert(@Valid @RequestBody AutorNewDTO objDto) {
		Autor obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Atualizar o Autor", notes = "Atualizar o Autor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Autor atualizado com sucesso")
    })
	public ResponseEntity<Void> update(@Valid @RequestBody AutorDTO objDto, @PathVariable Integer id) {
		Autor obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deletar um Autor", notes = "Deletar um Autor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Autor deletado com sucesso")
    })
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Listar Todos registros do Autor", notes = "Listar Autor", response = Autor.class, responseContainer = "List" )
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Sucesso, lista de Autores"),
			@ApiResponse(code = 401, message = "Você não tem permissão para acessar esse recurso"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar esse recurso"),
		    @ApiResponse(code = 404, message = "Recurso não localizado") })

	public ResponseEntity<List<Autor>> findAll() {
		List<Autor> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<AutorDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Autor> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AutorDTO> listDto = list.map(obj -> new AutorDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
