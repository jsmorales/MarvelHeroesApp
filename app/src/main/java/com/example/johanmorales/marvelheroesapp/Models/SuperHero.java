package com.example.johanmorales.marvelheroesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SuperHero implements Parcelable {

    private Integer id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    protected SuperHero(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<SuperHero> CREATOR = new Creator<SuperHero>() {
        @Override
        public SuperHero createFromParcel(Parcel in) {
            return new SuperHero(in);
        }

        @Override
        public SuperHero[] newArray(int size) {
            return new SuperHero[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //se define cuales atributos se van a pasar cuando escriba el parsel
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        //de otro tipo
        dest.writeValue(thumbnail);
    }
}
