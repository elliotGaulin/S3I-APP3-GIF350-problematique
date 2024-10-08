package menufact.facture;

import menufact.ingredients.exceptions.IngredientException;
import menufact.observateur.Chef;
import menufact.Client;
import menufact.menu.Menu;
import menufact.menu.exceptions.MenuException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

/**
 * Classe FactureController
 * Insipiré de : https://www.geeksforgeeks.org/mvc-design-pattern/
 *
 * @version 1.0
 * @auteur Elliot Gaulin
 * @date 2024-10-08
 */
public class FactureController {
    private Facture facture;
    private FactureView factureView;

    /**
     * Constructeur de la classe FactureController
     *
     * @param modele Facture
     * @param view   FactureView
     */
    public FactureController(Facture modele, FactureView view) {
        this.facture = modele;
        this.factureView = view;
    }

    /**
     * Associe un chef à la facture
     *
     * @param chef
     */
    public void associerChef(Chef chef) {
        facture.associerChef(chef);
    }

    /**
     * Ouvre la facture
     * @throws FactureException si la facture ne peut pas être ouverte
     */
    public void ouvrir() throws FactureException {
        facture.ouvrir();
    }

    /**
     * Ferme la facture
     * @throws FactureException si la facture ne peut pas être fermée
     */
    public void fermer() throws FactureException {
        facture.fermer();
    }

    /**
     * Payer la facture
     * @throws FactureException si la facture ne peut pas être payée
     */
    public void payer() throws FactureException {
        facture.payer();
    }

    /**
     * Génère la facture
     * @return la facture générée
     */
    public String genererFacture() {
        return factureView.render(facture);
    }

    /**
     * Ajoute un plat à la facture
     * @param p PlatChoisi à ajouter
     * @throws FactureException si le plat ne peut pas être ajouté
     * @throws IngredientException si un ingrédient est manquant
     * @throws PlatException si le plat est invalide
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException, IngredientException, PlatException {
        facture.ajoutePlat(p);
    }

    /**
     * Associe un client à la facture
     * @param c Client à associer
     */
    public void associerClient(Client c) {
        facture.associerClient(c);
    }

    /**
     * Ajoute un plat du menu à la facture
     * @param pos position du plat dans le menu
     * @param quant quantité du plat
     * @throws MenuException si le menu est invalide
     * @throws FactureException si le plat ne peut pas être ajouté
     * @throws IngredientException si un ingrédient est manquant
     * @throws PlatException si le plat est invalide
     */
    public void ajouterPlatMenu(int pos, int quant) throws MenuException, FactureException, IngredientException, PlatException {
        PlatAuMenu pm = Menu.getInstance().creerIterateur().position(pos);
        PlatChoisi p = new PlatChoisi(pm, quant);
        facture.ajoutePlat(p);
    }

    @Override
    public String toString() {
        return "Facture :" + this.facture + "\n View :" + this.factureView;
    }
}
