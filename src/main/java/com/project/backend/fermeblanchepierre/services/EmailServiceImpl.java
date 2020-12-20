package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void confirmOrder(String email){

        DateTimeFormatter dtfFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = LocalDateTime.now().format(dtfFull);
        String deadline = LocalDateTime.now().plusDays(7).format(dtf);


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("fbp.contactpro@gmail.com");
        mail.setTo(email);
        mail.setSubject("Facture n°XXXXXXXXXX");
        mail.setText(
                "Chère cliente, cher client,\n\n" +
                "Nous vous remercions pour votre commande XXXXXXXXXXX effectuée le " + now + ", nous vous adressons ci-joint une facture d'un montant de XX,XX euros.\n\n" +
                "Voici le récapitulatif de votre commande :\n" +
                "\n\nVoici les informations de paiement :\n" +
                "Montant à payer : € XX,XX\n" +
                "Avant le : " + deadline + "\n" +
                "Sur le compte : BEXX XXXX XXXX XXXX\n" +
                "BIC : XXXXXXXX\n" +
                "Communication (la communication doit contenir votre n° de commande) : XXXXXXXXXXX\n\n" +
                "Cordialement,\n" +
                "Ferme de Blanche Pierre."
        );



        javaMailSender.send(mail);
    }

}
