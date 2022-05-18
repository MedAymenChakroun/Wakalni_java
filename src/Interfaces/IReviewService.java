/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;

import entities.review;

/**
 *
 * @author remo
 */

public interface IReviewService<r> {
    void ajouter(review r);
    void modifier(int i,String note,int com);
    void supprimer(int i );
    List<review>afficher();
    String getnom(int i);
    String getnomproduit(int i);
    public List<String> listeproduits();
    public int getidproduit(String i);
    int getclientidtoken();
}
