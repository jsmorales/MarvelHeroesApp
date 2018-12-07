package com.example.johanmorales.marvelheroesapp.API;

import android.util.Log;

import com.example.johanmorales.marvelheroesapp.Autenticacion;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.johanmorales.marvelheroesapp.MainActivity.TAG;

public class MarvelService {

    public static Marvel getMarvelApi(){

        //se debe modificar el okHttp para poder usar los parametros de forma dinamica en
        //la URL, esto se usa para enviar los mismos valores que se necesiten siempre
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //la cadena que se retorna es la peticion http

                Request requestOriginal = chain.request();

                HttpUrl urlOriginal = requestOriginal.url();

                //se agregan los valores por medio del metodo y se contruye
                HttpUrl httpUrl = urlOriginal.newBuilder()
                        .addQueryParameter(Marvel.APIKEY_KEY,Autenticacion.PUBLIC_KEY)
                        .addQueryParameter(Marvel.TS_KEY,Autenticacion.TS)
                        .addQueryParameter(Marvel.HASH_KEY,Autenticacion.getHash())
                        .build();

                //ahora se crea la nueva peticion con la url que se ha creado
                Request.Builder requestBuilder = requestOriginal.newBuilder().url(httpUrl);

                Request request = requestBuilder.build();

                Log.d(TAG, "La cadena que se esta ejecutando es: "+httpUrl.toString());

                return chain.proceed(request);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(Autenticacion.URL_BASE)
                .client(client) //se pasa el nuevo cliente para que tome la nueva configuraciòn
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Marvel.class);
    }
}
