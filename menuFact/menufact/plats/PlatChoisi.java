package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;
    private PlatEtat etat;

    public PlatChoisi(PlatAuMenu plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
        this.etat = new PlatCommandee(this);
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public PlatAuMenu getPlat() {
        return plat;
    }

    public PlatEtat getEtat() {
        return etat;
    }
    public void setEtat(PlatEtat etat) {
        this.etat = etat;
    }

    public void preparee() throws PlatException {
        this.etat.preparee();
    }

    public void terminee() throws PlatException {
        this.etat.terminee();
    }

    public void servir() throws PlatException {
        this.etat.servir();
    }

    public void impossibleServir () throws PlatException {
        this.etat.impossibleServir();
    }


}
