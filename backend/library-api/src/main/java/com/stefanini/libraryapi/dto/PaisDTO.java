package com.stefanini.libraryapi.dto;

import java.io.Serializable;

import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.validation.AutorInsert;

@AutorInsert
public class PaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String codigo;
	
	private String nome;

	public PaisDTO() {
	}

	public PaisDTO(Pais obj) {
		id = obj.getId();
		codigo = obj.getCodigo();
		nome = obj.getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	
}
