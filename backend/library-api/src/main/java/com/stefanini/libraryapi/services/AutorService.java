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

import com.stefanini.libraryapi.dto.AutorDTO;
import com.stefanini.libraryapi.dto.AutorNewDTO;
import com.stefanini.libraryapi.exceptions.AuthorizationException;
import com.stefanini.libraryapi.exceptions.DataIntegrityException;
import com.stefanini.libraryapi.exceptions.ObjectNotFoundException;
import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.model.enums.Perfil;
import com.stefanini.libraryapi.repositories.AutorRepository;
import com.stefanini.libraryapi.security.UserSS;

@Service
public class AutorService {

	@Autowired
	private AutorRepository repo;

	public Autor find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Autor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));
	}

	@Transactional
	public Autor insert(Autor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Autor update(Autor obj) {
		Autor newObj = find(obj.getId());
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

	public List<Autor> findAll() {
		return repo.findAll();
	}

	public Autor findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Autor obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Autor.class.getName());
		}
		return obj;
	}

	public Page<Autor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Autor fromDTO(AutorDTO objDto) {
		return new Autor(objDto.getId(), objDto.getNome(), objDto.getEmail(),objDto.getSexo(), objDto.getDtNascimento(), objDto.getPais(), objDto.getCpf());
	}

	public Autor fromDTO(AutorNewDTO objDto) {
		Autor Autor = new Autor(null, objDto.getNome(), objDto.getEmail(),objDto.getSexo(), objDto.getDtNascimento(), objDto.getPais(), objDto.getCpf());
		return Autor;
	}

	private void updateData(Autor newObj, Autor obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setSexo(obj.getEmail());
		newObj.setDtNascimento(obj.getDtNascimento());
		newObj.setPais(obj.getPais());
		newObj.setCpf(obj.getCpf());
	}

}
