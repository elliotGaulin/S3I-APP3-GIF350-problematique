package menufact.plats;

import menufact.plats.exceptions.PlatException;

/**
 * État IMPOSSIBLE À SERVIR pour un plat choisi
 */
public class PlatImpossibleServir extends  PlatEtat{
    public PlatImpossibleServir(PlatChoisi platChoisi) {
        super(platChoisi);
    }

    @Override
    public void commandee() throws PlatException {
        throw new PlatException("Un plat impossible à servir ne peut pas être mis comme COMMANDÉE");

    }

    @Override
    public void preparee() throws PlatException {
        throw new PlatException("Un plat impossible à servir ne peut pas être mis comme EN PRÉPARATION");

    }

    @Override
    public void terminee() throws PlatException {
        throw new PlatException("Un plat impossible à servir ne paut pas être mis comme TERMINÉE");
    }

    @Override
    public void servir() throws PlatException {
        throw new PlatException("Un plat en préparation ne peut pas être mis comme SERVI.");
    }

    @Override
    public void impossibleServir() throws PlatException {


    }
}
