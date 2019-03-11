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

public class MainActivity extends AppCompatActivity {

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
        if (this.email.getText().toString().trim().equals(mail) && this.pwd.getText().toString().trim().equals(mdp)) {
            Intent intent = new Intent(MainActivity.this, ActivityList.class);
            intent.putExtra("user", this.email.getText().toString());
            startActivityForResult(intent, indiceMAJ);

        }
        else if(this.email.getText().toString().trim().isEmpty() || this.pwd.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Veuillez saisir une adresse mail et/ou un mot de passe" ,Toast.LENGTH_LONG).show();

        }


        else {
            Toast.makeText(this, "La tentative de connexion a échoué avec l'adresse " + email.getText().toString(), Toast.LENGTH_LONG).show();
        }
    }

}
