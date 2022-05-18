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
import entities.Offre;
import utilities.datasource;
import Interfaces.IOffre;


/**
 *
 * @author lacht
 */
public class OffreService implements IOffre<Offre> {
     private Connection conn= datasource.getInstance().getCnx();
  private Statement ste;
  private PreparedStatement pste;
  

    @Override
    public void ajouter(Offre O) {
       String req = "INSERT INTO `offre` (`crid`,`nom`,`type`,`prix`,`datefin`) VALUE (?,?,?,?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, O.getCrid());
            pste.setString(2, O.getNom());
            pste.setString(3, O.getType());
            pste.setFloat(4, O.getPrix());
            pste.setDate(5, O.getDatefin());
            pste.executeUpdate();
            System.out.println("offre créée");
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void modifier(int i,Offre O) {
    
         System.out.println(O.getOffreid());
        String req="UPDATE `offre` SET `nom`=? , `type`=? ,`prix`=?,`datefin`=? WHERE `offreid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1, O.getNom());
           pste.setString(2, O.getType());
           pste.setFloat(3, O.getPrix());
           pste.setDate(4, O.getDatefin());
           pste.executeUpdate();
            System.out.println("Offre bien modifié");
        } catch (SQLException ex) {
         System.out.println("Offre n'a pas été modifié");
         Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void supprimer(int i) {
      String req = "DELETE FROM `Offre` WHERE `offreid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Offre supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("offre non supprimé "+ ex);
        }  
       
        
    }

    @Override
    public List<Offre> afficher() {
        List<Offre> offres = new ArrayList<>();
        String req = "SELECT * FROM `offre`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Offre o = new Offre();
          
                o.setOffreid(rs.getInt("offreid"));
                o.setNom(rs.getString(2));
                o.setType(rs.getString(3));
                o.setPrix(rs.getFloat(4));
                o.setCrid(rs.getInt(5));
                o.setDatefin(rs.getDate((6)));
                offres.add(o);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return offres;
    }
    
    public void recherchePparPrix(String prix){
      String req = "SELECT FROM * WHERE `prix`='"+prix+"'";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rst = pste.executeQuery();
            rst.last();
            int nbrRow = rst.getRow();
            if(nbrRow!=0){
                System.out.println("offre trouve");
            }else{
                System.out.println("offre non  trouve");
            
            }
            System.out.println("Offre supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("offre non supprimé "+ ex);
        }  
      
       
    } 

    @Override
public List<Offre> recherche(String s) {
        List<Offre> produits = new ArrayList<>();
        String req = "SELECT * FROM `offre` where nom like '%"+ s+"%'";
        System.out.println(req);
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
//            
            while(rs.next()){
                Offre o = new Offre();
//                c.setCommandeid(rs.getInt("commandeid"));
//                c.setClientid(rs.getInt("clientid"));
//                c.setRcid(rs.getInt("rcid"));
//                c.setPanierid(rs.getInt("panierid"));
//                c.setDatecreation(rs.getString("datecreation"));
//                c.setDateexpedition(rs.getString("dateexpedition"));
//                c.setDatearrivee(rs.getString("datearrivee"));
                o.setOffreid(rs.getInt("offreid"));
                o.setNom(rs.getString(2));
                o.setType(rs.getString(3));
                o.setCrid(rs.getInt(4));
                o.setPrix(rs.getFloat(5));
                produits.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produits;
}

}
