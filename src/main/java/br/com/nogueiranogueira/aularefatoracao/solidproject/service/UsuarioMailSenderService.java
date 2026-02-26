package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMailSenderService {

    @Autowired
    private EmailService emailService; // DIP: depende da abstração, não da implementação

    public void enviarEmailBoasVindas(String email) {
        String assunto = "Bem-vindo ao nosso sistema!";
        String mensagem = "Olá! Obrigado por se cadastrar em nosso sistema.";
        emailService.enviarEmail(email, assunto, mensagem);
    }
}
