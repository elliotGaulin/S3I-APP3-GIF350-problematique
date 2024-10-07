package menufact.facture;

import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;

public abstract class FactureEtat {
    private final Facture facture;

    public FactureEtat(Facture facture) {
        this.facture = facture;
    }

    public abstract void payer() throws FactureException;
    public abstract void fermer() throws FactureException;
    public abstract void ouvrir() throws FactureException;
    public abstract void ajoutePlat(PlatChoisi p) throws FactureException;

    public Facture getFacture() {
        return facture;
    }

    @Override
    public abstract String toString();
//TODO?:    public abstract void function retirerPlat(PlatAuMenu platAuMenu);
}
