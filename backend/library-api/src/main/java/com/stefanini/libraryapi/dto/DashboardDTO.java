package com.stefanini.libraryapi.dto;

import java.io.Serializable;

public class DashboardDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long totalAutores;
	private Long totalObras;
	
    public DashboardDTO(){
    	
    }

	public DashboardDTO(Long totalAutores, Long totalObras) {
		super();
		this.totalAutores = totalAutores;
		this.totalObras = totalObras;
	}

	public Long getTotalAutores() {
		return totalAutores;
	}

	public void setTotalAutores(Long totalAutores) {
		this.totalAutores = totalAutores;
	}

	public Long getTotalObras() {
		return totalObras;
	}

	public void setTotalObras(Long totalObras) {
		this.totalObras = totalObras;
	}

   
}