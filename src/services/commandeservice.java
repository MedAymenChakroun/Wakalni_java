

package services;
import Interfaces.Icommande;
import java.util.logging.Logger;


import java.sql.*;
//import java.sql.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import utilities.datasource;
import entities.commande;
import entities.*;
import entities.user;

    public class commandeservice implements Icommande<commande>{
    private Statement ste;
    private PreparedStatement pste;
    Connection conn = datasource.getInstance().getCnx();


    
@Override
public void ajouter(commande c/*, utilisateur u,,panier pa*/) {
        String req="INSERT INTO `commande`(`datecreation`,`dateexpedition`,`datearrivee`,`clientid`,`panierid`,`nomclient`) VALUES(?,?,?,?,?,?)";
        try {
            System.out.println("datecreation : "+c.getDatecreation());
            System.out.println("dateexpedition : "+c.getDateexpedition());
            System.out.println("datearrivee : "+c.getDatearrivee());
            System.out.println("clientid : "+c.getClientid());
            System.out.println("panierid : "+c.getPanierid());
            System.out.println("nomclient :"+getnomclient(c.getClientid()));
           

            pste = conn.prepareStatement(req); 
            pste.setTimestamp(1,c.getDatecreation());
            pste.setTimestamp(2,c.getDateexpedition());
            pste.setTimestamp(3,c.getDatearrivee());
            pste.setInt(4,c.getClientid());
            pste.setInt(5,c.getPanierid());
            pste.setString(6,getnomclient(c.getClientid()));
            pste.executeUpdate();

            System.out.println("Commande ajoutée");

        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
@Override
public void modifier(int i,commande c) {
        String req="UPDATE `commande` SET `Datecreation`=TIMESTAMP,`Dateexpedition`=?,`Datearrivee`=?, WHERE `commandeid`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,"FIXED DATE");
            pste.setString(2,"FIXED DATE");
            pste.setString(3,"FIXED DATE");

            pste.executeUpdate();
            System.out.println("Date Modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
@Override
public void supprimer(int i) {
        String req="DELETE FROM `commande` WHERE `commandeid`='"+i+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Commande Supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
}
@Override
public List<commande> afficher() {
        List<commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM `commande` WHERE commande.commandeid <> 97";
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                float sum=0;
                String req2="SELECT nom FROM `utilisateur` WHERE id="+rs.getInt("clientid")+";";
                ResultSet rse = ste.executeQuery(req2);
                 ResultSet ret = ste.executeQuery("SELECT SUM(prixprod) FROM Panier");               
                  while (ret.next()) {
                  float c = ret.getFloat(1);
                  sum = sum + c;
                 }
                commande c = new commande();
                c.setCommandeid(rs.getInt("commandeid"));
                c.setClientid(rs.getInt("clientid"));
                c.setPanierid(rs.getInt("panierid"));
                c.setDatecreation(rs.getTimestamp("datecreation"));
                c.setDateexpedition(rs.getTimestamp("dateexpedition"));
                c.setDatearrivee(rs.getTimestamp("datearrivee"));
                c.setNomclient(rse.getString("nomclient"));
                c.setTotal(sum);

                commandes.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commandes;
}


@Override
public List<commande> search(String s) {
        List<commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM `commande` where datecreation like %"+ s+"%";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                commande c = new commande();
                c.setCommandeid(rs.getInt("commandeid"));
                c.setClientid(rs.getInt("clientid"));
                c.setRcid(rs.getInt("rcid"));
                c.setPanierid(rs.getInt("panierid"));
                c.setDatecreation(rs.getTimestamp("datecreation"));
                c.setDateexpedition(rs.getTimestamp("dateexpedition"));
                c.setDatearrivee(rs.getTimestamp("datearrivee"));
                commandes.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commandes;
}


@Override
public List<commande> sort() {
        List<commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM `commande`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                commande c = new commande();
                c.setCommandeid(rs.getInt("commandeid"));
                c.setClientid(rs.getInt("clientid"));
             //   c.setRcid(rs.getInt("rcid"));
                c.setPanierid(rs.getInt("panierid"));
                c.setDatecreation(rs.getTimestamp("datecreation"));
                c.setDateexpedition(rs.getTimestamp("dateexpedition"));
                c.setDatearrivee(rs.getTimestamp("datearrivee"));
                commandes.add(c);

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commandes;
}


    @Override
    public String getnomclient(int i) {
        String req = "SELECT nom FROM `utilisateur` u JOIN `commande` c ON u.id = c.clientid WHERE role='client' AND u.id="+i+"";
        String x = null;
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            x =rs.getString("nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
        
    }
    
        @Override
        public String getnomlivreur(int i) {
        String req = "SELECT nom FROM `utilisateur` u JOIN `commande` c ON u.id = c.livreurid WHERE role='livreur' AND u.id="+i+"";
          System.out.println("request"+req);
          String y = null;
   
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
           y=rs.getString("Nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return y;
        }
       
        
        @Override
        public String getnomresto(int i) {
        String req = "SELECT nom FROM `utilisateur` u JOIN `commande` c ON u.id = c.rcid WHERE role='resto' AND u.id="+i+"";
          System.out.println("request"+req);
          String z = null;
        
   
        
        try {
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
             z=rs.getString("Nom");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return z;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        @Override
//    public List<String> getnomclient() {
//        List<String> a = new ArrayList<>();
//        String req = "SELECT nom FROM `utilisateur` u JOIN `commande` c ON u.id = c.clientid WHERE role='client'";
//        
//        String x = null;
//        
//        try {
//            
//            ste = conn.createStatement();
//            ResultSet rs = ste.executeQuery(req);
//            while(rs.next()){
//            a.add(rs.getString("Nom"));
//            }
//            
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return a;


}

}
