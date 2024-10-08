package menufact.facture;

import menufact.ingredients.exceptions.IngredientException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

/**
 * État abstrait d'une facture
 */
public abstract class FactureEtat {
    private final Facture facture;

    /**
     * Constructeur
     * @param facture la facture
     */
    public FactureEtat(Facture facture) {
        this.facture = facture;
    }

    /**
     * Méthode pour payer une facture
     * @throws FactureException si la facture ne peut pas être payée
     */
    public abstract void payer() throws FactureException;

    /**
     * Méthode pour fermer une facture
     * @throws FactureException si la facture ne peut pas être fermée
     */
    public abstract void fermer() throws FactureException;

    /**
     * Méthode pour ouvrir une facture
     * @throws FactureException si la facture ne peut pas être ouverte
     */
    public abstract void ouvrir() throws FactureException;

    /**
     * Méthode pour ajouter un plat à la facture
     * @param p le plat choisi
     * @throws FactureException si le plat ne peut pas être ajouté
     * @throws IngredientException si un ingrédient est manquant
     * @throws PlatException si le plat est invalide
     */
    public abstract void ajoutePlat(PlatChoisi p) throws FactureException, IngredientException, PlatException;

    /**
     * Accesseur de la facture
     * @return
     */
    public Facture getFacture() {
        return facture;
    }
    @Override
    public abstract String toString();
}
