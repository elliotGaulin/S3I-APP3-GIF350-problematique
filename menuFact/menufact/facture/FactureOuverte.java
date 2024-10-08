package menufact.facture;

import menufact.ingredients.exceptions.IngredientException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

public class FactureOuverte extends FactureEtat {
    public FactureOuverte(Facture facture) {
        super(facture);
    }

    public void payer() {
        this.getFacture().setEtat(new FacturePayee(this.getFacture()) );
    }

    public void fermer() {
        this.getFacture().setEtat(new FactureFermee(this.getFacture()) );
    }

    public void ouvrir() {}

    public void ajoutePlat(PlatChoisi p) throws IngredientException, FactureException, PlatException {
        this.getFacture().ajoutePlatChoisi(p);
    }

    @Override
    public String toString() {
        return "Ouverte";
    }
}
