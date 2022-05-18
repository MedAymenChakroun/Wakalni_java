/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;


import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

public class Javamail {
 
        public static void sendMailcc(String recepient) throws MessagingException {
        System.out.println("its getting sent");
        Properties properties = new Properties(); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "wakalnimail@gmail.com";
        String password = "Wakalni123";
        Session session = Session.getInstance(properties,new Authenticator() {
          @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);}
              });
      Message message = prepareMessagecc(session,myAccountEmail,recepient); 
        Transport.send(message);  
        System.out.println("Message sent");
    }
                public static void sendMailvv(String recepient) throws MessagingException {
        System.out.println("its getting sent");
        Properties properties = new Properties(); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "wakalnimail@gmail.com";
        String password = "Wakalni123";
        Session session = Session.getInstance(properties,new Authenticator() {
          @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);}
              });
      Message message = prepareMessagevv(session,myAccountEmail,recepient); 
        Transport.send(message);  
        System.out.println("Message sent");
    }
        public static void sendMailrr(String recepient) throws MessagingException {
        System.out.println("its getting sent");
        Properties properties = new Properties(); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "wakalnimail@gmail.com";
        String password = "Wakalni123";
        Session session = Session.getInstance(properties,new Authenticator() {
          @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);}
              });
      Message message = prepareMessagerr(session,myAccountEmail,recepient); 
        Transport.send(message);  
        System.out.println("Message sent");
    }
    
    private static Message prepareMessagecc(Session session,String myAccountEmail,String recepient){
      
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Á propos de votre Reclamation");
            message.setText("Votre reclamation est en cours de traitement.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Javamail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
    
}
    private static Message prepareMessagevv(Session session,String myAccountEmail,String recepient){
      
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Á propos de votre Reclamation");
            message.setText("Votre reclamation est validé.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Javamail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
    
    
}
    private static Message prepareMessagerr(Session session,String myAccountEmail,String recepient){
      
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Á propos de votre Reclamation");
            message.setText("Votre reclamation est refusé.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Javamail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
    
    
}
            public static void sendMailrev(String recepient) throws MessagingException {
        System.out.println("its getting sent");
        Properties properties = new Properties(); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "wakalnimail@gmail.com";
        String password = "Wakalni123";
        Session session = Session.getInstance(properties,new Authenticator() {
          @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);}
              });
      Message message = prepareMessagerev(session,myAccountEmail,recepient); 
        Transport.send(message);  
        System.out.println("Message sent");
    }
             private static Message prepareMessagerev(Session session,String myAccountEmail,String recepient){
      
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Merci pour votre review !");
            message.setText("Votre review est creé.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Javamail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
    
}

}
