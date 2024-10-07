package menufact.plats;

import menufact.plats.exceptions.PlatException;

public abstract class PlatEtat {
    private final PlatChoisi platChoisi;

    public PlatEtat(PlatChoisi platChoisi) {
        this.platChoisi = platChoisi;
    }

    public abstract void commandee() throws PlatException;
    public abstract void preparee() throws PlatException;
    public abstract void terminee() throws PlatException;
    public abstract void servir() throws PlatException;
    public abstract void impossibleServir() throws PlatException;

    public PlatChoisi getPlatChoisi() {
        return platChoisi;
    }

}
