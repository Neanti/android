package univ.iutmetz.developpemtnmob1;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revue1DAO implements DAO<Revue1>{

    public static Revue1DAO instanceRevue1DAO;
    private static ActiviteEnAttenteAvecResultat activite;

    public static Revue1DAO getInstanceRevue1DAO(ActiviteEnAttenteAvecResultat activite) {
        if(instanceRevue1DAO==null)
        {
            instanceRevue1DAO = new Revue1DAO(activite);
        }
        return instanceRevue1DAO;
    }

    private final String URL_SERVEUR =
            "https://devweb.iutmetz.univ-lorraine.fr/~gay28u/";

    private Revue1DAO(ActiviteEnAttenteAvecResultat activite){
        this.activite = activite;
    }


    public void findAll(){
        RequeteSQL req = new RequeteSQL(activite,this);
        req.execute(URL_SERVEUR+"findall.php");
    }

    public void Libre(User u){
        Log.i("YOLO","LIBRE LANCE");
        RequeteSQL req = new RequeteSQL(activite,Revue1DAO.instanceRevue1DAO);
        req.execute(URL_SERVEUR+"RevueLibre.php?user=" + u.getMail());
    }


    public void insert(Revue1 objet){
        RequeteSQL req =new RequeteSQL(activite,this);
        String url = URL_SERVEUR + "NewRevue.php";
        String params = "?nom="+objet.getTitle()+"&description="+objet.getDescription()+"&ref="+objet.getReference()+"&periodicite="+objet.getPeriode()+"&prix="+objet.getFee()+"&image="+objet.getVisuel();
        req.execute(url+params);
    }


    public void traiteFindAll(String result){
        Log.i("YOLO","traifindall revue");
        ArrayList<Revue1> liste = new ArrayList<Revue1>();
        try{
            JSONArray array = new JSONArray(result);
            for (int i =0; i< array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                Revue1 r = new Revue1(row.getInt("id_rev"),
                        row.getInt("dispo"),
                        row.getString("ref"),
                        row.getString("description"),
                        row.getString("nom_revue"),
                        row.getDouble("prix"),
                        row.getString("periodicite"),
                        row.getString("url"));
                liste.add(r);
            }
            activite.notifyRetourRequeteFindAll(liste);
        } catch(JSONException je){
            Log.i("YOLO","pb jsonrevulibre" + je);
        }
    }
}
