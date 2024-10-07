package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatTerminee extends  PlatEtat{
    public PlatTerminee(PlatChoisi platChoisi) {
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

    }

    @Override
    public void servir() throws PlatException {

    }

    @Override
    public void impossibleServir() throws PlatException {

    }
}
