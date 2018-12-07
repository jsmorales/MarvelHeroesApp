package com.example.johanmorales.marvelheroesapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
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
    private static final String HERO_LIST_FRAGMENT_TAG = "hero_list_fragment";

    private FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


            }

            @Override
            public void onFailure(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Algo salió mal.", Toast.LENGTH_SHORT).show();
            }
        });

        //este frame es solo un placeholder, esta puesto para solo poner trozos
        //o los fragments sobre este, para poder poner estos fragments se necesita
        //un fragmentManager

        framelayout = findViewById(R.id.placeHolderFrameLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //se crea un fragment transaction para hacer el movimiento del fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //se instancia la clase java del fragmento
        HeroListFragment heroListFragment = new HeroListFragment();

        //se agrega la transaccion
        fragmentTransaction.add(R.id.placeHolderFrameLayout,heroListFragment, HERO_LIST_FRAGMENT_TAG);
        //se ejecuta la transaccion
        fragmentTransaction.commit();
    }
}
