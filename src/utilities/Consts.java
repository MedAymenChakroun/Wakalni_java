/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.regex.Pattern;

/**
 *
 * @author ibeno
 */
public final class Consts {

    public static final String CV_PATH = "C:\\Users\\malek guemri\\Desktop\\freeetudiant\\Uploads\\CV\\";
    public static final String IMG_PATH_LOAD = "file:///C:\\Users\\malek guemri\\Desktop\\freeetudiant\\Uploads\\Images\\";
    public static final String IMG_PATH = "C:\\Users\\malek guemri\\Desktop\\freeetudiant\\Uploads\\Images";

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME_REGEX = Pattern.compile("^[a-zA-Z]{3,30}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

    private Consts() {

    }
}
