    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author remo
 */
public class reclamation {
    public int reclamationid;
    public String sujet;
    public String contenu;
    public int clientid;
    public int commandeid;
    public String reponse;
    public String nomclient;

    public reclamation() {
    }

    public reclamation(String sujet, String contenu,int clientid,int commandeid,String reponse) {
        this.sujet = sujet;
        this.contenu = contenu;
        this.clientid = clientid;
        this.commandeid = commandeid;
        this.reponse=reponse;
    }

    public reclamation(int reclamationid, String sujet, String contenu, int clientid, int commandeid) {
        this.reclamationid = reclamationid;
        this.sujet = sujet;
        this.contenu = contenu;
        this.clientid = clientid;
        this.commandeid = commandeid;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }
    
    public reclamation(String sujet, String contenu) {
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }




   

    public int getReclamationid() {
        return reclamationid;
    }

    public void setReclamationid(int reclamationid) {
        this.reclamationid = reclamationid;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(int commandeid) {
        this.commandeid = commandeid;
    }

    @Override
    public String toString() {
        return "reclamation{" + "reclamationid=" + reclamationid + ", sujet=" + sujet + ", contenu=" + contenu + ", clientid=" + clientid + ", commandeid=" + commandeid + ", reponse=" + reponse + ", nomclient=" + nomclient + '}';
    }

  

    
    
    
}
