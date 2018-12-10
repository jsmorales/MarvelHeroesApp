package com.example.johanmorales.marvelheroesapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johanmorales.marvelheroesapp.Models.SuperHero;
import com.example.johanmorales.marvelheroesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SuperHeroesAdapter extends RecyclerView.Adapter<SuperHeroesAdapter.viewHolder> {

    ArrayList<SuperHero> superHeroesArrayList;
    Context context;

    public SuperHeroesAdapter(ArrayList superHeroesArrayList, Context context){
        this.superHeroesArrayList = superHeroesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.hero_item_list, viewGroup, false);

        viewHolder superHeroViewHolder = new viewHolder(view);

        return superHeroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        //i refers to position
        SuperHero superhero = superHeroesArrayList.get(i);

        viewHolder.heroDetailTextView.setText(superhero.getName());

        Picasso.get().load(superhero.getThumbnail().getFullPath()).into(viewHolder.heroImageView);

        //En esta parte se usa picasso
        //viewHolder.heroImageView.setImageResource(superhero.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return superHeroesArrayList.size();
    }

    //se crea el viewHolder
    public class viewHolder extends RecyclerView.ViewHolder{

        public ImageView heroImageView;
        public TextView heroDetailTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            heroImageView = itemView.findViewById(R.id.heroPictureImageView);
            heroDetailTextView = itemView.findViewById(R.id.heroDetailNameTextView);
        }
    }
}
