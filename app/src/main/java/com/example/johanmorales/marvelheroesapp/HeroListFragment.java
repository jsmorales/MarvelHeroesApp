package com.example.johanmorales.marvelheroesapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.johanmorales.marvelheroesapp.Adapters.SuperHeroesAdapter;
import com.example.johanmorales.marvelheroesapp.Models.SuperHero;

import java.util.ArrayList;

public class HeroListFragment extends Fragment {

    public static final String TAG = HeroListFragment.class.getSimpleName();
    ArrayList<SuperHero> superheroes;

    public HeroListFragment() {
        // Required empty public constructor
    }

    public interface HeroListClickListener{

        void onHeroClicked(SuperHero superHero);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //en esta etapa del ciclo de vida solo se utiliza para obtener datos
        //no se puede utilizar para nada grafico

        Bundle bundle = getArguments();

        superheroes = bundle.getParcelableArrayList(MainActivity.SUPERHEROES_LIST);

        //validar que la lista de heroes no sea null
        if(superheroes == null){
            Log.d(TAG, "No hay valores desde el bundle.");
            Toast.makeText(getContext(), "No se pudo obtener la lista.", Toast.LENGTH_SHORT).show();
        }else{
            //Se obtuvo la lista de heroes correctamente.
            //getContext() obtiene el contexto actual para el toast
            Toast.makeText(getContext(), "El primero super Heroe es: "+superheroes.get(0).getName(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //El apartado grafico se maneja en esta parte del ciclo de vida.

        View view = inflater.inflate(R.layout.fragment_hero_list,container,false);

        RecyclerView heroListRecyclerView = view.findViewById(R.id.superHeroListRecyclerView);

        //se setea el layoutmanager recien creado
        heroListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //The adapter type Recycler
        SuperHeroesAdapter heroAdapter = new SuperHeroesAdapter(superheroes, getContext(), new HeroListClickListener(){
            @Override
            public void onHeroClicked(SuperHero superHero) {
                //cambiar de fragment a el hero detail fragment
                goToHeroDetailFragment();
            }
        });

        heroListRecyclerView.setAdapter(heroAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void goToHeroDetailFragment() {
        Toast.makeText(getContext(), "Click al elemento heroe!", Toast.LENGTH_SHORT).show();
    }

}
