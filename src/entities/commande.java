

package entities;

import java.sql.Timestamp;
//import java.util.Date;

public class commande {
    
    private int commandeid;
    private int clientid;
    private int livreurid;
    private int rcid;   
    private int panierid;
    private String nomclient;
    private String nomlivreur;
    private String nomresto;
    private float total;
    
    private Timestamp datecreation;
    private Timestamp dateexpedition;
    private Timestamp datearrivee;
    public commande() {
        
        
    }

    public commande(int clientid, int panierid, Timestamp datecreation, Timestamp dateexpedition, Timestamp datearrivee) {
        this.clientid = clientid;
        this.panierid = panierid;
        this.datecreation = datecreation;
        this.dateexpedition = dateexpedition;
        this.datearrivee = datearrivee;
    }

    public commande( int clientid, int livreurid, int rcid, int panierid, Timestamp datecreation, Timestamp dateexpedition, Timestamp datearrivee,float total) {
        
        this.clientid = clientid;
        this.livreurid = livreurid;
        this.rcid = rcid;
        this.panierid = panierid;
        this.datecreation = datecreation;
        this.dateexpedition = dateexpedition;
        this.datearrivee = datearrivee;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    

    public int getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(int commandeid) {
        this.commandeid = commandeid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getLivreurid() {
        return livreurid;
    }

    public void setLivreurid(int livreurid) {
        this.livreurid = livreurid;
    }

    public int getRcid() {
        return rcid;
    }

    public void setRcid(int rcid) {
        this.rcid = rcid;
    }

    public int getPanierid() {
        return panierid;
    }

    public void setPanierid(int panierid) {
        this.panierid = panierid;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getNomlivreur() {
        return nomlivreur;
    }

    public void setNomlivreur(String nomlivreur) {
        this.nomlivreur = nomlivreur;
    }

    public String getNomresto() {
        return nomresto;
    }

    public void setNomresto(String nomresto) {
        this.nomresto = nomresto;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }

    public Timestamp getDateexpedition() {
        return dateexpedition;
    }

    public void setDateexpedition(Timestamp dateexpedition) {
        this.dateexpedition = dateexpedition;
    }

    public Timestamp getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(Timestamp datearrivee) {
        this.datearrivee = datearrivee;
    }

    @Override
    public String toString() {
        return "commande{" + "commandeid=" + commandeid + ", clientid=" + clientid + ", livreurid=" + livreurid + ", rcid=" + rcid + ", panierid=" + panierid + ", nomclient=" + nomclient + ", nomlivreur=" + nomlivreur + ", nomresto=" + nomresto + ", total=" + total + ", datecreation=" + datecreation + ", dateexpedition=" + dateexpedition + ", datearrivee=" + datearrivee + '}';
    }

  
    
    
    
    
 




    
    


}
