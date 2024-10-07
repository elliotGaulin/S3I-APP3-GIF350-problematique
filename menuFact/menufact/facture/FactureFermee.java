package menufact.facture;

import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;

public class FactureFermee extends FactureEtat {
    public FactureFermee(Facture facture) {
        super(facture);
    }

    public void payer() {
        this.getFacture().setEtat(new FactureFermee(this.getFacture()));
    }

    public void fermer() {
    }

    public void ouvrir() {
        this.getFacture().setEtat(new FactureOuverte(this.getFacture()));
    }

    public void ajoutePlat(PlatChoisi p) throws FactureException {
        throw new FactureException("On peut ajouter un plat seulement sur une facture OUVERTE.");
    }

    @Override
    public String toString() {
        return "Fermée";
    }
}
