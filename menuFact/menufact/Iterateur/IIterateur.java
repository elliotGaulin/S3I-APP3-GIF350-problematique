package menufact.Iterateur;

import menufact.Iterateur.exceptions.IterateurException;

/**
 * Interface IIterateur pour parcourir des elements
 * @param <T> le type de l'iterateur
 */
public interface IIterateur<T> {
    /**
     * Methode qui permet de verifier si l'iterateur a un element suivant
     * @return true si l'iterateur a un element suivant, false sinon
     */
    public boolean aSuivant();

    /**
     * Methode qui permet de verifier si l'iterateur a un element precedent
     * @return true si l'iterateur a un element precedent, false sinon
     */
    public boolean aPrecedent();

    /**
     * Methode qui permet de se deplacer vers l'element precedent
     * @return l'element precedent
     * @throws IterateurException si l'element precedent n'existe pas
     */
    public T positionPrecedente() throws IterateurException;

    /**
     * Methode qui permet de se deplacer vers l'element suivant
     * @return l'element suivant
     * @throws IterateurException si l'element suivant n'existe pas
     */
    public T positionSuivante() throws IterateurException;

    /**
     * Methode qui permet de se deplacer vers le premier element
     * @return le premier element
     */
    public T premier();

    /**
     * Methode qui permet de se deplacer vers le dernier element
     * @return le dernier element
     */
    public T dernier();

    /**
     * Methode qui permet de se deplacer vers l'element a la position i
     * @param i la position de l'element
     * @return l'element a la position i
     * @throws IterateurException si l'element a la position i n'existe pas
     */
    public T position(int i) throws IterateurException;

    /**
     * Methode qui permet de retourner l'element courant
     * @return l'element courant
     */
    public T courant();
}
