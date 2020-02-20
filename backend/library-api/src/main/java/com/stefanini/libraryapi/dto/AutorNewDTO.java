package com.stefanini.libraryapi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.validation.AutorInsert;

@AutorInsert
public class AutorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento do nome é obrigatório")
	private String nome;
	
	@Email(message="Email inválido")
	private String email;

	private String sexo;

	@NotEmpty(message="Preenchimento da data de nascimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy kk:mm")
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;
	
	private Pais pais;
	
	private String cpf;

	public AutorNewDTO() {
	}
	
	public AutorNewDTO(Integer id, String nome, String email, String sexo, Date dtNascimento, Pais pais, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		this.pais = pais;
		this.cpf = cpf;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	
}
