/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.ILeftoversService;
import entities.Leftovers;
import entities.Organisation;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.datasource;

/**
 *
 * @author dell
 */
public class LeftoversService implements ILeftoversService <Leftovers> {
 
  
 @Override
    public void ajouterLeftover(Leftovers l) {
         try {
            String requete= "INSERT INTO leftovers (sujet,type,quantite,dateexpiration,chefrestoid)"
                    + "VALUES (?,?,?,?,?)";                                                                                                                                                            //yhezha lel bd
            PreparedStatement pste = datasource.getInstance().getCnx()                                                                                                                                //y7adher les données eli bch y7otehom fel requete fel ?
                    .prepareStatement(requete);
            pste.setString(1, l.getSujet());
            pste.setString(2, l.getType());
            pste.setInt(3, l.getQuantite());
            pste.setDate(4, (Date) l.getDateexpiration());
            pste.setInt(5,l.getchefrestoid());
            pste.executeUpdate();
            System.out.println("Leftovers inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
 @Override
 public void supprimerLeftovers(Leftovers l) {
        try {
            String requete = "DELETE FROM leftovers where leftoverid=?";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, l.getLeftoverid());
            pst.executeUpdate();
            System.out.println("Leftover supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 
 
 
 
 
@Override
    public void modifierLeftovers(Leftovers l) {
        try {
            String requete = "UPDATE leftovers SET sujet=?,type=?,quantite=?,dateexpiration=?,chefrestoid=?  WHERE leftoverid=?";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, l.getSujet());
            pst.setString(2, l.getType());
            pst.setInt(3, l.getQuantite());
            pst.setDate(4, (Date) l.getDateexpiration());
            pst.setInt(5,l.getchefrestoid());
            pst.setInt(6, l.getLeftoverid());
            
            pst.executeUpdate();
            System.out.println("Leftovers modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
@Override
    public List<Leftovers> afficher() {
         List<Leftovers> leftoversList = new ArrayList<>();                                                                                                                                    //list stocki feha les données 
        try {
            String requete = "SELECT * FROM leftovers";
            Statement st = datasource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);                                                                                                                                            //eli bch y7ot feha les données executé par la requete
            while(rs.next()){                                                                                                                                                           //verifi ken mezel fama data
                Leftovers l = new Leftovers();
                l.setLeftoverid(rs.getInt("leftoverid"));
                l.setSujet(rs.getString("sujet"));
                l.setType(rs.getString("type"));
                l.setQuantite(rs.getInt("quantite"));
                l.setDateexpiration(rs.getDate("dateexpiration"));
                l.setChefrestoid(rs.getInt("chefrestoid"));
                leftoversList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return leftoversList;
    }
    
 @Override
    public List<Leftovers> rechercherLeftovers(String sujet){
      String requete="select * from leftovers where sujet=?";
        ResultSet rs=null;
        List<Leftovers> list=new ArrayList();
        try{
            PreparedStatement ps = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setString(1, sujet);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Leftovers l=new Leftovers();
        try{
            while(rs.next()){
               l.setLeftoverid(rs.getInt("leftoverid"));
                l.setSujet(rs.getString("sujet"));
                l.setType(rs.getString("type"));
                l.setQuantite(rs.getInt("quantite"));
                l.setDateexpiration(rs.getDate("dateexpiration"));
                l.setChefrestoid(rs.getInt("chefrestoid"));
                list.add(new Leftovers(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getInt(6)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
  }
    
 @Override
    public List<Leftovers> trierLeftovers(){
      List<Leftovers> list=new ArrayList<>();
        try{
            String requete="select * from leftovers order by dateexpiration asc";
            PreparedStatement ps = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Leftovers(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getInt(6)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;   
}
    
}

