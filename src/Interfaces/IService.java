/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import javax.swing.text.html.parser.Entity;

/**
 *
 * @author Nora
 */
public interface IService<T> {
    void ajouter(T entity);
    void modifier(T entity);
    void supprimer(T entity);
    List<T> afficher();
}
