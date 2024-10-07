package menufact.facture;

import menufact.plats.PlatChoisi;

public class FactureView {
        private final Facture facture;

    public FactureView(Facture facture) {
        this.facture = facture;
    }

    public String render() {
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
}
