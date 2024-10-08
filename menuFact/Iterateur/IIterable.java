package Iterateur;

import menufact.exceptions.IterateurException;

public interface IIterable<T> {
    public IIterateur<T> creerIterateur()  throws IterateurException;
}
