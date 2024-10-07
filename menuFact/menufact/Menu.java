package menufact;

import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

public class Menu {
    private static Menu instance;

    private String description;
    private int courant;
    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    private Menu(String description) {
        this.description = description;
    }

    void ajoute (PlatAuMenu p)
    {
        plat.add(p);
    }

    public void position(int i) throws MenuException {
        if (i >= plat.size() || i < 0) {
            throw new MenuException("On depasse le nombre minimal ou maximal de plats.");
        }
        courant = i;
    }

    public PlatAuMenu platCourant()
    {
        return plat.get(courant);
    }

    public void positionSuivante() throws MenuException
    {
        if (courant+1 >= plat.size())
            throw new MenuException("On depasse le nombre maximale de plats.");
        else
            courant++;
    }

    public void positionPrecedente() throws MenuException {
        if (courant - 1 < 0)
            throw new MenuException("On depasse le nombre minimale de plats");
        else
            courant--;
    }

    /**
     * Retourne ou créé et retourne l'instance du menu
     * @param description La description du menu créé
     * @return L'instance du menu
     */
    public static Menu getInstance(String description)
    {
        if (Menu.instance == null) {
            Menu.instance = new Menu(description);
        }

        return Menu.instance;
    }

    /**
     * Retourne ou créé et retourne l'instance du menu
     * La description du menu créé sera vide
     * @return L'instance du menu
     */
    public static Menu getInstance()
    {
        if (Menu.instance == null) {
            Menu.instance = new Menu("");
        }

        return Menu.instance;
    }


    @Override
    public String toString() {
        return "menufact.Menu{" +
                "description='" + description + '\'' +
                ", courant=" + courant +
                ", plat=" + "\n" + plat +
                '}';
    }
}
