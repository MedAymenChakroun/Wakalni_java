/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author lacht
 */
public class produit {
   
    private int produitid,crid;
    private String nom;
    private String type;
    private Float prix;

    public produit() {
    }

    public produit(int id,  String nom, String type,int crid, Float prix) {
        this.produitid = id;
        this.crid = crid;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }

     public produit(  String nom, String type,int crid, Float prix) {
//        this.produitid = id;
        this.crid = crid;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }
    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public int getCrid() {
        return crid;
    }

    public void setCrid(int crid) {
        this.crid = crid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "produitid=" + produitid + ", crid=" + crid + ", nom=" + nom + ", type=" + type + ", prix=" + prix + '}';
    }
    
    
    
}
