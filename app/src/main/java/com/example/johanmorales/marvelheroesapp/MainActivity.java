package com.example.johanmorales.marvelheroesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hashTextView = findViewById(R.id.hashTextView);

        try {
            Log.d(TAG,"Intentando obtener hash:");
            Log.d(TAG,Autenticacion.getHash());

            hashTextView.setText(Autenticacion.getHash());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
