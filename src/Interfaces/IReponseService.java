/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;

import entities.reponse;

/**
 *
 * @author remo
 */

public interface IReponseService<r> {
    void ajouter(reponse r);
    void modifier(int i,String rep);
    void supprimer(int i );
    List<reponse> afficher();
     public void repvalider(int i);
     public void reprefuser(int i);
    int getclientidtoken(int i);
}
