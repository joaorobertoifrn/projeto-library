package com.stefanini.libraryapi.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.stefanini.libraryapi.dto.AutorDTO;
import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.repositories.AutorRepository;
import com.stefanini.libraryapi.resources.exception.FieldMessage;

public class AutorUpdateValidator implements ConstraintValidator<AutorUpdate, AutorDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AutorRepository repo;
	
	@Override
	public void initialize(AutorUpdate ann) {
	}

	@Override
	public boolean isValid(AutorDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Autor aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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

