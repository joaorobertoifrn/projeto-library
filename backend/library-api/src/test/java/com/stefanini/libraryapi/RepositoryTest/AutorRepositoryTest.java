package com.stefanini.libraryapi.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stefanini.libraryapi.model.Autor;
import com.stefanini.libraryapi.model.Pais;
import com.stefanini.libraryapi.repositories.AutorRepository;
import com.stefanini.libraryapi.repositories.PaisRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AutorRepositoryTest {
	
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	@DisplayName("Cadastro de Autor")
	public void createShouldPersistData() throws ParseException {
		// Autor(Integer id, String nome, String email, String sexo, Date dtNascimento, Pais pais, String cpf)
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);
		
		Autor autor = new Autor(null,"Gray Blazejewski","gblazejewski0@flickr.com","Masculino", sdf.parse("24/07/1992"), pais,"07778990008");
		this.autorRepository.save(autor);
		
		assertThat(autor.getId()).isNotNull();
		assertThat(autor.getNome()).isEqualTo("Gray Blazejewski");
		assertThat(autor.getEmail()).isEqualTo("gblazejewski0@flickr.com");
		assertThat(autor.getSexo()).isEqualTo("Masculino");
		assertThat(autor.getDtNascimento()).isEqualTo(sdf.parse("24/07/1992"));
		assertThat(autor.getPais()).isEqualTo(pais);
		assertThat(autor.getCpf()).isEqualTo("07778990008");

	}

	@Test
	@DisplayName("Delete de Autor")
	public void deleteShouldRemoveData() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);
		
		Autor autor = new Autor(null,"Gray Blazejewski","gblazejewski0@flickr.com","Masculino", sdf.parse("24/07/1992"), pais,"07778990008");
		this.autorRepository.save(autor);

		this.autorRepository.delete(autor);
		assertThat(autorRepository.findById(autor.getId())).isNotNull();
	}

	@Test
	@DisplayName("Update de Autor")
	public void updateShouldChangeAndPersistData() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);
		
		Autor autor = new Autor(null,"Gray Blazejewski","gblazejewski0@flickr.com","Masculino", sdf.parse("24/07/1992"), pais,"07778990008");
		this.autorRepository.save(autor);

		autor.setNome("Nome Alterado");
		autor.setEmail("email@email.com");
		autor = autorRepository.save(autor);
		
		assertThat(autorRepository.findById(autor.getId()));
	}

	@Test
	@DisplayName("Verifica se o Nome do Autor Foi Preenchido")
	public void createWhenNomeIsNullShouldThrowConstraintViolationException() throws ParseException {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Preenchimento do nome é obrigatório");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);

		Autor autor = new Autor(null,null,"gblazejewski0@flickr.com","Masculino", sdf.parse("24/07/1992"), pais,"07778990008");

		this.autorRepository.save(autor);
	}

	@Test
	@DisplayName("Verifica se o Email do Autor foi Preenchido")
	public void createWhenEmailIsNotValidShouldThrowConstraintViolationException() throws ParseException {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Email inválido");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);

		Autor autor = new Autor(null,"Gray Blazejewski","emailerrado.com","Masculino", sdf.parse("24/07/1992"), pais,"07778990008");

		this.autorRepository.save(autor);
		
	}

	@Test
	@DisplayName("Verifica se a Data de Nascimento foi Preenchida")
	public void createWhenDtNascimentoIsNullShouldThrowConstraintViolationException() throws ParseException {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Preenchimento da data de nascimento é obrigatório");

		Pais pais = new Pais(null,"BR","Brasil");
		this.paisRepository.save(pais);

		Autor autor = new Autor(null,"Gray Blazejewski","gblazejewski0@flickr.com","Masculino", null, pais,"07778990008");

		this.autorRepository.save(autor);
		
	}

}
