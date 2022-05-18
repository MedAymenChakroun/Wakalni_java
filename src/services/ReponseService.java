/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Interfaces.IReponseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.reponse;
import utilities.datasource;
/**
 *
 * @author remo
 */
public class ReponseService implements IReponseService<reponse> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public ReponseService() {
        conn = datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(reponse r) {
        String req = "INSERT INTO `reponse` (`reclamationid`,`reponsenom`) VALUE ('"+ r.getReclamationid() +"','"+r.getReponsenom() +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("reponse crée");
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<reponse> afficher() {
        List<reponse> reponses = new ArrayList<>();
        String req = "SELECT * FROM `reponse`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                reponse r = new reponse();
                r.setReponseid(rs.getInt("reponseid"));
                r.setReclamationid(rs.getInt("reclamationid"));
                r.setReponsenom(rs.getString("reponsenom"));
                reponses.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reponses;
    }

    @Override
    public void modifier(int i,String s) {
        System.out.println(i);
        String req="UPDATE `reponse` SET  `reponsenom`=?   WHERE `reponseid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,s);
            pste.executeUpdate();
            System.out.println("Reponse bien modifié");
        } catch (SQLException ex) {
         System.out.println("Reponse n'a pas été modifié");
         Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override

    public void supprimer(int i) {
           String req = "DELETE FROM `reponse` WHERE `reponseid` = '"+i+"' ";
    try {
        pste = conn.prepareStatement(req);
        pste.executeUpdate();
        System.out.println("Reponse supprimé avec success");

    } catch (SQLException ex) {
        Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE,null,ex);
         System.out.println("Reponse non supprimé "+ ex);
    }
}
    @Override
    public void repvalider(int i){
               String req = "UPDATE `reponse` SET  `reponsenom`='Validé'   WHERE `reclamationid`='"+i+"'";
    try {
        pste = conn.prepareStatement(req);
        pste.executeUpdate();
        System.out.println("Reponse modifié");

    } catch (SQLException ex) {
        Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE,null,ex);
         System.out.println("Reponse non modifié "+ ex);
    }    
    }
    
    @Override
    public void reprefuser(int i){
               String req = "UPDATE `reponse` SET  `reponsenom`='Refusé'   WHERE `reclamationid`='"+i+"'";
    try {
        pste = conn.prepareStatement(req);
        pste.executeUpdate();
        System.out.println("Reponse modifié");

    } catch (SQLException ex) {
        Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE,null,ex);
         System.out.println("Reponse non modifié "+ ex);
    }

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
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
}
