package com.example.a21759217.appexpmdmt2_ricardomartinez;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a21759217.appexpmdmt2_ricardomartinez.RetrofitUtils.APIRestService;
import com.example.a21759217.appexpmdmt2_ricardomartinez.RetrofitUtils.RetrofitClient;
import com.example.a21759217.appexpmdmt2_ricardomartinez.model.Skin;
import com.example.a21759217.appexpmdmt2_ricardomartinez.model.SkinDetalle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DatosSkinActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvRareza;
    private TextView tvCoste;
    private TextView tvEstrellas;
    private TextView tvPuntos;
    private TextView tvVotos;
    private ImageView ivSkin;

    private String id;
    private ArrayList<Skin> skin;
    private SkinDetalle skinDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_skin);

        tvNombre=findViewById(R.id.tvNombreSkin);
        tvDescripcion=findViewById(R.id.tvDescripcion);
        tvRareza=findViewById(R.id.tvRarezaSkin);
        tvCoste=findViewById(R.id.tvCoste);
        tvEstrellas=findViewById(R.id.tvEstrellas);
        tvPuntos=findViewById(R.id.tvPuntos);
        tvVotos=findViewById(R.id.tvVotos);
        ivSkin=findViewById(R.id.ivSkins);

        id=getIntent().getStringExtra("ID");
        cargarDatos();

    }

    private void cargarDatos() {

        if(isNetworkAvailable()){
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<SkinDetalle> call= ars.obtenerDetalle(id);


            call.enqueue(new Callback<SkinDetalle>() {
                @Override
                public void onResponse(Call<SkinDetalle> call, Response<SkinDetalle> response) {
                    if(!response.isSuccessful()){
                        Log.i("Resultado: ", "Error" + response.code());
                    }else{

                        skinDetalle=response.body();
                        cargarComponentes(skinDetalle);
                    }
                }

                @Override
                public void onFailure(Call<SkinDetalle> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });


        }
    }

    private boolean isNetworkAvailable() {
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

    public void cargarComponentes(SkinDetalle skinDetalle){

        tvNombre.setText(skinDetalle.getName());
        tvDescripcion.setText("Descripcion: "+skinDetalle.getDescription());
        tvRareza.setText("Rareza: "+skinDetalle.getRarity());
        tvCoste.setText("Coste: "+skinDetalle.getCost());
        tvEstrellas.setText("Stars: "+skinDetalle.getRatings().getAvgStars());
        tvPuntos.setText("Puntos: "+skinDetalle.getRatings().getTotalPoints());
        tvVotos.setText("Votos: "+skinDetalle.getRatings().getTotalPoints());
        ivSkin.setImageDrawable(getResources().getDrawable(skinDetalle.getSkinId(skinDetalle.getId(),this)));
        if(ivSkin==null){
            ivSkin.setVisibility(View.INVISIBLE);
        }

    }
}
