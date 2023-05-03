package com.example.jukeboxbartolini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Testo extends AppCompatActivity {

    TextView testo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testo);

        testo = findViewById(R.id.testoSong);

        String titolo = getIntent().getStringExtra("titolo");
        String testoCanzone = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(titolo)));
            String line;
            while ((line = reader.readLine()) != null) {
                testoCanzone += line + "\n";
            }
            reader.close();
        } catch (IOException e) {
            Log.e("ERRORE", "NON TROVATO FILE: " + titolo);
        }

        testo.setText(testoCanzone);
        testo.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}