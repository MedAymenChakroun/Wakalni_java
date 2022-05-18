/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author remo
 */
public class review {
    public int reviewid;
    public int note;
    public String commentaire;
    public int utilisateurid;
    public int produitid;
    public String nomclient;
    public String nomproduit;

    public review() {
    }

    public review(int note, String commentaire, int utilisateurid, int produitid) {
        this.note = note;
        this.commentaire = commentaire;
        this.utilisateurid = utilisateurid;
        this.produitid = produitid;
    }

    
    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getUtilisateurid() {
        return utilisateurid;
    }

    public void setUtilisateurid(int utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    @Override
    public String toString() {
        return "review{" + "reviewid=" + reviewid + ", note=" + note + ", commentaire=" + commentaire + ", utilisateurid=" + utilisateurid + ", produitid=" + produitid + ", nomclient=" + nomclient + ", nomproduit=" + nomproduit + '}';
    }
    
    



}