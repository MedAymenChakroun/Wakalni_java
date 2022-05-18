/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Organisation;

/**
 *
 * @author dell
 */
public class sendSMS {
   public static final String ACCOUNT_SID = System.getenv("ACbbd60a6094499329eb69b069d95d053a");
    public static final String AUTH_TOKEN = System.getenv("83e26528cd595919120d22711d579582");

    public static void sendSMS(Organisation o) {
        Twilio.init("ACbbd60a6094499329eb69b069d95d053a", "83e26528cd595919120d22711d579582");
        Message message = Message.creator(new PhoneNumber("+21699366081"),
        new PhoneNumber("+19106599565"), 
        "Nom: "+o.getNom()+" Numero: "+o.getNumero()+" Email: "+o.getEmail()).create();
       

        System.out.println(message.getSid());
    }
    
}
