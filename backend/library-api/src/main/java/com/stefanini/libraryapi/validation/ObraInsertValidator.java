package com.stefanini.libraryapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.stefanini.libraryapi.dto.ObraNewDTO;
import com.stefanini.libraryapi.resources.exception.FieldMessage;

public class ObraInsertValidator implements ConstraintValidator<ObraInsert, ObraNewDTO> {

	@Override
	public void initialize(ObraInsert ann) {
	}

	@Override
	public boolean isValid(ObraNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getDtPublicacao() == null || objDto.getDtExposicao() == null) {
			list.add(new FieldMessage("dtPublicacao", "Data de Publicação é requerida"));
		} else if (!(objDto.getDtPublicacao() == null) || !(objDto.getDtExposicao() != null)) {
				list.add(new FieldMessage("dtPublicacao", "Data de Publicação é requerida"));
			} 

		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

