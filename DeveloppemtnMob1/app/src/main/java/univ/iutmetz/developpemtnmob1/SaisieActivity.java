package univ.iutmetz.developpemtnmob1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.Reference;

public class SaisieActivity extends AppCompatActivity {


    private EditText title ;
    private EditText fee ;
    private EditText reference ;
    private EditText description;
    private EditText image;

    public static final int ACTION_ANNULEE =1;
    public static final int ACTION_VALIDEE =2;
    private Button btn_update;
    Revue revueMAJ;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);

        if (this.getIntent().getSerializableExtra("revue")!=null){
            this.revueMAJ = (Revue) this.getIntent().getSerializableExtra("revue");

        }else {
            this.revueMAJ =null;
        }

        //Spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.periode,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Log.i("cycle", "onCreate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id==android.R.id.home){
            this.setResult(ACTION_ANNULEE);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();

        this.title=this.findViewById(R.id.edit_title);
        this.image=this.findViewById(R.id.edit_img);
        this.fee=this.findViewById(R.id.edit_fee);
        this.reference=this.findViewById(R.id.edit_ref);
        this.description=this.findViewById(R.id.edit_description);
        this.btn_update=this.findViewById(R.id.btn_add);
        this.spinner=this.findViewById(R.id.spinner);



        if(revueMAJ!=null){
            reference.setText(revueMAJ.getReference());
            title.setText(revueMAJ.getTitle());
            fee.setText(String.valueOf(revueMAJ.getFee()));
            description.setText(revueMAJ.getDescription());
            btn_update.setText("UPDATE");
            spinner.setSelection(revueMAJ.getPeriode());
            image.setText(revueMAJ.getVisuel());



        }
        Log.i("cycle", "onStart");
    }


    public void Add(View view){
        Revue r =new Revue();
    if(this.reference.getText().toString().isEmpty() || this.title.getText().toString().isEmpty() || this.description.getText().toString().isEmpty() ||  this.fee.getText().toString().isEmpty() ||  this.image.getText().toString().isEmpty()) {

        Toast.makeText(this, "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
    }
    else {
        Intent intent = new Intent();
        Intent revue =intent.putExtra("revue", r);
        r.setTitle(title.getText().toString());
        r.setVisuel(image.getText().toString());
        r.setFee(Float.parseFloat(fee.getText().toString()));
        r.setDescription(description.getText().toString());
        r.setPeriode(spinner.getSelectedItemPosition());
        r.setReference(reference.getText().toString());

        this.setResult(ACTION_VALIDEE, intent);

        Toast.makeText(this, title.getText() + " added " + " ( " + fee.getText() + " euros ) ", Toast.LENGTH_LONG).show();
        this.finish();
    }


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



public void onClickRetour(View view){
    this.setResult(ACTION_ANNULEE);
    this.finish();


}

    @Override
    public void onBackPressed() {
        this.onClickRetour(null);
    }
}
