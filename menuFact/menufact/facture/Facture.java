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

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Client getClient() {
        return this.client;
    }

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

    @Override
    public IIterateur<PlatChoisi> creerIterateur() throws IterateurException {
        if (platchoisi.size() == 0) {
            throw new IterateurException("L'iterable est vide.");
        }

        return new IterateurFacture();
    }

    private class IterateurFacture implements IIterateur<PlatChoisi> {
        private int courant = 0;

        @Override
        public boolean aSuivant() {
            return courant < platchoisi.size() - 1;
        }

        @Override
        public boolean aPrecedent() {
            return courant > 0;
        }

        @Override
        public PlatChoisi positionPrecedente() throws IterateurException {
            if(!aPrecedent()) {
                throw new IterateurException("On depasse la limite inferieure de l'iterable.");
            }

            courant--;
            return courant();
        }

        @Override
        public PlatChoisi positionSuivante() throws IterateurException {
            if (!aSuivant()) {
                throw new IterateurException("On depasse la limite superieure de l'iterable.");
            }

            courant++;
            return courant();
        }

        @Override
        public PlatChoisi premier() {
            courant = 0;
            return courant();
        }

        @Override
        public PlatChoisi dernier() {
            courant = platchoisi.size() - 1;
            return courant();
        }

        @Override
        public PlatChoisi position(int i) throws IterateurException {
            if (i >= platchoisi.size() || i < 0) {
                throw new IterateurException("On depasse les limites du l'iterable.");
            }
            courant = i;
            return courant();
        }

        @Override
        public PlatChoisi courant() {
            return platchoisi.get(courant);
        }
    }
}