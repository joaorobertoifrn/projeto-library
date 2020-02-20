package com.stefanini.libraryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.libraryapi.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {


}
