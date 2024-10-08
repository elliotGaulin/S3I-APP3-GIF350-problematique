package menufact.Iterateur;

import menufact.Iterateur.exceptions.IterateurException;

/**
 * Interface IIterable est une interface qui permet de creer un iterateur
 * @param <T> le type de l'iterateur
 */
public interface IIterable<T> {
    /**
     * Methode qui permet de creer un iterateur
     * @return l'iterateur
     * @throws IterateurException si l'iterateur n'est pas cree
     */
    public IIterateur<T> creerIterateur()  throws IterateurException;
}
