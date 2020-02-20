package com.stefanini.libraryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.libraryapi.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>  {

	@Transactional(readOnly=true)
	Autor findByEmail(String email);

}
