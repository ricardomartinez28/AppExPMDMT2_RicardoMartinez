package com.example.a21759217.appexpmdmt2_ricardomartinez;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a21759217.appexpmdmt2_ricardomartinez.RetrofitUtils.APIRestService;
import com.example.a21759217.appexpmdmt2_ricardomartinez.RetrofitUtils.RetrofitClient;
import com.example.a21759217.appexpmdmt2_ricardomartinez.model.Skin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    private AdaptadorSkin adaptador;
    private LinearLayoutManager llm;

    private EditText etRareza;


    private ArrayList<Skin> datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        rvMain.setItemAnimator(new DefaultItemAnimator());

        llm = new LinearLayoutManager(this);
        rvMain.setLayoutManager(llm);

        etRareza = findViewById(R.id.etRareza);

    }

    public void buscarSkin(View v) {

         if(isNetworkAvailable()){
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);

        String rareza = etRareza.getText().toString().trim();
        if (rareza.equals("")) {
            Call<ArrayList<Skin>> call = ars.obtenerSkins();

            call.enqueue(new Callback<ArrayList<Skin>>() {
                @Override
                public void onResponse(Call<ArrayList<Skin>> call, Response<ArrayList<Skin>> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {
                        datos = response.body();

                        if (datos != null) {
                            adaptador = new AdaptadorSkin(datos);
                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(MainActivity.this, DatosSkinActivity.class);
                                    i.putExtra("ID", datos.get(rvMain.getChildAdapterPosition(v)).getIdentifier());
                                    startActivity(i);
                                }
                            });
                            rvMain.setAdapter(adaptador);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Skin>> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });

        } else {

            if (esRareza()) {

                Call<ArrayList<Skin>> call = ars.obtenerskinRareza(rareza);

                call.enqueue(new Callback<ArrayList<Skin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Skin>> call, Response<ArrayList<Skin>> response) {
                        if (!response.isSuccessful()) {
                            Log.i("Resultado: ", "Error" + response.code());
                        } else {
                            datos = response.body();

                            if (datos != null) {
                                adaptador = new AdaptadorSkin(datos);
                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(MainActivity.this, DatosSkinActivity.class);
                                        i.putExtra("ID", datos.get(rvMain.getChildAdapterPosition(v)).getIdentifier());
                                        startActivity(i);
                                    }
                                });
                                rvMain.setAdapter(adaptador);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Skin>> call, Throwable t) {
                        Log.e("error", t.toString());
                    }
                });


            } else {
                Toast.makeText(MainActivity.this, "Debes introducir una rareza valida", Toast.LENGTH_LONG).show();
            }

        }
    }

}

    private boolean esRareza() {
        //(legendary, epic, rare, uncommon o common)
        String rareza = etRareza.getText().toString().trim().toLowerCase();
        if (rareza.equals("legendary") || rareza.equals("epic") || rareza.equals("rare") || rareza.equals("uncommon") || rareza.equals("common")) {
            return true;
        } else {
            return false;
        }

    }


    private boolean isNetworkAvailable() {
        //Forzamos que sea true
        boolean isAvailable = false;
        //Gestor de conectividad
        ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        //Objeto que recupera la información de la red
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Si la información de red no es nula y estamos conectados
        //la red está disponible
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

}
