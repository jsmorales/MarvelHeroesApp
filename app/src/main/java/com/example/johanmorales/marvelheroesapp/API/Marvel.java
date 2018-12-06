package com.example.johanmorales.marvelheroesapp.API;

import com.example.johanmorales.marvelheroesapp.Autenticacion;
import com.example.johanmorales.marvelheroesapp.Models.BaseResponse;
import com.example.johanmorales.marvelheroesapp.Models.Data;
import com.example.johanmorales.marvelheroesapp.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//se crea una interfaz en la cual se definen las peticiones que se van a ejecutar a la API

public interface Marvel {

    String hash = Autenticacion.getHash();

    @GET("v1/public/series/{seriesId}/characters?apikey=6062af1679edc54bc4ae69791d995528&ts=1&hash=db963a1820699d2ca3b1b041a2133e68")

    /*
    * Se define la llamada con los modelos creados
    * LLamada que retorna un BaseResponse el cual necesita un Data
    * que Tiene un array list de SuperHeroes
    * */

    Call<BaseResponse<Data<ArrayList<SuperHero>>>> getHeroes(@Path("seriesId") int seriesId);
}
