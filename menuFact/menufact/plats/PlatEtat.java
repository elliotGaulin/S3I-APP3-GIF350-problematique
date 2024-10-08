package menufact.plats;

import menufact.plats.exceptions.PlatException;

/**
 * État d'un plat choisi
 */
public abstract class PlatEtat {
    private final PlatChoisi platChoisi;

    /**
     * Constructeur
     * @param platChoisi le plat choisi
     */
    public PlatEtat(PlatChoisi platChoisi) {
        this.platChoisi = platChoisi;
    }

    /**
     * Commande le plat
     * @throws PlatException
     */
    public abstract void commandee() throws PlatException;
    /**
     * Prepare le plat
     * @throws PlatException
     */
    public abstract void preparee() throws PlatException;
    /**
     * Termine le plat
     * @throws PlatException
     */
    public abstract void terminee() throws PlatException;
    /**
     * Sert le plat
     * @throws PlatException
     */
    public abstract void servir() throws PlatException;
    /**
     * Impossible de servir le plat
     * @throws PlatException
     */
    public abstract void impossibleServir() throws PlatException;

    /**
     * Retourne le plat choisi
     * @return le plat choisi
     */
    public PlatChoisi getPlatChoisi() {
        return platChoisi;
    }

}
