package univ.iutmetz.developpemtnmob1;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDAO implements DAO<User> {

    private static UserDAO instanceUserDAO;
    private static ActiviteEnAttenteAvecResultat activite;


    public static UserDAO getInstanceUserDAO(ActiviteEnAttenteAvecResultat activite) {
        if(instanceUserDAO==null)
        {
            instanceUserDAO = new UserDAO(activite);
        }
        return instanceUserDAO;
    }

    private final String URL_SERVEUR =
            "https://devweb.iutmetz.univ-lorraine.fr/~gay28u/";


    private UserDAO(ActiviteEnAttenteAvecResultat activite){
        this.activite = activite;
    }


    public void findAll(){
        Log.i("YOLO","FindAll");
        RequeteSQL req = new RequeteSQL(activite,this);
        req.execute(URL_SERVEUR+"GetUser.php");
    }

    @Override
    public void Libre(User u) {

    }


    public void insert(User objet){
        RequeteSQL req =new RequeteSQL(activite,this);
        String url = URL_SERVEUR + "NewUser.php";
        String params = "?mail="+objet.getMail()+"&pass="+objet.getMdp();
        req.execute(url+params);
    }


    public void traiteFindAll(String result){
        Log.i("YOLO","traitefindall User");

        ArrayList<User> liste = new ArrayList<User>();
        try{
            JSONArray array = new JSONArray(result);
            for (int i =0; i< array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                User u = new User(row.getInt("uid"),
                        row.getString("mail"),
                        row.getString("pass"),
                        row.getInt("droit"));
                liste.add(u);
            }
            this.activite.notifyRetourRequeteFindAll(liste);
        } catch(JSONException je){
            Log.i("YOLO","json exp" + je);
        }
    }
}
