

package Interfaces;

import java.util.List;
import entities.panier;
import entities.commande;
import entities.user;




public interface Icommande<c> {
    void ajouter(commande c/*,utilisateur u,panier pa*/);
    void modifier(int i ,commande c);
    void supprimer(int i);
    List<commande> search(String s);
    List<commande> afficher();
    List<commande> sort();
    String getnomclient(int i);
    String getnomlivreur(int i);
    String getnomresto(int i);
//    List<commande> getnomclient();

}
