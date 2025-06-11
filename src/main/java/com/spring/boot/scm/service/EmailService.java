package com.spring.boot.scm.service;

import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String body) {
        boolean result = false;
        String from = "alamin000125@gmail.com";
        String password = "owmd duus xnjj ufsv"; // Use environment var or secure storage

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("✅ Email sent successfully!");
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send email.");
        }
        return result;
    }
}
