package com.example.johanmorales.marvelheroesapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johanmorales.marvelheroesapp.Models.SuperHero;
import com.squareup.picasso.Picasso;


public class HeroDetailFragment extends Fragment {

    public static final String TAG = HeroDetailFragment.class.getSimpleName();
    SuperHero superHero;

    TextView heroDetailTitleTextView;
    TextView heroDetailDescription;
    ImageView heroPictureImageView;

    public HeroDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {

            superHero = getArguments().getParcelable(HeroListFragment.SUPER_HERO);

            //validar que la lista de heroes no sea null
            if (superHero == null) {
                Log.d(TAG, "No hay heroe disponible.");
                Toast.makeText(getContext(), "No hay heroe disponible.", Toast.LENGTH_SHORT).show();
            } else {
                //Se obtuvo la lista de heroes correctamente.
                //getContext() obtiene el contexto actual para el toast
                Toast.makeText(getContext(), "El super Heroe es: " + superHero.getName(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hero_detail, container, false);

        heroDetailTitleTextView = view.findViewById(R.id.heroDetailTitleTextView);
        heroDetailDescription = view.findViewById(R.id.heroDetailDescriptionTextView);
        heroPictureImageView = view.findViewById(R.id.heroDetailThumbnailTextView);

        if(superHero != null) {

            Log.d(TAG, "La descripcion es: "+superHero.getDescription());

            heroDetailTitleTextView.setText(superHero.getName());
            heroDetailDescription.setText(!superHero.getDescription().equals("") ? superHero.getDescription() :  getString(R.string.no_information_message));

            Log.d(TAG,"Path image hero: "+superHero.getThumbnail().getFullPath());
            Picasso.get().load(superHero.getThumbnail().getFullPath()).into(heroPictureImageView);
        }
        // Inflate the layout for this fragment
        return view;
    }

}
