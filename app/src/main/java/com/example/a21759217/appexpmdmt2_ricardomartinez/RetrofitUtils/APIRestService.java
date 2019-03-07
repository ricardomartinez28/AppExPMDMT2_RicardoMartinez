package com.example.a21759217.appexpmdmt2_ricardomartinez.RetrofitUtils;

import com.example.a21759217.appexpmdmt2_ricardomartinez.model.Skin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRestService {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    @GET("skins")
    Call<ArrayList<Skin>> obtenerSkins();

    @GET("skins")
    Call<ArrayList<Skin>> obtenerskinRareza(@Query("rarity") String rareza);

}
