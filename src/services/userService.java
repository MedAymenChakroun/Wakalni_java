/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.IService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.UserUtils;
import utilities.datasource;

/**
 *
 * @author Nora
 */
public class userService implements IService<user> {

    private PreparedStatement pste;
    UserUtils uu = new UserUtils();

    Connection conn = datasource.getInstance().getCnx();

    @Override
    public void ajouter(user u) {

        String req = "INSERT INTO `user` (`roles_id`,`firstname`,`lastname`, `age`, `email`, `password`, `phonenumber`, `password_java`) VALUE ('" + u.getRole() + "','" + u.getNom() + "','" + u.getPrenom()
                + "','" + u.getAge() + "','" + u.getEmail() + "','" + uu.cryptpass(u.getPassword()) + "','" + u.getTel() + "','"+uu.crypterPassword(u.getPassword())+"'";
        try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user créée");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouter2(user u) {
        String req = "INSERT INTO `user` (`roles_id`,`firstname`,`lastname`, `age`, `email`, `password`, `phonenumber`, `password_java`) VALUE (?,?,?,?,?,?,?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, u.getRole());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
            pste.setString(4, u.getAge());
            pste.setString(5, u.getEmail());
            pste.setString(6, uu.cryptpass(u.getPassword()));
            pste.setString(7, u.getTel());
            pste.setString(8, uu.crypterPassword(u.getPassword()));
            pste.executeUpdate();
            System.out.println("user créé");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(user u) {
        String req = "Update `user` set roles_id =? , firstname=? , lastname=? , age=? , email=? , password_java=? , phonenumber=? where id=?";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, u.getRole());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
            pste.setString(4, u.getAge());
            pste.setString(5, u.getEmail());
            pste.setString(6, uu.crypterPassword(u.getPassword()));
            pste.setString(7, u.getTel());
            pste.setInt(8, u.getId());
            pste.executeUpdate();
            System.out.println("user modifié");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public void modifier2(user u) {
        String req = "Update `user` set roles_id =? , firstname=? , lastname=? , age=? , email=? , password_java=? , phonenumber=? where id=?" ;
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, u.getRole());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
            pste.setString(4, u.getAge());
            pste.setString(5, u.getEmail());
            pste.setString(6, u.getPassword());
            pste.setString(7, u.getTel());
            pste.setInt(8, u.getId());
            pste.executeUpdate();
            System.out.println("user modifié");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(user u) {
        String req = "delete from `user` where id=?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, u.getId());
            pste.executeUpdate();
            System.out.println("user supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer2(int id) {
        String req = "delete from `user` where id=" + id;
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("user supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<user> afficher() {

        List<user> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setAge(rs.getString(7));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(11));
                u.setTel(rs.getString(8));
                u.setRole(rs.getString(10));
                users.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(users);
        return users;
    }

    public int verifierData(String id, String password) throws SQLException { //Login user 
        
        Argon2 argon2 = Argon2Factory.create();
        PreparedStatement stmt = null;
        ResultSet rst = null;
        int resultat = -1;
       
            String sql = "SELECT * FROM user WHERE email=? AND password_java=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, uu.crypterPassword(password));
            rst = stmt.executeQuery();
            if (rst.next()) {
                    resultat = rst.getShort("roles_id");
                }
            System.out.println(uu.crypterPassword(password));
            System.out.println(rst.getString("password_java"));
        
        return resultat;
    }

    public boolean getbyusernameandpassword(String email, String password) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rst = null;

        String sql = "SELECT * FROM user WHERE email=? AND password_java=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, uu.crypterPassword(password));
        rst = stmt.executeQuery();
        return (rst.next());
    }


    public int current_user() {

        PreparedStatement stmt = null;
        ResultSet rst = null;
        int id = 0;
        try {
            String sql = "SELECT id FROM user where token = 1 ;";
            stmt = conn.prepareStatement(sql);
            rst = stmt.executeQuery();
            rst.next();
            id = rst.getInt("id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public user afficher_user(int id) {

        user u = new user();
        String req = "SELECT * FROM `user` where id = " + id;
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setAge(rs.getString(7));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(11));
                u.setTel(rs.getString(8));
                u.setRole(rs.getString(10));
            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public List<user> search(String s) {
        List<user> users = new ArrayList<>();
        String req = "SELECT * FROM `user` where firstname like '%" + s + "%' or lastname like '%" + s + "%' or age like '%" + s + "%' or email like '%" + s + "%' or phonenumber like '%" + s + "%' or roles like '%" + s + "%'";
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setAge(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(11));
                u.setTel(rs.getString(7));
                u.setRole(rs.getString(11));
                users.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public user loadDataUser(int id) {
        user u = new user();
        String req = "SELECT * FROM `user` where id = " + id;
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setAge(rs.getString(7));
                u.setEmail(rs.getString(2));
                u.setTel(rs.getString(8));
                u.setRole(rs.getString(10));
            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;

    }

    public String Random6Digits() {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());

        int num = generator.nextInt(99999) + 99999;
        if (num < 100000 || num > 999999) {
            num = generator.nextInt(99999) + 99999;
            if (num < 100000 || num > 999999) {
                try {
                    throw new Exception("Unable to generate PIN at this time..");
                } catch (Exception ex) {
                    Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String numm = "" + num + "";
        return numm;
    }

    public boolean check_email(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = '" + email + "'";
        Statement ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req);
        return rs.next();

    }

    public user getUserByMail(String email) {
        user u = new user();
        String req = "SELECT * FROM `user` where email ='" + email + "'";
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(5));
                u.setPrenom(rs.getString(6));
                u.setAge(rs.getString(7));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(11));
                u.setTel(rs.getString(8));
                u.setRole(rs.getString(10));
            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean verifierCompteValide(String id, String password) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM user WHERE email=? AND password_java=? AND valide = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, uu.crypterPassword(password));
            rst = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rst.next();
    }

    public void validerCompte(String email) {

        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE user SET valide = 1 where email ='" + email + "'";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkUniqueEmail(String email) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM user WHERE email=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rst = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rst.next();

    }
    public void update_roles(){
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE `user`\n" +
                        "SET roles = CASE roles_id \n" +
                            "WHEN '0' THEN 'ROLE_ADMIN' \n" +
                            "WHEN '1' THEN 'ROLE_USER' \n" +
                            "WHEN '2' THEN 'ROLE_CHEF' \n" +
                            "WHEN '3' THEN 'ROLE_LIVREUR' \n" +
                            "ELSE roles\n" +
                            "END\n" +
                        "WHERE roles IN('0', '1', '2', '3');";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int getiduser(String email){
      PreparedStatement stmt = null;
        ResultSet rst = null;
        int id = 0;
        try {
            String sql = "SELECT id FROM user where email = '"+email+"';";
            stmt = conn.prepareStatement(sql);
            rst = stmt.executeQuery();
            rst.next();
            id = rst.getInt("id");
            System.out.println(id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;   
    }
}
