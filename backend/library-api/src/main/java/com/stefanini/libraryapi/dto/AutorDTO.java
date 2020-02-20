package com.stefanini.libraryapi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.validation.UsuarioUpdate;

@UsuarioUpdate
public class AutorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;

	private String sexo;

	@Temporal(TemporalType.DATE)
	private Date dtNascimento;
	
	private Pais pais;
	
	private String cpf;
	
	public AutorDTO() {
	}

	public AutorDTO(Autor obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		sexo = obj.getSexo();
		dtNascimento = obj.getDtNascimento();
		pais = obj.getPais();
		cpf = obj.getCpf();
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
