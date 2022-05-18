/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.user;
import java.util.List;

/**
 *
 * @author Nora
 */
public interface IUserService {
    
    void ajouter(user p);
    void modifier(user p);
    void supprimer(user p);
    List<user> afficher();
    
}
