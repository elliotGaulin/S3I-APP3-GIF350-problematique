package menufact.plats;

import menufact.plats.exceptions.PlatException;

/**
 * État SERVI pour un plat choisi
 */
public class PlatServi extends  PlatEtat{
    public PlatServi(PlatChoisi platChoisi) {
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
