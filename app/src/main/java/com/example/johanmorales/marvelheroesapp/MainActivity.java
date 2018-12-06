package com.example.johanmorales.marvelheroesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;

/*
* Librerias usadas por gradle
* https://square.github.io/retrofit/
* https://github.com/google/gson
* http://square.github.io/picasso/
* */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    TextView hashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashTextView = findViewById(R.id.hashTextView);

        try {
            Log.d(TAG,"Intentando obtener hash:");
            Log.d(TAG,Autenticacion.getHash());

            hashTextView.setText(Autenticacion.getHash());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
