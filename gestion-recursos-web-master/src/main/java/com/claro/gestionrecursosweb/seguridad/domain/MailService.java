package com.claro.gestionrecursosweb.seguridad.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(String from, String to, String subject, String body) {

        String status = null;
        try {
            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom(from);
            InternetAddress address = new InternetAddress();
            address.setAddress(to);
            mail.setTo(address.toString());
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
            status = "ok";
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
            status = "Error";
        }

        return status;
    }
}
