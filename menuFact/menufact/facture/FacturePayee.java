package menufact.facture;

import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;

public class FacturePayee extends FactureEtat {
    public FacturePayee(Facture facture) {
        super(facture);
    }

    public void payer() {}
    public void fermer() throws FactureException {
        throw new FactureException("La facture payée ne peut pas être fermée.");
    }
    public void ouvrir() throws FactureException {
        throw new FactureException("La facture ne peut pas être reouverte.");
    }
    public void ajoutePlat(PlatChoisi p) throws FactureException {
        throw new FactureException("On peut ajouter un plat seulement sur une facture OUVERTE.");
    }

    @Override
    public String toString() {
        return "Payée";
    }
}
