package com.example.johanmorales.marvelheroesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johanmorales.marvelheroesapp.API.MarvelService;
import com.example.johanmorales.marvelheroesapp.Models.BaseResponse;
import com.example.johanmorales.marvelheroesapp.Models.Data;
import com.example.johanmorales.marvelheroesapp.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* Librerias usadas por gradle
* https://square.github.io/retrofit/
* https://github.com/google/gson
* http://square.github.io/picasso/
* */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int SERIES_ID = 354;
    

    TextView hashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashTextView = findViewById(R.id.hashTextView);

        Log.d(TAG,"Intentando obtener hash:");
        Log.d(TAG,Autenticacion.getHash());

        //hashTextView.setText(Autenticacion.getHash());

        //probando retrofit

        //se crea la llamada de acuerdo a la implementacion hecha
        Call<BaseResponse<Data<ArrayList<SuperHero>>>> superHeroesCall = MarvelService.getMarvelApi().getHeroes(SERIES_ID);

        //se ejecuta el metodo para poner la peticion en fila sobreecribiendo los metodos
        superHeroesCall.enqueue(new Callback<BaseResponse<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Response<BaseResponse<Data<ArrayList<SuperHero>>>> response) {

                SuperHero hero0 = response.body().getData().getResults().get(0);

                Toast.makeText(MainActivity.this, "Hero Name: "+hero0.getName(), Toast.LENGTH_SHORT).show();

                hashTextView.setText(hero0.getName());
            }

            @Override
            public void onFailure(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Algo salió mal.", Toast.LENGTH_SHORT).show();
            }
        });



        //se crea la llamada de acuerdo a la implementacion hecha
        Call<BaseResponse<Data<ArrayList<SuperHero>>>> superHeroesCallSort = MarvelService.getMarvelApi().getHeroesSortInverted(SERIES_ID,"-name");

        //se ejecuta el metodo para poner la peticion en fila sobreecribiendo los metodos
        superHeroesCallSort.enqueue(new Callback<BaseResponse<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Response<BaseResponse<Data<ArrayList<SuperHero>>>> response) {

                SuperHero hero0 = response.body().getData().getResults().get(0);

                Toast.makeText(MainActivity.this, "Hero Name: "+hero0.getName(), Toast.LENGTH_SHORT).show();

                hashTextView.setText(hero0.getName());
            }

            @Override
            public void onFailure(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Algo salió mal.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
