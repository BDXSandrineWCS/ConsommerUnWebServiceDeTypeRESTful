package fr.wcs.weathertoaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "910932b0abccc3a557c66b2addd5e44f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getObjectByJsonByVolley(getString(R.string.api_url)+API_KEY);
    }

    public void getObjectByJsonByVolley(String url) {

        // Crée une file d'attente pour les requêtes vers l'API at url
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");
                            int l=0;
                            while ( l < list.length()) {
                                JSONObject weatherOccurence = (JSONObject) list.get(l);
                                long unixDate= weatherOccurence.getLong("dt");
                                String stringDate = new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(new Date(unixDate*1000));
                                JSONArray weather = weatherOccurence.getJSONArray("weather");
                                for (int i = 0; i < weather.length(); i++) {
                                    JSONObject weatherInfos = (JSONObject) weather.get(i);
                                    String description = weatherInfos.getString("description");
                                    Toast.makeText(MainActivity.this, stringDate + " -> " +description, Toast.LENGTH_SHORT).show();
                                }
                                l+=8;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonObjectRequest);
    }
}
