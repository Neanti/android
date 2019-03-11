package univ.iutmetz.developpemtnmob1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.InputStream;

public class ImageFromURL extends AsyncTask<String, Void, Object[]> {
private ActivityList activite;

    public ImageFromURL(ActivityList activityList) {
    }

    protected Object[] doInBackground(String... urlEtIndice){
        String urlIcone = urlEtIndice[0];
        Bitmap icone = null;
        try {
            InputStream in = new java.net.URL(urlIcone).openStream();
            icone = BitmapFactory.decodeStream(in);
            in.close();

        } catch (Exception e) {
            Log.i("Pas d'icone", "utiliser un drawable générique à la place !");

        }
        return new Object[]{icone, urlEtIndice[1]};
    }

   @Override
   protected void onPostExecute(Object[] result){
        this.activite.receptionneImage(result);
   }

   protected void onPreExecute(){

   }
}
