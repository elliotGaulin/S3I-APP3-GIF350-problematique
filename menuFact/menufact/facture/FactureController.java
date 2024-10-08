package menufact.facture;

import ingredients.exceptions.IngredientException;
import observateur.Chef;
import menufact.Client;
import menufact.Menu;
import menufact.exceptions.MenuException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

public class FactureController {
        private Facture facture;
        private FactureView factureView;


        public FactureController(Facture model, FactureView view) {
            this.facture = model;
            this.factureView = view;
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

        public void ajoutePlat(PlatChoisi p) throws FactureException, IngredientException, PlatException {
            facture.ajoutePlat(p);
        }

        public void associerClient(Client c) {
            facture.associerClient(c);
        }

        public void ajouterPlatMenu(int pos, int quant) throws MenuException, FactureException, IngredientException, PlatException {
            PlatAuMenu pm = Menu.getInstance().creerIterateur().position(pos);
            PlatChoisi p = new PlatChoisi(pm, quant);
            facture.ajoutePlat(p);
        }


}
