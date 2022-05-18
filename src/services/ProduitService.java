/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.produit;
import utilities.datasource;
import Interfaces.IProduit;


/**
 *
 * @author lacht
 */
   public class ProduitService implements IProduit<produit> {
    
private Connection conn= datasource.getInstance().getCnx();
  private Statement ste;
  private PreparedStatement pste;



 



    @Override
    public void ajouter(produit P) {
          String req = "INSERT INTO `produit` (`nom`,`type`,`crid`,`prix`) VALUE (?,?,?,?)";
       try {
            pste = conn.prepareStatement(req);
            pste.setString(1, P.getNom());
            pste.setString(2, P.getType());
            pste.setInt(3, P.getCrid());
            pste.setFloat(4, P.getPrix());
            pste.executeUpdate();
            System.out.println("produit créée");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int i,produit P) {
    
         System.out.println(P.getProduitid());
        String req="UPDATE `produit` SET `nom`=? , `type`=? ,`prix`=? WHERE `produitid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1, P.getNom());
           pste.setString(2, P.getType());
           pste.setFloat(3, P.getPrix());
           pste.executeUpdate();
            System.out.println("Produit bien modifié");
        } catch (SQLException ex) {
         System.out.println("Produit n'a pas été modifié");
         Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int i) {
      String req = "DELETE FROM `Produit` WHERE `produitid`= '"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Produit supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Produit non supprimé "+ ex);
        }   
    }
     
    @Override
    public List<produit> afficher() {
          List<produit> produits = new ArrayList<>();
        String req = "SELECT * FROM `Produit`";
        
        try{
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();;
            while(rs.next()){
                produit p = new produit();
                p.setProduitid(rs.getInt("produitid")); //p.setProduitid(rs.getInt("produitid")
                p.setNom(rs.getString("nom"));//p.setNom(rs.getString(2));
                p.setType(rs.getString("type"));//p.setType(rs.getString(3));
                p.setCrid(rs.getInt("crid"));//p.setCrid(rs.getInt(4));
                p.setPrix(rs.getFloat("prix"));   //p.setPrix(rs.getFloat(5));
               // System.out.println();
                produits.add(p);
            }
            } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }
 

@Override
public List<produit> recherche(String s) {
        List<produit> produits = new ArrayList<>();
        String req = "SELECT * FROM `produit` where nom like '%"+ s+"%'";
        System.out.println(req);
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
//            
            while(rs.next()){
                produit p = new produit();
//                c.setCommandeid(rs.getInt("commandeid"));
//                c.setClientid(rs.getInt("clientid"));
//                c.setRcid(rs.getInt("rcid"));
//                c.setPanierid(rs.getInt("panierid"));
//                c.setDatecreation(rs.getTimestamp("datecreation"));
//                c.setDateexpedition(rs.getTimestamp("dateexpedition"));
//                c.setDatearrivee(rs.getTimestamp("datearrivee"));
                p.setProduitid(rs.getInt("produitid"));
                p.setNom(rs.getString(2));
                p.setType(rs.getString(3));
                p.setCrid(rs.getInt(4));
                p.setPrix(rs.getFloat(5));
                produits.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produits;
}

  

}
