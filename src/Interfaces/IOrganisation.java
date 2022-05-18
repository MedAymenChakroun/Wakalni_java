/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import entities.Organisation;
import java.util.List;

/**
 *
 * @author dell
 * @param <Organisation>
 */
public interface IOrganisation <Organisation> {
    
   
   public void ajouterOrganisation(Organisation o);
   public void supprimerOrganisation(Organisation o);
   public void modifierOrganisation(Organisation o);
   public List<Organisation> afficher();
   public List<Organisation> rechercherOrganisation(String nom);
   public List<Organisation> trierOrganisation();
   public List<Organisation> afficherByOrganisation(Organisation org);
    
    
}
