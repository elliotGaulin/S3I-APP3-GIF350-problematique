package menufact;

import Iterateur.IIterable;
import Iterateur.IIterateur;
import menufact.exceptions.IterateurException;
import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

public class Menu implements IIterable<PlatAuMenu> {
    private static Menu instance;

    private String description;
    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    private Menu(String description) {
        this.description = description;
    }

    public void ajoute(PlatAuMenu p)
    {
        plat.add(p);
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
                ", plat=" + "\n" + plat +
                '}';
    }

    @Override
    public IIterateur<PlatAuMenu> creerIterateur() throws IterateurException {
        if (plat.isEmpty()) {
            throw new IterateurException("Le menu est vide.");
        }

        return new IterateurMenu();
    }

    private class IterateurMenu implements IIterateur<PlatAuMenu> {
        private int courant = 0;

        @Override
        public boolean aSuivant() {
            return courant < plat.size() - 1;
        }

        @Override
        public boolean aPrecedant() {
            return courant > 0;
        }

        @Override
        public PlatAuMenu positionPrecedente() throws IterateurException {
            if(!aPrecedant()) {
                throw new IterateurException("On depasse la limite inferieure de l'iterable.");
            }

            courant--;
            return courant();
        }

        @Override
        public PlatAuMenu positionSuivante() throws IterateurException {
            if (!aSuivant()) {
                throw new IterateurException("On depasse la limite superieure de l'iterable.");
            }

            courant++;
            return courant();
        }

        @Override
        public PlatAuMenu premier() {
            courant = 0;
            return courant();
        }

        @Override
        public PlatAuMenu dernier() {
            courant = plat.size() - 1;
            return courant();
        }

        @Override
        public PlatAuMenu position(int i) throws IterateurException {
            if (i >= plat.size() || i < 0) {
                throw new IterateurException("On depasse les limites du l'iterable.");
            }
            courant = i;
            return courant();
        }

        @Override
        public PlatAuMenu courant() {
            return plat.get(courant);
        }
    }
}
