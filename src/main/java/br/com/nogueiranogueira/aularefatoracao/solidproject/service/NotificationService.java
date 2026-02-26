package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    public void enviarBoasVindas(String email, String nome) {
        emailService.enviarEmail(
                email,
                "Bem-vindo ao nosso sistema!",
                "Olá " + nome + ", seu cadastro foi realizado com sucesso."
        );
    }
}
