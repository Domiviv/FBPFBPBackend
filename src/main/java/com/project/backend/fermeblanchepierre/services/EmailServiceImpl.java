package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void confirmOrder(String email, Order order, List<Stock> stocks){

        DecimalFormat df = new DecimalFormat("#.##");
        DateTimeFormatter dtfFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = LocalDateTime.now().format(dtfFull);
        String deadline = LocalDateTime.now().plusDays(7).format(dtf);

        String itemList = "";
        Double total = 0.0;

        for(Stock stock : stocks){
            total = total + stock.getItem().getPrice();
            itemList = itemList + "- " + stock.getItem().toString() + "\n";
        }


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("fbp.contactpro@gmail.com");
        mail.setTo(email);
        mail.setSubject("Confirmation de votre commande portant le numéro " + order.getIdOrder());
        mail.setText(
                "Chère cliente, cher client,\n\n" +
                "Nous vous remercions pour votre commande (portant le numéro " + order.getIdOrder() + ") effectuée le " + now + ", nous vous adressons ci-joint une facture d'un montant de " + df.format(total)+ " euros.\n\n" +
                "Voici le récapitulatif de votre commande :\n" + itemList +
                "\n\nVoici les informations de paiement :\n" +
                "Montant total à payer : € " + df.format(total) + "\n" +
                "Avant le : " + deadline + "\n" +
                "Sur le compte : BEXX XXXX XXXX XXXX\n" +
                "BIC : XXXXXXXX\n" +
                "Communication (la communication doit contenir votre n° de commande) : " + order.getIdOrder() + "\n\n" +
                "Cordialement,\n" +
                "Ferme de Blanche Pierre."
        );
        javaMailSender.send(mail);
    }

}
