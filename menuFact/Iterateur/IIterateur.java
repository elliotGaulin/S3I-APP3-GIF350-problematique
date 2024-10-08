package Iterateur;

import menufact.exceptions.IterateurException;

public interface IIterateur<T> {
    public boolean aSuivant();
    public boolean aPrecedant();

    public T positionPrecedente() throws IterateurException;
    public T positionSuivante() throws IterateurException;

    public T premier();
    public T dernier();

    public T position(int i) throws IterateurException;
    public T courant();
}
