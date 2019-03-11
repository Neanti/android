package univ.iutmetz.developpemtnmob1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListeRevuesAdaptateur extends ArrayAdapter<Revue> {

    private ArrayList<Bitmap> listeIcones;

    public ListeRevuesAdaptateur(Context contexte, ArrayList<Revue> liste, ArrayList<Bitmap> icones){
        super(contexte,0,liste);
        this.listeIcones =icones;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Revue uneRevue = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_liste_revues, parent, false);
        }
        TextView tvNom = convertView.findViewById(R.id.la_nom);
        tvNom.setText(uneRevue.getTitle());
        ImageView imgIcone = convertView.findViewById(R.id.la_visuel);
        imgIcone.setImageBitmap(this.listeIcones.get(position));

        return convertView;
    }

}

