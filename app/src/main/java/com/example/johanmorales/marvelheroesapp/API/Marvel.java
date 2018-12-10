package com.example.johanmorales.marvelheroesapp.API;

import com.example.johanmorales.marvelheroesapp.Autenticacion;
import com.example.johanmorales.marvelheroesapp.Models.BaseResponse;
import com.example.johanmorales.marvelheroesapp.Models.Data;
import com.example.johanmorales.marvelheroesapp.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//se crea una interfaz en la cual se definen las peticiones que se van a ejecutar a la API

public interface Marvel {

    String HASH = Autenticacion.getHash();
    String APIKEY_KEY = "apikey";
    String HASH_KEY = "hash";
    String TS_KEY = "ts";

    //se deben agregar los valores de la url por medio de un interceptor

    @GET("v1/public/series/{seriesId}/characters")

    /*
    * Se define la llamada con los modelos creados
    * LLamada que retorna un BaseResponse el cual necesita un Data
    * que Tiene un array list de SuperHeroes
    * */

    Call<BaseResponse<Data<ArrayList<SuperHero>>>> getHeroes(@Path("seriesId") int seriesId);

    //Ejemplo peticion de query parameters, parametros que no se usan todo el tiempo
    @GET("v1/public/series/{seriesId}/characters")
    Call<BaseResponse<Data<ArrayList<SuperHero>>>> getHeroesSortInverted(@Path("seriesId") int seriesId, @Query("orderBy") String orderBy, @Query("limit") Integer Limit);
}
