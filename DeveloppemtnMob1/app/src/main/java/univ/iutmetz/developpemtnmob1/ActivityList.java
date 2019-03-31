package univ.iutmetz.developpemtnmob1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivityList extends AppCompatActivity implements AdapterView.OnItemClickListener, ActiviteEnAttenteAvecResultat {

    private ArrayList<Revue1> listeRevues;
    private ListView listView;
    private ArrayList<Bitmap> listeImages;
    private ListeRevuesAdaptateur adaptateur;
    private TextView bn;
    private int indiceMAJ;
    public static final int APPEL_NOUVELLE =1;
    public static final int ACTION_ANNULEE=1;
    public static final int APPEL_MAJ =2;
    private Revue1DAO revue1DAO = Revue1DAO.getInstanceRevue1DAO(this);
    private User utilisateur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState != null){

            this.utilisateur.setMail(this.getIntent().getStringExtra("mail"));
            this.utilisateur.setDroit(Integer.valueOf(this.getIntent().getStringExtra("droit")));
            this.indiceMAJ=savedInstanceState.getInt("indice");
        }

        //listeImages = new ArrayList<>();
        //for(int i=0;i<listeRevues.size(); i++){
          //  listeImages.add(null);
            //this.chargeImage(i);
        //}
        Log.i("YOLO", "onCreateActivitylist");



    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id==android.R.id.home){
            this.setResult(ACTION_ANNULEE);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickRetour(View view){
        this.setResult(ACTION_ANNULEE);
        this.finish();


    }
    @Override
    public void onBackPressed() {
        this.onClickRetour (null);
    }




    @Override
    public void onStart() {
        super.onStart();
        Log.i("YOLO", "onStartActivitylistr");
        this.utilisateur = new User();
        this.utilisateur.setMail(this.getIntent().getStringExtra("mail"));
        this.utilisateur.setDroit(Integer.valueOf(this.getIntent().getStringExtra("droit")));
        Revue1DAO.getInstanceRevue1DAO(this).Libre(this.utilisateur);
        //Revue1DAO.getInstanceRevue1DAO(this).Libre(this.utilisateur);

        Log.i("YOLO", "onStartActivitylistr");


    }

    protected void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("cycle","onResume");

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("user",this.utilisateur.getMail());
        outState.putSerializable("liste", this.listeRevues);
        outState.putInt("indice", this.indiceMAJ);
    }


    public void onClickNouvelleRevue(View view){
        Intent intent = new Intent(ActivityList.this, SaisieActivity.class);
        startActivityForResult(intent,APPEL_NOUVELLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ActivityList.this, SaisieActivity.class);
        intent.putExtra("revue", this.listeRevues.get(position));
        startActivityForResult(intent, APPEL_MAJ);
        indiceMAJ=position;

    }



    private void chargeImage(int idx) {
        ImageFromURL ifu = new ImageFromURL(this);
        ifu.execute("https://devweb.iutmetz.univ-lorraine.fr/~gay28u/"+this.listeRevues.get(idx).getVisuel(), String.valueOf(idx));
    }

    public void receptionneImage(Object[] result){
        listeImages.set(Integer.parseInt(result[1].toString()),(Bitmap)result[0]);
        this.adaptateur.notifyDataSetChanged();
    }

    @Override
    public void notifyRetourRequete(String resultat) {

    }

    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {
        Log.i("YOLO","findallretour revue");
        this.listeRevues = new ArrayList<>();
        this.listeRevues =  liste;
        this.listView = this.findViewById(R.id.la_liste);
        listeImages = new ArrayList<>();
        for(int i=0;i<listeRevues.size(); i++){
            listeImages.add(null);
            this.chargeImage(i);
        }
        this.adaptateur = new ListeRevuesAdaptateur(
                this,
                this.listeRevues, this.listeImages);
        this.listView.setAdapter(this.adaptateur);
        this.listView.setAdapter(adaptateur);
        this.listView.setOnItemClickListener(this);
        this.bn =(TextView)this.findViewById(R.id.la_bienvenue);
        bn.setText(String.format(getResources().getString(R.string.bon),this.utilisateur.getMail()));
    }
}


