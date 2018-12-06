package com.example.johanmorales.marvelheroesapp.API;

import com.example.johanmorales.marvelheroesapp.Autenticacion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelService {

    public static Marvel getMarvelApi(){
        return new Retrofit.Builder().baseUrl(Autenticacion.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Marvel.class);
    }
}
