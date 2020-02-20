package com.stefanini.libraryapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Audited
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message="Preenchimento do nome é obrigatório")
	private String nome;

	@Email(message="Email inválido")
	@Column(unique = true)
	private String email;

	private String sexo;

	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message="Preenchimento da data de nascimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtNascimento;
	
	@ManyToOne
	@JoinColumn(name="pais")
	private Pais pais;
	
	@Column(unique = true)
	private String cpf;

	
	@ManyToMany
	@JoinTable(name = "AUTOR_OBRA",
		joinColumns = @JoinColumn(name = "autor_id"),
		inverseJoinColumns = @JoinColumn(name = "obra_id")
	)
	
	private List<Obra> obras = new ArrayList<>();


	public Autor() {
	}

	public Autor(Integer id, String nome, String email, String sexo, Date dtNascimento, Pais pais, String cpf) {
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
	
	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	
	}
}


/*
 * #### Autor
 - Nome - obrigatório
 - Sexo
 - E-mail - não obrigatório, deve ser validado caso preenchido (não pode haver dois cadastros com mesmo e-mail)
 - Data de nascimento - obrigatório, deve ser validada
 - País de origem - obrigatório (deve ser um país existente)
 - CPF - somente deve ser informado caso país de origem seja o Brasil, desta forma torna-se obrigatório. Deve ser validado (formatado e não pode haver dois cadastros com mesmo CPF)
 * */
