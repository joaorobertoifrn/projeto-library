package com.stefanini.libraryapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.stefanini.libraryapi.dto.AutorNewDTO;
import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.repositories.AutorRepository;
import com.stefanini.libraryapi.repositories.PaisRepository;
import com.stefanini.libraryapi.resources.exception.FieldMessage;
import com.stefanini.libraryapi.services.validation.utils.BR;

public class AutorInsertValidator implements ConstraintValidator<AutorInsert, AutorNewDTO> {

	@Autowired
	private AutorRepository repo;

	@Autowired
	private PaisRepository repoPais;

	
	@Override
	public void initialize(AutorInsert ann) {
	}

	@Override
	public boolean isValid(AutorNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Pais pais = repoPais.findByNome(objDto.getPais().getNome());
		if (pais != null) {
			list.add(new FieldMessage("pais", "Nome do Pais inválido"));
		}
		
		if (objDto.getPais().getNome().contentEquals("Brasil")) {
			if (!BR.isValidCPF(objDto.getCpf())) {
				list.add(new FieldMessage("cpf", "CPF inválido"));
			}
		}

		Autor aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

