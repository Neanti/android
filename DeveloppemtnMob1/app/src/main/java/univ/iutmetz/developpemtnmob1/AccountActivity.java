package univ.iutmetz.developpemtnmob1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity implements ActiviteEnAttenteAvecResultat{



    private EditText mail;
    private EditText mdp;
    private int indiceMAJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    @Override
    public void onStart(){
        super.onStart();

        this.mail=this.findViewById(R.id.edit_newemail);
        this.mdp=this.findViewById(R.id.edit_newpwd);

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
    public void create (View view){
        if (this.mail.getText().toString().trim().isEmpty() || this.mdp.getText().toString().trim().isEmpty()) {
            Toast.makeText(this,"Veuillez saisir une adresse mail et/ou un mot de passe" ,Toast.LENGTH_LONG).show();
        }
        else {

            User u = new User(mail.getText().toString(),this.mdp.getText().toString());
            UserDAO.getInstanceUserDAO(this).insert(u);
            this.finish();
        }

    }

    @Override
    public void notifyRetourRequete(String resultat) {

    }

    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {


    }
}
