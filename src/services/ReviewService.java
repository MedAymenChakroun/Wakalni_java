/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Interfaces.IReviewService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.review;
import utilities.datasource;
/**
 *
 * @author remo
 */
public class ReviewService implements IReviewService<review> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public ReviewService() {
        conn = datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(review r) {
        String req = "INSERT INTO `review` (`note`,`commentaire`,`utilisateurid`,`produitid`,`produitnom`,`clientnom`) VALUE ('" + r.getNote() +"','"+r.getCommentaire() +"','"+r.getUtilisateurid() +"','"+r.getProduitid() +"','"+getnomproduit(r.getProduitid()) +"','"+getnom(r.getUtilisateurid()) +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("review crée");
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<review> afficher() {
            List<review> reviews = new ArrayList<>();
            String req = "SELECT * FROM `review`";
            try {

                ste = conn.createStatement();
                ResultSet rs = ste.executeQuery(req);
    
                while(rs.next()){
                    review v = new review();
                    v.setReviewid(rs.getInt("reviewid"));
                    v.setNote(rs.getInt("note"));
                    v.setCommentaire(rs.getString("commentaire"));
                    v.setUtilisateurid(rs.getInt("utilisateurid"));
                    v.setProduitid(rs.getInt("produitid"));
                    v.setNomproduit("makloub");
                    v.setNomclient("ahmed");
                    reviews.add(v);
                }
    
            } catch (SQLException ex) {
                Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            return reviews;
    }
    @Override
    public void modifier(int i,String co,int no) {
        System.out.println(i);
        String req="UPDATE `review` SET  `commentaire`=? , `note`=?  WHERE `reviewid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,co);
            pste.setInt(2,no);
            pste.executeUpdate();
            System.out.println("review bien modifié");
        } catch (SQLException ex) {
         System.out.println("review n'a pas été modifié");
         Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override

    public void supprimer(int i) {
           String req = "DELETE FROM `review` WHERE `reviewid` = '"+i+"' ";
    try {
        pste = conn.prepareStatement(req);
        pste.executeUpdate();
        System.out.println("review supprimé avec success");

    } catch (SQLException ex) {
        Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE,null,ex);
         System.out.println("review non supprimé "+ ex);
    }
}
@Override
    public String getnom(int i) {
        String req = "SELECT nom FROM `utilisateur` u JOIN `review` rc ON u.id = rc.utilisateurid WHERE  role=1 AND u.id="+i+"";
        String x = null;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getString("nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
    @Override
    public String getnomproduit(int i) {
        String req = "SELECT nom FROM `produit` p JOIN `review` rc ON p.produitid = rc.produitid WHERE  p.produitid="+i+"";
        String x = null;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getString("nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
    @Override
    public int getidproduit(String i) {
        String req = "SELECT produitid FROM `produit`   WHERE  nom LIKE '%"+i+"%'";
        int x = 0;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getInt("produitid");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
    
    @Override
    public List<String> listeproduits(){
                  List<String> lists = new ArrayList<>();
            String req = "SELECT nom FROM `produit`";
            try {

                ste = conn.createStatement();
                ResultSet rs = ste.executeQuery(req);
    
                while(rs.next()){
                    String s = rs.getString("nom");
                   
                    lists.add(s);
                }
    
            } catch (SQLException ex) {
                Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            return lists;
    }
    @Override
    public int getclientidtoken()
    
    {
        int x = 0;
        String req = "Select id FROM utilisateur WHERE token=1";
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getInt("id");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    
    }


