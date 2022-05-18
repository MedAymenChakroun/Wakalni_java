/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.*;
/**
 *
 * @author Nora
 */
public class datasource {

    private String url = "jdbc:mysql://localhost:3306/wakalni";
    private String username = "root";
    private String password = "";
    private Connection cnx; 
    private static datasource instance;
    
    private datasource() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("database connected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public static datasource getInstance() {
        if(instance == null){
            instance = new datasource();
        }
        return instance;
    }

}
