package menufact.facture;

import inventaire.Inventaire;
import menufact.Chef;
import menufact.Client;
import menufact.GestionnaireEvenement;
import menufact.facture.exceptions.FactureException;
import menufact.plats.PlatChoisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Une facture du systeme Menufact
 * @author Domingo Palao Munoz
 * @version 1.0
 */
public class Facture {
    private Date date;
    private String description;
    private FactureEtat etat;
    private ArrayList<PlatChoisi> platchoisi = new ArrayList<PlatChoisi>();
    private int courant;
    private Client client;


    private Inventaire inventaire;
    private GestionnaireEvenement gestionnaireEvenement;


    /**********************Constantes ************/
    private final double TPS = 0.05;
    private final double TVQ = 0.095;
    private final String evenementAjoutPlatChoisi = "ajout-platChoisi";

    /**
     *
     * @param client le client de la facture
     */
    public void associerClient (Client client)
    {
        this.client = client;
    }

    /**
     * Calcul du sous total de la facture
     * @return le sous total
     */
    public double sousTotal()
    {
        double soustotal=0;
         for (PlatChoisi p : platchoisi)
             soustotal += p.getQuantite() * p.getPlat().getPrix();
        return soustotal;
    }

    /**
     *
     * @return le total de la facture
     */
    public double total(){
        return sousTotal()+tps()+tvq();
    }

    /**
     *
     * @return la valeur de la TPS
     */
    double tps(){
        return TPS*sousTotal();
    }

    /**
     *
     * @return la valeur de la TVQ
     */
    double tvq(){
        return TVQ*(TPS+1)*sousTotal();
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
     * @throws FactureException en cas que la facture soit PAYEE
     */
    public void ouvrir() throws FactureException
    {
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
     *
     * @return l'état de la facture
     */
    public FactureEtat getEtat()
    {
        return etat;
    }

    /**
     *
     * @param description la description de la Facture
     */
    public Facture(String description) {
        date = new Date();
        etat = new FactureOuverte(this);
        courant = -1;
        this.description = description;
        this.gestionnaireEvenement = new GestionnaireEvenement(evenementAjoutPlatChoisi);
        this.inventaire = Inventaire.getInstance(Optional.empty());
    }

    /**
     *
     * @param p un plat choisi
     * @throws FactureException Seulement si la facture est OUVERTE
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException {
        this.etat.ajoutePlat(p);
    }

    public void ajoutePlatChoisi(PlatChoisi p)
    {
        this.platchoisi.add(p);
        gestionnaireEvenement.notifier(evenementAjoutPlatChoisi, "Veuillez procéder à la préparation du plat suivant : " + p.toString());
    }

    /**
     *
     * @return le contenu de la facture en chaîne de caracteres
     */
    @Override
    public String toString() {
        return "menufact.facture.Facture{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", platchoisi=" + platchoisi +
                ", courant=" + courant +
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
     * @param chef
     */
    public void associerChef(Chef chef) {
        gestionnaireEvenement.abonner(evenementAjoutPlatChoisi, chef);
    }

    /**
     * Dissocier un chef à la facture
     * @param chef
     */
    public void dissocierChef(Chef chef) {
        gestionnaireEvenement.desabonner(evenementAjoutPlatChoisi, chef);
    }
}
