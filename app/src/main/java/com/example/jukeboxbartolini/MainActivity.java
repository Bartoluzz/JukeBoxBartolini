package com.example.jukeboxbartolini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {




    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button buttonCliccami = findViewById(R.id.buttonCliccami); //dichiaro e collego un oggetto button
        txt = findViewById(R.id.textOUT);

        buttonCliccami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //un range di numeri random
                Random random = new Random();
                int val = random.nextInt(10)+1;
                //txt.setText(Integer.toString(val));

                //reindirizzo sulla nuova activity
                Intent toSecond = new Intent(MainActivity.this, secondActivity.class);
                toSecond.putExtra("String",Integer.toString(val) ); //per "mandare" la string
                toSecond.putExtra("Num",val); //per "mandare" il numero
                startActivity(toSecond);

            }
        });



    }
}