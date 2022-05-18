package services;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.sql.*;
//import java.sql.Date;
import java.util.ArrayList;
//import java.util.List;

import utilities.datasource;
import entities.panier;
import Interfaces.Ipanier;

public class panierservice implements Ipanier<panier>{
    private Statement ste;
    private PreparedStatement pste;
    Connection conn = datasource.getInstance().getCnx();
    
    @Override
    public float somme(){
    String req= "SELECT DISTINCT sum(prixprod) FROM panier p JOIN ProduitPanier pp ON pp.pproduitid = p.produitid JOIN produit pa ON pa.produitid = pp.pproduitid  ";
    float x = 0;
    try {
        
        ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               x=rs.getFloat("sum(prixprod)");
            }
    } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    return x;
    }

    @Override
    public void ajouter(panier pa/*,produit po, offre of, utilisateur u*/) {
        String req="INSERT INTO `panier`(`produitid`,`clientid`) VALUES(?,?)";
        System.out.println("req"+req);
        try {
            System.out.println("produitid : "+pa.getProduitid());
          //  System.out.println("offreid : "+pa.getOffreid());
            System.out.println("clientid : "+pa.getClienid());
            

            pste = conn.prepareStatement(req);
            pste.setInt(1,pa.getProduitid());
            pste.setInt(2,pa.getClienid());
         
            
            pste.executeUpdate();

            System.out.println("Panier ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override 
      public  int updateid(int i){
     String req="DELETE FROM `panier` WHERE `panierid`='"+i+"'";
     int x = 0 ;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                x = (rs.getInt("panierid"));
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    
    }
    
    

    @Override
    public void supprimer(String i,String j,Float k) {
        String req="DELETE FROM `panier` WHERE `nomproduit`='"+i+"' AND `typeprod`='"+j+"' AND prixprod="+k+"";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Panier Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        
    }
    

    public List<panier> afficher() {
        List<panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM `panier`";
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                        String ret = "SELECT prixprod FROM `panier` WHERE panierid="+rs.getInt("panierid")+";";

                ste = conn.createStatement();
            ResultSet rsp = ste.executeQuery(ret);
                panier pa = new panier();
                pa.setPanierid(rs.getInt("panierid"));
                pa.setProduitid(rs.getInt("produitid"));
                pa.setPrixprod(rsp.getFloat("prixprod"));
                pa.setClienid(rs.getInt("produitid"));
                paniers.add(pa);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paniers;
    }
    
    
    
    
    
     public String getnomproduit(int i) {
         //String req1 = "SELECT produitid from 'panier' p where p.panierid="+i+"";
         // save response from req1 into a list 
         // for each element of the list req1 do getproduct 
         String req = "SELECT DISTINCT nom FROM `produit` p JOIN `panier` pa ON p.produitid = pa.produitid WHERE p.produitid="+i+"";;
         String x = null;
          try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);        
            while(rs.next()){
            x=rs.getString("nom");
                
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x; 
     }
     
     
     
     
     
     
    @Override
    public double getprice(int i) {
         //String req1 = "SELECT produitid from 'panier' p where p.panierid="+i+"";
         // save response from req1 into a list 
         // for each element of the list req1 do getproduct 
         String req = "SELECT DISTINCT prix FROM `produit` p JOIN `panier` pa ON p.produitid = pa.produitid WHERE p.produitid="+i+"";
         
         float x = 0;
          try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);        
            while(rs.next()){ 
                x = rs.getFloat("prix");
                
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
     } 

    
}
