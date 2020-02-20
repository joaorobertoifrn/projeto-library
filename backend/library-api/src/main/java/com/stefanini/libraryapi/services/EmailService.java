package com.stefanini.libraryapi.services;

import org.springframework.mail.SimpleMailMessage;

import com.stefanini.libraryapi.model.Usuario;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
