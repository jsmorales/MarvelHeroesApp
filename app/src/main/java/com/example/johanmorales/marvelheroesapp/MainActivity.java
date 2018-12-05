package com.example.johanmorales.marvelheroesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Log.d(TAG,"Intentando obtener hash:");
            Log.d(TAG,Autenticacion.getHash());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
