package menufact.facture;

import menufact.ingredients.exceptions.IngredientException;
import menufact.Iterateur.IIterable;
import menufact.Iterateur.IIterateur;
import menufact.observateur.Chef;
import menufact.Client;
import menufact.observateur.GestionnaireEvenement;
import menufact.Iterateur.exceptions.IterateurException;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;
import menufact.plats.exceptions.PlatException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Une facture du systeme Menufact
 *
 * @author Domingo Palao Munoz
 * @version 1.0
 */
public class Facture implements IIterable<PlatChoisi> {
    private Date date;
    private String description;
    private FactureEtat etat;
    private ArrayList<PlatChoisi> platchoisi = new ArrayList<PlatChoisi>();
    private int courant;
    private Client client;


    /**********************Constantes ************/
    private final double TPS = 0.05;
    private final double TVQ = 0.095;
    private final String evenementAjoutPlatChoisi = GestionnaireEvenement.evenementAjoutPlatChoisi;

    /**
     * @param client le client de la facture
     */
    public void associerClient(Client client) {
        this.client = client;
    }

    /**
     * Calcul du sous total de la facture
     *
     * @return le sous total
     */
    public double sousTotal() {
        double soustotal = 0;
        for (PlatChoisi p : platchoisi)
            soustotal += p.getQuantite() * p.getPlat().getPrix();
        return soustotal;
    }

    /**
     * @return le total de la facture
     */
    public double total() {
        return sousTotal() + tps() + tvq();
    }

    /**
     * @return la valeur de la TPS
     */
    double tps() {
        return TPS * sousTotal();
    }

    /**
     * @return la valeur de la TVQ
     */
    double tvq() {
        return TVQ * (TPS + 1) * sousTotal();
    }

    /**
     * Permet de changer l'état de la facture à PAYEE
     */
    public void payer() throws FactureException {
        this.etat.payer();
    }

    /**
     * Permet de changer l'état de la facture à FERMEE
     */
    public void fermer() throws FactureException {
        this.etat.fermer();
    }

    /**
     * Permet de changer l'état de la facture à OUVERTE
     *
     * @throws FactureException en cas que la facture soit PAYEE
     */
    public void ouvrir() throws FactureException {
        this.etat.ouvrir();
    }

    /**
     * @return la description de la facture
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return la date de la facture
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return le client de la facture
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * @return la liste des plats choisis
     */
    public ArrayList<PlatChoisi> getPlatchoisi() {
        return platchoisi;
    }

    /**
     * @return l'état de la facture
     */
    public FactureEtat getEtat() {
        return etat;
    }

    /**
     * @param description la description de la Facture
     */
    public Facture(String description) {
        date = new Date();
        etat = new FactureOuverte(this);
        this.description = description;
    }

    /**
     * @param p un plat choisi
     * @throws FactureException Seulement si la facture est OUVERTE
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException, IngredientException, PlatException {
        this.etat.ajoutePlat(p);
    }

    /**
     * Ajoute un plat choisi à la facture
     *
     * @param p le plat choisi
     *
     */
    public void ajoutePlatChoisi(PlatChoisi p)  {
        this.platchoisi.add(p);
    }

    /**
     * @return le contenu de la facture en chaîne de caracteres
     */
    @Override
    public String toString() {
        return "menufact.facture.Facture{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", platchoisi=" + platchoisi +
                ", client=" + client +
                ", TPS=" + TPS +
                ", TVQ=" + TVQ +
                '}';
    }

    /**
     *  Setter de l'etat
     * @param etat l'état de la facture
     */
    public void setEtat(FactureEtat etat) {
        this.etat = etat;
    }

    /**
     * Associer un chef à la facture
     * pour écouter sur les évènements d'ajout de plats choisi
     *
     * @param chef
     */
    public void associerChef(Chef chef) {
        GestionnaireEvenement.getInstance(evenementAjoutPlatChoisi).abonner(evenementAjoutPlatChoisi, chef);
    }

    /**
     * Dissocier un chef à la facture
     *
     * @param chef
     */
    public void dissocierChef(Chef chef) {
        GestionnaireEvenement.getInstance(evenementAjoutPlatChoisi).desabonner(evenementAjoutPlatChoisi, chef);
    }

    /**
     * Créer un iterateur pour la facture
     * @return un iterateur pour la facture
     * @throws IterateurException si la facture est vide
     */
    @Override
    public IIterateur<PlatChoisi> creerIterateur() throws IterateurException {
        if (platchoisi.size() == 0) {
            throw new IterateurException("L'iterable est vide.");
        }

        return new IterateurFacture();
    }

    /**
     * Iterateur pour la facture
     */
    private class IterateurFacture implements IIterateur<PlatChoisi> {
        private int courant = 0;

        /**
         * Vérifier s'il y a un élément suivant
         * @return vrai s'il y a un élément suivant
         */
        @Override
        public boolean aSuivant() {
            return courant < platchoisi.size() - 1;
        }

        /**
         * Vérifier s'il y a un élément précédent
         * @return vrai s'il y a un élément précédent
         */
        @Override
        public boolean aPrecedent() {
            return courant > 0;
        }

        /**
         * Positionner l'itérateur sur l'élément précédent
         * @return l'élément précédent
         * @throws IterateurException si on dépasse la limite inférieure de l'itérable
         */
        @Override
        public PlatChoisi positionPrecedente() throws IterateurException {
            if(!aPrecedent()) {
                throw new IterateurException("On depasse la limite inferieure de l'iterable.");
            }

            courant--;
            return courant();
        }

        /**
         * Positionner l'itérateur sur l'élément suivant
         * @return l'élément suivant
         * @throws IterateurException si on dépasse la limite supérieure de l'itérable
         */
        @Override
        public PlatChoisi positionSuivante() throws IterateurException {
            if (!aSuivant()) {
                throw new IterateurException("On depasse la limite superieure de l'iterable.");
            }

            courant++;
            return courant();
        }

        /**
         * Positionner l'itérateur sur le premier élément
         * @return le premier élément
         */
        @Override
        public PlatChoisi premier() {
            courant = 0;
            return courant();
        }

        /**
         * Positionner l'itérateur sur le dernier élément
         * @return le dernier élément
         */
        @Override
        public PlatChoisi dernier() {
            courant = platchoisi.size() - 1;
            return courant();
        }

        /**
         * Positionner l'itérateur sur l'élément à la position i
         * @param i la position
         * @return l'élément à la position i
         * @throws IterateurException si on dépasse les limites de l'itérable
         */
        @Override
        public PlatChoisi position(int i) throws IterateurException {
            if (i >= platchoisi.size() || i < 0) {
                throw new IterateurException("On depasse les limites du l'iterable.");
            }
            courant = i;
            return courant();
        }

        /**
         * Retourner l'élément courant
         * @return l'élément courant
         */
        @Override
        public PlatChoisi courant() {
            return platchoisi.get(courant);
        }
    }
}