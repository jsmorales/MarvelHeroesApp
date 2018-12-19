package com.example.johanmorales.marvelheroesapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
    public static final int SUCCESS_CODE = 200;
    public static final String SUPERHEROES_LIST = "superheroes_list";

    private FrameLayout framelayout;
    private ArrayList<SuperHero> superHeroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG,"Intentando obtener hash:");
        Log.d(TAG,Autenticacion.getHash());


        //este frame es solo un placeholder, esta puesto para solo poner trozos
        //o los fragments sobre este, para poder poner estos fragments se necesita
        //un fragmentManager

        framelayout = findViewById(R.id.placeHolderFrameLayout);

        //hashTextView.setText(Autenticacion.getHash());

        //probando retrofit

        createListHeroes();

    }


    public void createListHeroes(){

        //se crea la llamada de acuerdo a la implementacion hecha
        Call<BaseResponse<Data<ArrayList<SuperHero>>>> superHeroesCall = MarvelService.getMarvelApi().getHeroesSortInverted(SERIES_ID,"name", 100);

        //se ejecuta el metodo para poner la peticion en fila sobreecribiendo los metodos
        superHeroesCall.enqueue(new Callback<BaseResponse<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Response<BaseResponse<Data<ArrayList<SuperHero>>>> response) {

                if(response.code() == SUCCESS_CODE){

                    //Toast.makeText(MainActivity.this, "Respuesta: "+response.code(), Toast.LENGTH_SHORT).show();
                    /*superHeroList = response.body().getData().getResults();
                    SuperHero hero0 = superHeroList.get(0);
                    Toast.makeText(MainActivity.this, "Hero Name: "+hero0.getName(), Toast.LENGTH_SHORT).show();*/

                    superHeroList = response.body().getData().getResults();

                    //pasar los valores al fragment
                    //se crea un bundle
                    Bundle bundle = new Bundle();
                    //con el metodo putParcelableArrayList se agrega con la llave tipo string y el array de heroes
                    bundle.putParcelableArrayList(SUPERHEROES_LIST,superHeroList);

                    //Se crea FragmentManager para manejar nuestro fragment
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    //se valida si ya se tiene un fragment de tipo HeroListFragment por medio dl tag que se asigna en la transaccion
                    //que se agrega en fragmentTransaction.add
                    HeroListFragment savedHeroListFragment = (HeroListFragment) fragmentManager.findFragmentByTag(HERO_LIST_FRAGMENT_TAG);

                    if(savedHeroListFragment == null) {
                        //si no encuentra el fragment crea uno nuevo, de lo contrario no hace nada

                        //se crea un fragment transaction para hacer el movimiento del fragment
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //se instancia la clase java del fragmento
                        HeroListFragment heroListFragment = new HeroListFragment();

                        //se agregan argumentos con el metodo setArguments para el fragment
                        heroListFragment.setArguments(bundle);

                        //se agrega la transaccion
                        fragmentTransaction.add(R.id.placeHolderFrameLayout, heroListFragment, HERO_LIST_FRAGMENT_TAG);
                        //se ejecuta la transaccion
                        fragmentTransaction.commit();

                    }

                }else{
                    //Toast.makeText(MainActivity.this, "Respuesta: "+response.code(), Toast.LENGTH_SHORT).show();
                    getRetryMethod("Respuesta: "+response.code()+", "+getString(R.string.error_service_message));
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                //Toast.makeText(MainActivity.this, "Algo salió mal.", Toast.LENGTH_SHORT).show();

                getRetryMethod(getString(R.string.network_error_message));
            }

        });

    }

    public void getRetryMethod(String mensaje){

        //uso de snackbar
        Snackbar snackbar = Snackbar.make(framelayout,mensaje,Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry_message), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Intentando de nuevo Lista de Heroes.", Toast.LENGTH_SHORT).show();
                        createListHeroes();
                    }
                });

        snackbar.show();
    }

}
