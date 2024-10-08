package menufact.Iterateur;

import menufact.Iterateur.exceptions.IterateurException;

public interface IIterable<T> {
    public IIterateur<T> creerIterateur()  throws IterateurException;
}
