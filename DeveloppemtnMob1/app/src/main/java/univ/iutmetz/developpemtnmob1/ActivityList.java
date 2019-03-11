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


public class ActivityList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Revue> listeRevues;
    private ListView listView;
    private ArrayList<Bitmap> listeImages;
    private ListeRevuesAdaptateur adaptateur;
    private String utilisateur;
    private TextView bn;
    private int indiceMAJ;
    public static final int APPEL_NOUVELLE =1;
    public static final int ACTION_ANNULEE=1;
    public static final int APPEL_MAJ =2;
    private RevueDAO revueDAO = RevueDAO.getInstanceRevueDAO(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.listeRevues = RevueDAO.getInstanceRevueDAO(this).findAll();
      this.utilisateur=this.getIntent().getStringExtra("user");
        if (savedInstanceState != null){
            this.utilisateur = savedInstanceState.getString("user");
            this.listeRevues= (ArrayList<Revue>)savedInstanceState.getSerializable("liste");
            this.indiceMAJ=savedInstanceState.getInt("indice");

        }

        listeImages = new ArrayList<>();
        for(int i=0;i<listeRevues.size(); i++){
            listeImages.add(null);
            this.chargeImage(i);
        }
        Log.i("cycle", "onCreate");



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
        this.listView = this.findViewById(R.id.la_liste);
        this.adaptateur = new ListeRevuesAdaptateur(
                this,
                this.listeRevues, this.listeImages);
        this.listView.setAdapter(this.adaptateur);
        this.listView.setAdapter(adaptateur);
        this.listView.setOnItemClickListener(this);
        this.bn =(TextView)this.findViewById(R.id.la_bienvenue);
        bn.setText(String.format(getResources().getString(R.string.bon),this.utilisateur));
        Log.i("cycle", "onStart");
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

        outState.putString("user",this.utilisateur);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode==SaisieActivity.ACTION_ANNULEE){
            return;
        }

        if (requestCode==APPEL_NOUVELLE && resultCode==SaisieActivity.ACTION_VALIDEE){
            listeRevues.add((Revue) data.getSerializableExtra("revue"));
            listeImages.add(null);
            chargeImage(this.listeImages.size()-1);
        }
        else {
            listeRevues.set(indiceMAJ,(Revue) data.getSerializableExtra("revue"));
            chargeImage(this.listeImages.size()-1);
        }
        revueDAO.ecriture(listeRevues);
    }

    private void chargeImage(int idx) {
        ImageFromURL ifu = new ImageFromURL(this);
        ifu.execute("https://devweb.iutmetz.univ-lorraine.fr/~laroche5/devmob_unes/"+this.listeRevues.get(idx).getVisuel(), String.valueOf(idx));
    }

    public void receptionneImage(Object[] result){
        listeImages.set(Integer.parseInt(result[1].toString()),(Bitmap)result[0]);
        this.adaptateur.notifyDataSetChanged();
    }
}


