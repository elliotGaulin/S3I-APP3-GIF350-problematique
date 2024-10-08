package menufact.menu;

import menufact.Iterateur.IIterable;
import menufact.Iterateur.IIterateur;
import menufact.Iterateur.exceptions.IterateurException;
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
        return "menufact.menu.Menu{" +
                "description='" + description + '\'' +
                ", plat=" + "\n" + plat +
                '}';
    }

    /**
     * Crée un itérateur pour le menu
     * @return
     * @throws IterateurException
     */
    @Override
    public IIterateur<PlatAuMenu> creerIterateur() throws IterateurException {
        if (plat.isEmpty()) {
            throw new IterateurException("Le menu est vide.");
        }

        return new IterateurMenu();
    }

    private class IterateurMenu implements IIterateur<PlatAuMenu> {
        private int courant = 0;

        /**
         * Vérifie si on peut aller à un plat suivant
         * @return Vrai si on peut aller à un plat suivant, faux sinon
         */
        @Override
        public boolean aSuivant() {
            return courant < plat.size() - 1;
        }

        /**
         * Vérifie si on peut aller à un plat précédent
         * @return Vrai si on peut aller à un plat précédent, faux sinon
         */
        @Override
        public boolean aPrecedent() {
            return courant > 0;
        }

        /**
         * Retourne le plat précédent
         * @return Le plat précédent
         * @throws IterateurException Si on dépasse la limite inférieure de l'itérable
         */
        @Override
        public PlatAuMenu positionPrecedente() throws IterateurException {
            if(!aPrecedent()) {
                throw new IterateurException("On depasse la limite inferieure de l'iterable.");
            }

            courant--;
            return courant();
        }

        /**
         * Retourne le plat suivant
         * @return Le plat suivant
         * @throws IterateurException Si on dépasse la limite supérieure de l'itérable
         */
        @Override
        public PlatAuMenu positionSuivante() throws IterateurException {
            if (!aSuivant()) {
                throw new IterateurException("On depasse la limite superieure de l'iterable.");
            }

            courant++;
            return courant();
        }

        /**
         * Retourne le premier plat du menu
         * @return Le premier plat du menu
         */
        @Override
        public PlatAuMenu premier() {
            courant = 0;
            return courant();
        }

        /**
         * Retourne le dernier plat du menu
         * @return Le dernier plat du menu
         */
        @Override
        public PlatAuMenu dernier() {
            courant = plat.size() - 1;
            return courant();
        }

        /**
         * Retourne le plat à la position i
         * @param i La position du plat
         * @return Le plat à la position i
         * @throws IterateurException Si la position est invalide
         */
        @Override
        public PlatAuMenu position(int i) throws IterateurException {
            if (i >= plat.size() || i < 0) {
                throw new IterateurException("On depasse les limites du l'iterable.");
            }
            courant = i;
            return courant();
        }

        /**
         * Retourne le plat courant
         * @return Le plat courant
         */
        @Override
        public PlatAuMenu courant() {
            return plat.get(courant);
        }
    }
}
