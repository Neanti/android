package univ.iutmetz.developpemtnmob1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ActiviteEnAttenteAvecResultat{

    private String mail = new String("driss.com");
    private String mdp = new String("driss");
    private EditText email ;
    private EditText pwd;
    private int indiceMAJ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("cycle", "onCreate");

    }

    @Override
    public void onStart(){
        super.onStart();

        this.email=this.findViewById(R.id.edit_email);
        this.pwd=this.findViewById(R.id.edit_pwd);

        UserDAO.getInstanceUserDAO(this).findAll();

        Log.i("cycle", "onStart");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i("cycle","onResume");

    }

    public void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }

    public void connection(View view) {
        if (this.email.getText().toString().trim().isEmpty() || this.pwd.getText().toString().trim().isEmpty()) {
            Toast.makeText(this,"Veuillez saisir une adresse mail et/ou un mot de passe" ,Toast.LENGTH_LONG).show();
        }
        else {
            User u = new User(this.email.getText().toString(),this.pwd.getText().toString());
            Iterator<User> it = this.liste.iterator();
            User r;
            while(it.hasNext())
            {
             r = it.next();
             if(u.compare(r)==1){
                 Intent intent = new Intent(MainActivity.this, ActivityList.class);
                 intent.putExtra("mail",r.getMail());
                 intent.putExtra("droit",r.isDroit() +"");
                 startActivityForResult(intent, indiceMAJ);
             }
             else if (u.compare(r)==2){
                 Intent intent = new Intent(MainActivity.this, ActivityList.class);
                 intent.putExtra("mail",r.getMail());
                 intent.putExtra("droit",r.isDroit() +"");

                 startActivityForResult(intent, indiceMAJ);
             }
             Log.i("YOLO","NOT FOUND");
            }
        }
    }

    public void account(View view){
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivityForResult(intent, indiceMAJ);
    }

    @Override
    public void notifyRetourRequete(String resultat) {


    }

    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {
        this.liste = new ArrayList<>();
    this.liste=liste;
        Iterator<User> it = this.liste.iterator();
        User r;
        while(it.hasNext())
        {
            r = it.next();
            Log.i("YOLO",r.toString());
        }

    }

    public ArrayList<User> liste;
}
