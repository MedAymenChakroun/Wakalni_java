package Interfaces;
import java.util.List;
//import wakalni.utilisateur.entities.utilisateur;
//import wakalni.offre.entities.offre;
import entities.panier;

public interface Ipanier<pa> {

    void ajouter(panier pa/*,produit po,offre of,utilisateur u*/);
    void supprimer(String i,String j,Float k);
    List<panier> afficher();
    String getnomproduit(int i);
    double getprice(int i);
    public  int updateid(int i);
    public float somme();
}
