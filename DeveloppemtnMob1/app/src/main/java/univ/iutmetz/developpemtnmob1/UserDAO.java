package univ.iutmetz.developpemtnmob1;



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
        RequeteSQL req = new RequeteSQL(activite,this);
        req.execute(URL_SERVEUR+"FindAll.php");
    }


    public void insert(User objet){
        RequeteSQL req =new RequeteSQL(activite,this);
        String url = URL_SERVEUR + "NewUser.php";
        String params = "?mail="+objet.getMail()+"&pass="+objet.getMdp();
        req.execute(url+params);
    }


    public void traiteFindAll(String result){

        ArrayList<User> liste = new ArrayList<User>();
        try{
            JSONArray array = new JSONArray(result);
            for (int i =0; i< array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                User u = new User(row.getInt("id"),
                        row.getString("mail"),
                        row.getString("pass"),
                        row.getInt("droit"));
                liste.add(u);
            }
            this.activite.notifyRetourRequeteFindAll(liste);
        } catch(JSONException je){
            System.out.println("pb json" + je);
        }
    }
}
