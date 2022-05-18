/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import java.util.ArrayList;

import entities.produit;

/**
 *
 * @author lacht
 */
public interface IProduit <P> {
    
      void ajouter(P entity);
    void modifier(int i,P entity);
    void supprimer(int i);
    List<produit> recherche(String s);
    List<produit> afficher();

    
}
