
package entities;

public class ProduitPanier {
    private int produitid,panierid;

    public ProduitPanier() {
    }

    public ProduitPanier(int produitid, int panierid) {
        this.produitid = produitid;
        this.panierid = panierid;
    }
    
    

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public int getPanierid() {
        return panierid;
    }

    public void setPanierid(int panierid) {
        this.panierid = panierid;
    }

    @Override
    public String toString() {
        return "ProduitPanier{" + "produitid=" + produitid + ", panierid=" + panierid + '}';
    }

    public void setNom(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}