package com.stefanini.libraryapi.model;

import java.util.Calendar;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(AuditoriaListener.class)
public class Auditoria extends DefaultRevisionEntity {

	private static final long serialVersionUID = 1L;
	private String nomeUsuario;
	private Calendar dataHora;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

}
