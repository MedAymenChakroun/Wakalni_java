/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import entities.panier;
import entities.produit;
import entities.ProduitPanier;

public interface IProduitPanier {
    void ajouter(ProduitPanier pp);
    List<produit> jointure(/*int i*/);
    
}
