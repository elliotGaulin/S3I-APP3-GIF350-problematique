package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatEnPreparation extends  PlatEtat{
    public PlatEnPreparation(PlatChoisi platChoisi) {
        super(platChoisi);
    }

    @Override
    public void commande() throws PlatException {


    }

    @Override
    public void prepare() throws PlatException {

    }

    @Override
    public void termine() throws PlatException {

    }

    @Override
    public void servir() throws PlatException {

    }

    @Override
    public void impossibleServir() throws PlatException {

    }
}
