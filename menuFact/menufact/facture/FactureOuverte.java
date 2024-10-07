package menufact.facture;

import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;

public class FactureOuverte extends FactureEtat {
    public FactureOuverte(Facture facture) {
        super(facture);
    }

    public void payer() {
        this.getFacture().setEtat(new FactureFermee(this.getFacture()) );
    }

    public void fermer() {
        this.getFacture().setEtat(new FactureFermee(this.getFacture()) );
    }

    public void ouvrir() {}

    public void ajoutePlat(PlatChoisi p) {
        this.getFacture().ajoutePlatChoisi(p);
    }

    @Override
    public String toString() {
        return "Ouverte";
    }
//    public void retirerPlat(PlatAuMenu platAuMenu);
}
