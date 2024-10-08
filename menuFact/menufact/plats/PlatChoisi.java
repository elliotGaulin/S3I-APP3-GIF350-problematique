package menufact.plats;

import menufact.plats.exceptions.PlatException;

/**
 * Plat choisi par le client dans le menu
 */
public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;
    private PlatEtat etat;

    public PlatChoisi(PlatAuMenu plat, int quantite) throws PlatException {
        this.plat = plat;
        setQuantite(quantite);
        this.etat = new PlatCommandee(this);
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    /**
     *
     * @return la quantité du plat choisi
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite la quantité du plat choisi
     * @throws PlatException
     */
    public void setQuantite(int quantite) throws PlatException {
        if (quantite < 1) {
            throw new PlatException("La quantité doit être supérieure à 0.");
        }
        this.quantite = quantite;
    }

    /**
     *
     * @return le plat choisi
     */
    public PlatAuMenu getPlat() {
        return plat;
    }

    /**
     *
     * @return l'état du plat choisi
     */
    public PlatEtat getEtat() {
        return etat;
    }
    /**
     *
     * @param etat l'état du plat choisi
     */
    public void setEtat(PlatEtat etat) {
        this.etat = etat;
    }

    /**
     * Commande le plat
     * @throws PlatException
     */
    public void commandee() throws PlatException {
        this.etat.commandee();
    }

    /**
     * Prépare le plat
     * @throws PlatException
     */
    public void preparee() throws PlatException {
        this.etat.preparee();
    }

    /**
     * Termine le plat
     * @throws PlatException
     */
    public void terminee() throws PlatException {
        this.etat.terminee();
    }

    /**
     * Sert le plat
     * @throws PlatException
     */
    public void servir() throws PlatException {
        this.etat.servir();
    }

    /**
     * Impossible de servir le plat
     * @throws PlatException
     */
    public void impossibleServir () throws PlatException {
        this.etat.impossibleServir();
    }


}
