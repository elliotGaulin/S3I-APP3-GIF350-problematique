package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatEnPreparation extends  PlatEtat{
    public PlatEnPreparation(PlatChoisi platChoisi) {
        super(platChoisi);
    }

    @Override
    public void commandee() throws PlatException {

    }

    @Override
    public void preparee() throws PlatException {

    }

    @Override
    public void terminee() throws PlatException {
        getPlatChoisi().setEtat(new PlatTerminee(getPlatChoisi()));
    }

    @Override
    public void servir() throws PlatException {
        throw new PlatException("Un plat en préparation ne peut pas être servi.");
    }

    @Override
    public void impossibleServir() throws PlatException {
    }
}
