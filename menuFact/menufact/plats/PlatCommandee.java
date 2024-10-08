package menufact.plats;

import menufact.facture.Facture;
import menufact.plats.exceptions.PlatException;

/**
 * État COMMANDÉE pour un platChoisi
 */
public class PlatCommandee extends PlatEtat {
    public PlatCommandee(PlatChoisi platChoisi) {
        super(platChoisi);
    }

    @Override
    public void commandee() throws PlatException {


    }

    @Override
    public void preparee() throws PlatException {
        getPlatChoisi().setEtat(new PlatEnPreparation(getPlatChoisi()));
    }

    @Override
    public void terminee() throws PlatException {
        throw new PlatException("Un plat COMMANDÉE ne peut pas être mis comme TERMINÉE");

    }

    @Override
    public void servir() throws PlatException {
        throw new PlatException("Un plat COMMANDÉE ne peut pas être mis comme SERVI");

    }

    @Override
    public void impossibleServir() throws PlatException {
        getPlatChoisi().setEtat(new PlatImpossibleServir(getPlatChoisi()));

    }
}
