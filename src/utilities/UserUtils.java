/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.security.MessageDigest;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javax.xml.bind.DatatypeConverter;
import static utilities.Consts.VALID_EMAIL_ADDRESS_REGEX;
import static utilities.Consts.VALID_NAME_REGEX;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.user;

/**
 *
 * @author ibeno
 */
public class UserUtils {

    Connection cnx;
    Argon2 argon2 = Argon2Factory.create();

    public UserUtils() {
        cnx = datasource.getInstance().getCnx();
    }

    public String crypterPassword(String password) {
        String hashValue = "";
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        return hashValue;
    }

    public boolean verifpass(String password, String hash) {

        
        char[] ch = password.toCharArray();

        try {

            //hash = argon2.hash(10, 65536, 1, ch);
            // Verify password
            if (argon2.verify(hash, password.toCharArray())) {
                return true;
            } else {
                return false;
            }

        } finally {
            // Wipe confidential data
            argon2.wipeArray(ch);
        }
    }

    public String cryptpass(String pass) {

        String hash;
        try {

            hash = argon2.hash(10, 65536, 1,pass.toCharArray());

        } finally {
            // Wipe confidential data
            argon2.wipeArray(pass.toCharArray());
        }

        return hash;
    }

    public boolean testNom(String nom) {

        Matcher matcher = VALID_NAME_REGEX.matcher(nom);
        return matcher.find();

    }

    public boolean testNumTelephonique(String tel) {
        int i;
        String[] tab = {"0", "1", "4", "6", "8"};
        for (i = 0; i < tab.length; i++) {
            if (tel.charAt(0) == tab[i].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public boolean testTel(String tel) {

        int i, length;
        length = tel.length();
        if (length != 8) {
            return false;
        }
        for (i = 0; i < length; i++) {

            if ((!(tel.charAt(i) >= '0' && tel.charAt(i) <= '9')) || (testNumTelephonique(tel) == false)) {
                return false;
            }
        }
        return true;

    }

    public boolean testEmail(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }

    /*public boolean testPassword(String password) {
     Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
     return matcher.find();
     }*/
    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public boolean check_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }

    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }
}
