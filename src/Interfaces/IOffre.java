/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import entities.Offre;

/**
 *
 * @author lacht
 */
public interface IOffre<O> {
     
 void ajouter(O entity );
    void modifier(int i,O entity);
    void supprimer(int i);
        List<Offre> recherche(String s);

    List<O> afficher();
}
