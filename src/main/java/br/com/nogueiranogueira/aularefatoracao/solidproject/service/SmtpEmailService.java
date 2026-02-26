package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SmtpEmailService implements EmailService {

    @Override
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teste", "senha");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("teste@teste.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso para: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
