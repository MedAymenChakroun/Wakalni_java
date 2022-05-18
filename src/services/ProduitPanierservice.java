/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.panier;
import services.panierservice;
import java.sql.*;
import utilities.datasource;
import entities.ProduitPanier;
import Interfaces.IProduitPanier;
import entities.produit;

public class ProduitPanierservice implements IProduitPanier{
    
    
    private Statement ste;
    private PreparedStatement pste;
    Connection conn = datasource.getInstance().getCnx();
    
    
     @Override
    public void ajouter(ProduitPanier pp) {
        /*String req = "select from produitpanier (`ppanierid`,`pproduitid`) VALUE ('44','"+pp.getProduitid() +"')";*/
    String req= "SELECT p.produitid FROM Produit p JOIN ProduitPanier pp ON pp.pproduitid = p.produitid JOIN Panier pa ON pa.panierid = pp.ppanierid  ";   
        System.out.println("request"+req);
        try {

            ste = conn.createStatement();
            ste.executeQuery(req);
            System.out.println("fetch success");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitPanierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    

    @Override
    public List<produit> jointure(/*int i*/){
    List<produit> produits = new ArrayList<>();
    String req= "SELECT DISTINCT nom,typeprod,quantite,prixprod FROM panier p JOIN ProduitPanier pp ON pp.pproduitid = p.produitid JOIN produit pa ON pa.produitid = pp.pproduitid  ";   /*pa.clientid ='"+i+"'*/
    try {
        ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                produit p = new produit();
                p.setNom(rs.getString("nom"));
                p.setType(rs.getString("typeprod"));
                p.setCrid(rs.getInt("quantite"));
                p.setPrix(rs.getFloat("prixprod"));
                produits.add(p);
                
            }
    } catch (SQLException ex) {
            Logger.getLogger(ProduitPanierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    return produits;
    }
     public ResultSet tri_produit() {
         
       try {
            PreparedStatement req = conn.prepareStatement("SELECT * FROM produit ORDER BY nom");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    
}
}
