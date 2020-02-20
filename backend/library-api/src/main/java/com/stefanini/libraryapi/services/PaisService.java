package com.stefanini.libraryapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.libraryapi.dto.PaisDTO;
import com.stefanini.libraryapi.dto.PaisNewDTO;
import com.stefanini.libraryapi.exceptions.AuthorizationException;
import com.stefanini.libraryapi.exceptions.DataIntegrityException;
import com.stefanini.libraryapi.exceptions.ObjectNotFoundException;
import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.model.enums.Perfil;
import com.stefanini.libraryapi.repositories.PaisRepository;
import com.stefanini.libraryapi.security.UserSS;

@Service
public class PaisService {

	@Autowired
	private PaisRepository repo;

	public Pais find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Pais> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pais.class.getName()));
	}

	@Transactional
	public Pais insert(Pais obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Pais update(Pais obj) {
		Pais newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public List<Pais> findAll() {
		return repo.findAll();
	}

	public Page<Pais> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Pais fromDTO(PaisDTO objDto) {
		return new Pais(objDto.getId(), objDto.getCodigo(), objDto.getNome());
	}

	public Pais fromDTO(PaisNewDTO objDto) {
		Pais Pais = new Pais(null, objDto.getCodigo(), objDto.getNome());
		return Pais;
	}

	private void updateData(Pais newObj, Pais obj) {
		newObj.setCodigo(obj.getCodigo());
		newObj.setNome(obj.getNome());
	}

}
