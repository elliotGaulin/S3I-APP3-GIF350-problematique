package menufact.plats;

import menufact.facture.Facture;
import menufact.plats.exceptions.PlatException;

public class PlatCommandee extends  PlatEtat{
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

    }

    @Override
    public void servir() throws PlatException {

    }

    @Override
    public void impossibleServir() throws PlatException {

    }
}
