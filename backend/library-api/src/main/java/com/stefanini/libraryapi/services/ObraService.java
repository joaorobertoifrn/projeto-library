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

import com.stefanini.libraryapi.dto.ObraDTO;
import com.stefanini.libraryapi.dto.ObraNewDTO;
import com.stefanini.libraryapi.exceptions.AuthorizationException;
import com.stefanini.libraryapi.exceptions.DataIntegrityException;
import com.stefanini.libraryapi.exceptions.ObjectNotFoundException;
import com.stefanini.libraryapi.model.Obra;
import com.stefanini.libraryapi.model.enums.Perfil;
import com.stefanini.libraryapi.repositories.ObraRepository;
import com.stefanini.libraryapi.security.UserSS;

@Service
public class ObraService {

	@Autowired
	private ObraRepository repo;

	public Obra find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Obra> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Obra.class.getName()));
	}

	@Transactional
	public Obra insert(Obra obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Obra update(Obra obj) {
		Obra newObj = find(obj.getId());
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

	public List<Obra> findAll() {
		return repo.findAll();
	}

	public Page<Obra> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Obra fromDTO(ObraDTO objDto) {
		return new Obra(objDto.getId(), objDto.getNome(), objDto.getDescricao(),objDto.getDtPublicacao(), objDto.getDtExposicao());
	}

	public Obra fromDTO(ObraNewDTO objDto) {
		Obra Obra = new Obra(null, objDto.getNome(), objDto.getDescricao(), objDto.getDtPublicacao(), objDto.getDtExposicao());
		return Obra;
	}

	private void updateData(Obra newObj, Obra obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		newObj.setDtPublicacao(obj.getDtPublicacao());
		newObj.setDtExposicao(obj.getDtExposicao());
		
	}

}
