package menufact;

/**
 * Client
 */
public class Client {
    private int idClient;
    private String nom;
    private String numeroCarteCredit;

    public Client(int idClient, String nom, String numeroCarteCredit) {
        this.idClient = idClient;
        this.nom = nom;
        this.numeroCarteCredit = numeroCarteCredit;
    }

    /**
     *
     * @return l'id du client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient l'id du client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom le nom du client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return le numéro de carte de crédit du client
     */
    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     *
     * @param numeroCarteCredit le numéro de carte de crédit du client
     */
    public void setNumeroCarteCredit(String numeroCarteCredit) {
        this.numeroCarteCredit = numeroCarteCredit;
    }


    @Override
    public String toString() {
        return "menufact.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                '}';
    }
}
/*
@startuml
class menufact.Client{}
* */