package menufact.facture;

import menufact.plats.PlatChoisi;

/**
 * Classe FactureView
 * Insipiré de : https://www.geeksforgeeks.org/mvc-design-pattern/
 * @version 1.0
 * @auteur Elliot Gaulin
 * @date 2024-10-08
 */
public class FactureView {
    public String render(Facture facture) {
        String lesPlats = "";
        String factureGenere = "";

        int i =1;

        factureGenere =   "Facture generee.\n" +
                "Date:" + facture.getDate() + "\n" +
                "Description: " + facture.getDescription() + "\n" +
                "Client:" + facture.getClient().getNom() + "\n" +
                "Les plats commandes:" + "\n" + lesPlats;

        factureGenere += "Seq   Plat         Prix   Quantite\n";
        for (PlatChoisi plat : facture.getPlatchoisi())
        {
            factureGenere +=  i + "     " + plat.getPlat().getDescription() +  "  " + plat.getPlat().getPrix() +  "      " + plat.getQuantite() + "\n";
            i++;
        }

        factureGenere += "          TPS:               " + facture.tps() + "\n";
        factureGenere += "          TVQ:               " + facture.tvq() + "\n";
        factureGenere += "          Le total est de:   " + facture.total() + "\n";

        return factureGenere;
    }

    @Override
    public String toString() {
        return "FactureView";
    }
}
