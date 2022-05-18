package entities;

public class panier {
    private int panierid;
    private int produitid;
    private int offreid;
    private int clienid;
    private int quantite;
    private String nomproduit;
    private String typeproduit;
    private Float prix;
    private String type;
    private float prixprod;
    
    public panier() {    
        
    }

    public panier(int produitid, float prixprod, int clienid) {
        this.produitid = produitid;
        this.prixprod = prixprod;
        this.clienid = clienid;
    }

    public float getPrixprod() {
        return prixprod;
    }

    public void setPrixprod(float prixprod) {
        this.prixprod = prixprod;
    }

    @Override
    public String toString() {
        return "panier{" + "panierid=" + panierid + ", produitid=" + produitid + ", offreid=" + offreid + ", clienid=" + clienid + ", quantite=" + quantite + ", nomproduit=" + nomproduit + ", typeproduit=" + typeproduit + ", prix=" + prix + ", type=" + type + ", prixprod=" + prixprod + '}';
    }

    

    public int getPanierid() {
        return panierid;
    }

    public void setPanierid(int panierid) {
        this.panierid = panierid;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public int getOffreid() {
        return offreid;
    }

    public void setOffreid(int offreid) {
        this.offreid = offreid;
    }

    public int getClienid() {
        return clienid;
    }

    public void setClienid(int clienid) {
        this.clienid = clienid;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    
    
    
    }

  
   
