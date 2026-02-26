package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

public interface EmailService {
    void enviarEmail(String destinatario, String assunto, String mensagem);
}
