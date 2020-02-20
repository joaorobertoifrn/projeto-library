package com.stefanini.libraryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.libraryapi.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

	public Pais findByNome(String nome);;

}
