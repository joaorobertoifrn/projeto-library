package com.stefanini.libraryapi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.validation.UsuarioUpdate;

@UsuarioUpdate
public class ObraNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;

	@Length(max=240, message="O tamanho deve conter no máximo 240 caracteres")
	private String descricao;

	private Date dtPublicacao;

	private Date dtExposicao;
	
	public ObraNewDTO() {
	}

	public ObraNewDTO(Autor obj) {
		id = obj.getId();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public Date getDtExposicao() {
		return dtExposicao;
	}

	public void setDtExposicao(Date dtExposicao) {
		this.dtExposicao = dtExposicao;
	}
	
	
}
