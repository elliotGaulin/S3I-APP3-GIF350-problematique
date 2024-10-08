package menufact.facture;

import ingredients.exceptions.IngredientException;
import menufact.Chef;
import menufact.Client;
import menufact.Menu;
import menufact.exceptions.MenuException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;

public class FactureController {
        private Facture facture;
        private FactureView factureView;


        public FactureController(Facture facture, FactureView factureView) {
            this.facture = facture;
            this.factureView = factureView;
        }

        public void associerChef(Chef chef) {
            facture.associerChef(chef);
        }

        public void ouvrir() throws FactureException {
            facture.ouvrir();
        }

        public void fermer() throws FactureException {
            facture.fermer();
        }

        public void payer() throws FactureException {
            facture.payer();
        }

        public String genererFacture() {
            return factureView.render(facture);
        }

        public void ajoutePlat(PlatChoisi p) throws FactureException, IngredientException {
            facture.ajoutePlat(p);
        }

        public void associerClient(Client c) {
            facture.associerClient(c);
        }

        public void ajouterPlatMenu(int pos, int quant) throws MenuException, FactureException {
            Menu menu = Menu.getInstance();
            menu.position(pos);
//            PlatChoisi p = new PlatChoisi(menu.platCourant(), quant);
//            facture.ajoutePlat(p);
        }


}
