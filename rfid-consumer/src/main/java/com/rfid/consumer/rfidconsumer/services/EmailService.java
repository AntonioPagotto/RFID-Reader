package com.rfid.consumer.rfidconsumer.services;

import com.rfid.consumer.rfidconsumer.entities.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    // Envia e-mail via JavaMailSender
    public void sendEmail(String to, String subject, Tag tag) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(getHtml(tag), true);
        emailSender.send(message);
    }

    // Montando HTML do e-mail
    private String getHtml(Tag savedTag){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'as' HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return "<html><head><style>" +
                "body { font-family: Arial, sans-serif; }" +
                "h1 { color: #007bff; }" +
                "table { border-collapse: collapse; width: 100%; }" +
                "th, td { text-align: left; padding: 8px; }" +
                "th { background-color: #007bff; color: white; }" +
                "</style></head><body>" +
                "<h1>Equipamento Rastreado</h1>" +
                "<p>O equipamento <strong>" + savedTag.getName() + "</strong> acabou de ser rastreado.</p>" +
                "<table>" +
                "<tr><th>Movimentação</th><th>Data e Hora</th></tr>" +
                "<tr><td>" + savedTag.getPlace() + "</td><td>" + formattedDateTime + "</td></tr>" +
                "</table></body></html>";
    }

}