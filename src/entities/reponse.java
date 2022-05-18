/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author remo
 */
public class reponse {
    private int reponseid;
    private int reclamationid;
    private String reponsenom;



    public reponse() {
    }

  
    
    public reponse(int reclamationid, String reponsenom) {
        this.reclamationid = reclamationid;
        this.reponsenom = reponsenom;
       
    }

    public int getReponseid() {
        return reponseid;
    }

    public void setReponseid(int reponseid) {
        this.reponseid = reponseid;
    }

    public int getReclamationid() {
        return reclamationid;
    }

    public void setReclamationid(int reclamationid) {
        this.reclamationid = reclamationid;
    }

    public String getReponsenom() {
        return reponsenom;
    }

    public void setReponsenom(String reponsenom) {
        this.reponsenom = reponsenom;
    }

    @Override
    public String toString() {
        return "reponse{" + "reponseid=" + reponseid + ", reclamationid=" + reclamationid + ", reponsenom=" + reponsenom + '}';
    }
    
}

    
