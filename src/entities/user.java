/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nora
 */
public class user {
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String role;
    private String age;
    private String password;

    public user() {
    }

    public user(int id, String nom, String prenom, String email, String tel, String role, String age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.age= age;
    }

    public user(String nom, String prenom, String email, String tel, String role, String age, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.age = age;
        this.password = password;
    }
    
    
    public user(int id, String nom, String prenom, String email, String tel, String role, String age, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getRole() {
        return role;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAge(String adresse) {
        this.age = adresse;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", role=" + role + ", age=" + age + ", password=" + password + '}';
    }

       

        
    
}
