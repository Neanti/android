package univ.iutmetz.developpemtnmob1;

import java.util.ArrayList;

public interface ActiviteEnAttenteAvecResultat {

    public void notifyRetourRequete(String resultat);
    public void notifyRetourRequeteFindAll(ArrayList liste);
}
