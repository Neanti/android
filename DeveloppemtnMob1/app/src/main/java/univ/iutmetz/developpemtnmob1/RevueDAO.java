package univ.iutmetz.developpemtnmob1;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RevueDAO {

   private static final String FICHIER ="fichier";
    private Context contexte;
    private static RevueDAO instanceRevueDAO;

    public static RevueDAO getInstanceRevueDAO(Context contexte){
        if (instanceRevueDAO == null) {
            instanceRevueDAO = new RevueDAO(contexte);

        }
        return instanceRevueDAO;
    }

    private RevueDAO(Context contexte) {
        this.contexte=contexte;
    }

    public void ecriture (ArrayList<Revue> tabRevue){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(this.contexte.openFileOutput(FICHIER, Context.MODE_PRIVATE));
            oos.writeObject(tabRevue);
            oos.close();
        }
        catch (IOException ioe) {

        }

    }

    public ArrayList<Revue> lecture() {
        ArrayList<Revue> resultat = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(this.contexte.openFileInput(FICHIER));
            resultat = (ArrayList<Revue>) ois.readObject();
            ois.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultat;

    }

    public ArrayList<Revue> findAll(){
        return lecture();

    }

}
