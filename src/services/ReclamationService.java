/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Interfaces.IReclamationService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.reclamation;
import utilities.datasource;
/**
 *
 * @author remo
 */
public class ReclamationService implements IReclamationService<reclamation> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public ReclamationService() {
        conn = datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(reclamation r) {
        String c ="En cours";
        String req = "INSERT INTO `reclamation` (`sujet`, `contenu`, `clientid`, `commandeid`, `etat`) VALUE ('" + r.getSujet() +"','"+r.getContenu() +"','"
        +r.getClientid()+"','" +r.getReponse() +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("reclamation crée");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<reclamation> afficher() {
        List<reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM `reclamation`";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              reclamation r = new reclamation();
                r.setReclamationid(rs.getInt("reclamationid"));
                r.setSujet(rs.getString("sujet"));
                r.setContenu(rs.getString("contenu"));
                r.setClientid(rs.getInt("clientid"));
                r.setCommandeid(rs.getInt("commandeid"));
                r.setReponse(rs.getString("reponse"));
                r.setNomclient(rs.getString("nomclient"));
                reclamations.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reclamations;
    }

    @Override
    public void modifier(int i,String con,String suj) {
        System.out.println(i);
        String req="UPDATE `reclamation` SET `sujet`=? , `contenu`=?   WHERE `reclamationid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,suj);
            pste.setString(2,con);
            pste.executeUpdate();
            System.out.println("Reclamation bien modifié");
        } catch (SQLException ex) {
         System.out.println("Reclamation n'a pas été modifié");
         Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override

    public void supprimer(int i) {
           String req = "DELETE FROM `reclamation` WHERE `reclamationid` = '"+i+"' ";
    try {
        pste = conn.prepareStatement(req);
        pste.executeUpdate();
        System.out.println("Reclamation supprimé avec success");

    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE,null,ex);
         System.out.println("Reclamation non supprimé "+ ex);
    }
}
    @Override
    public List<reclamation> rechercher(int i) {
        List<reclamation> reclamations = new ArrayList<>();
        String req = "Select * FROM `reclamation` WHERE `clientid` = '"+i+"'";
        
         try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              reclamation r = new reclamation();
                r.setReclamationid(rs.getInt("reclamationid"));
                r.setSujet(rs.getString("sujet"));
                r.setContenu(rs.getString("contenu"));
                r.setClientid(rs.getInt("clientid"));
                r.setCommandeid(rs.getInt("commandeid"));
                r.setReponse(rs.getString("reponse"));
               // r.setNomclient(rs.getString("nomclient"));
                reclamations.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reclamations;
    }
     @Override
    public String getreponse(int i) {
        String req = "SELECT reponsenom FROM `reponse` r JOIN `reclamation` rc ON r.reclamationid = rc.reclamationid WHERE  r.reclamationid="+i+"";
        String x = null;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getString("reponsenom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
    @Override
    public void update(int i) {
        String req="UPDATE `reclamation` SET `reponse`=? WHERE `reclamationid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,getreponse(i));
            pste.executeUpdate();
          
        } catch (SQLException ex) {

         Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@Override
public int getid(String s,String c){
    int x = 0 ;
   String req="SELECT reclamationid FROM `reclamation` WHERE sujet = '"+s+"' AND contenu= '"+c+"'";
    try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getInt("reclamationid");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
@Override
    public String getnom(int i) {
        String req = "SELECT nom FROM `utilisateur` u JOIN `reclamation` rc ON u.id = rc.clientid WHERE  role=1 AND u.id="+i+"";
        String x = null;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getString("nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
      @Override
    public int getclientidtoken(int i)
    
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
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    

    }
    

