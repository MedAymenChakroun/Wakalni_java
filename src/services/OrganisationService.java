/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.IOrganisation;
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
public class OrganisationService implements IOrganisation <Organisation> {
    
 @Override
 public void ajouterOrganisation(Organisation o) {
         try {
            String requete= "INSERT INTO organisation (nom,adresse,email,numero,leftoverid)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pste = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            pste.setString(1, o.getNom());
            pste.setString(2, o.getAdresse());
            pste.setString(3, o.getEmail());
            pste.setInt(4, o.getNumero());
            pste.setInt(5,o.getLeftoverid());
            pste.executeUpdate();
            System.out.println("Organisation inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
 
 @Override
  public void supprimerOrganisation(Organisation o) {
        try {
            String requete = "DELETE FROM organisation where organisationid=?";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, o.getOrganisationid());
            pst.executeUpdate();
            System.out.println("Organisation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
  
 @Override
  public void modifierOrganisation(Organisation o) {
        try {
            String requete = "UPDATE organisation SET nom=?,adresse=?,email=?,numero=?,leftoverid=?  WHERE organisationid=?";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, o.getNom());
            pst.setString(2, o.getAdresse());
            pst.setString(3, o.getEmail());
            pst.setInt(4,o.getNumero());
            pst.setInt(5,o.getLeftoverid());
            pst.setInt(6,o.getOrganisationid());
            
            pst.executeUpdate();
            System.out.println("Organisation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
 @Override
  public List<Organisation> afficher() {
         List<Organisation> organisationList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM organisation";
            Statement st = datasource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Organisation o = new Organisation();
                o.setOrganisationid(rs.getInt("organisationid"));
                o.setNom(rs.getString("nom"));
                o.setAdresse(rs.getString("adresse"));
                o.setEmail(rs.getString("email"));
                o.setNumero(rs.getInt("numero"));
                o.setLeftoverid(rs.getInt("leftoverid"));
                organisationList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return organisationList;
    }
  
 @Override
  public List<Organisation> rechercherOrganisation(String nom){
      String requete="select * from organisation where nom=?";
        ResultSet rs=null;
        List<Organisation> list=new ArrayList();
        try{
            PreparedStatement ps = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setString(1, nom);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Organisation o=new Organisation();
        try{
            while(rs.next()){
               o.setOrganisationid(rs.getInt("organisationid"));
                o.setNom(rs.getString("nom"));
                o.setAdresse(rs.getString("adresse"));
                o.setEmail(rs.getString("email"));
                o.setNumero(rs.getInt("numero"));
                o.setLeftoverid(rs.getInt("leftoverid"));
                list.add(new Organisation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
  }
  
 @Override
  public List<Organisation> trierOrganisation(){
      List<Organisation> list=new ArrayList<>();
        try{
            String requete="select * from organisation order by organisationid asc";
            PreparedStatement ps = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Organisation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;   
}
  
 @Override
  public List<Organisation> afficherByOrganisation(Organisation org) {
      String requete="SELECT * FROM organisation o INNER JOIN leftovers lf ON lf.leftoverid=o.leftoverid where o.leftoverid=?";
      ResultSet rs=null;
      List<Organisation> list=new ArrayList();
         
        try{
            PreparedStatement ps = datasource.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setInt(1, org.getLeftoverid());
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Organisation o=new Organisation();
        try{
            while(rs.next()){
               o.setOrganisationid(rs.getInt("organisationid"));
                o.setNom(rs.getString("nom"));
                o.setAdresse(rs.getString("adresse"));
                o.setEmail(rs.getString("email"));
                o.setNumero(rs.getInt("numero"));
                o.setLeftoverid(rs.getInt("leftoverid"));
                list.add(new Organisation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
  }
  
  
  
  
 
    

    
   
   

    
  
}
