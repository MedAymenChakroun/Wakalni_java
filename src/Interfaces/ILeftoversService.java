/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import entities.Leftovers;
/**
 *
 * @author dell
 * @param <Leftovers>
 */
public interface ILeftoversService <Leftovers> {
    
    
    public void ajouterLeftover(Leftovers l);
    public void supprimerLeftovers(Leftovers l);
    public void modifierLeftovers(Leftovers l);
    public List<Leftovers> afficher();
    public List<Leftovers> rechercherLeftovers(String sujet);
    public List<Leftovers> trierLeftovers();
    

    /**
     *
     * @return
     */

    
    

    
}
