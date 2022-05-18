/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;




/**
 *
 * @author lacht
 */
public class Offre {
    private int offreid,crid;
    private String nom;
    private String type;
    private Float prix;
    private Date datefin;
    
    public Offre() {
    }

    public Offre(int offreid, int crid, String nom, String type, Float prix, Date datefin) {
        this.offreid = offreid;
        this.crid = crid;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
    }

    public int getOffreid() {
        return offreid;
    }

    public void setOffreid(int offreid) {
        this.offreid = offreid;
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

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Offre{" + "offreid=" + offreid + ", crid=" + crid + ", nom=" + nom + ", type=" + type + ", prix=" + prix + ", datefin=" + datefin + '}';
    }
    

    

}
