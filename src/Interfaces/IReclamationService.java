/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;

import entities.reclamation;
/**
 *
 * @author remo
 */

public interface IReclamationService<r> {
    void ajouter(reclamation r);
    void modifier(int i,String con,String suj);
    void supprimer(int i );
    List<reclamation> afficher();
    List<reclamation> rechercher(int i);
     String getreponse(int i) ;
     void update(int i);
     int getid(String s,String c);
     String getnom(int i);
     int getclientidtoken(int i);

             }