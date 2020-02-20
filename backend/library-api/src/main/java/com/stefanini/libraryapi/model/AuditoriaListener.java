package com.stefanini.libraryapi.model;

import java.util.Calendar;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditoriaListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		Auditoria auditoria = (Auditoria) revisionEntity;
		auditoria.setNomeUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		// 0 (ADD), 1 (MOD) and 2 (DEL)
		auditoria.setDataHora(Calendar.getInstance());
	}

}
