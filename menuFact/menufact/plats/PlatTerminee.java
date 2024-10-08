package menufact.plats;

import menufact.plats.exceptions.PlatException;

public class PlatTerminee extends PlatEtat {
    public PlatTerminee(PlatChoisi platChoisi) {
        super(platChoisi);
    }

    @Override
    public void commandee() throws PlatException {

        throw new PlatException("Un plat terminée ne peut pas être mis comme COMMANDÉE");

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
        getPlatChoisi().setEtat(new PlatServi(getPlatChoisi()));

    }

    @Override
    public void impossibleServir() throws PlatException {
        throw new PlatException("Un plat TERMINÉE ne peut pas être mis comme IMPOSSIBLE À SERVIR");

    }
}
