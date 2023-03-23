package com.example.jukeboxbartolini;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class secondActivity extends AppCompatActivity {


    private Button bottoneYt;

    //array dalla quale prendo l'indice delle canzoni per stamparlo
    private String[] canzoni = new String[]{
        "Staring At The Sun",
        "Thousand Bad Times",
        "Do It Right",
        "Love Again",
        "Come & Go",
        "Never Sleep",
        "Charlie Be Qiet",
        "Chosen 1",
        "High",
        "Cash In Cash Out"
    };

    private String[] linkYt = {
        "https://youtu.be/Wq6EeYFiAZU",
        "https://youtu.be/ul-9U681Y2c",
        "https://youtu.be/kcr3NC7fsKY",
        "https://youtu.be/Mc-wFcieEmU",
        "https://youtu.be/kcr3NC7fsKY",
        "https://youtu.be/5Di20x6vVVU",
        "https://youtu.be/BnsWBJnyJiQ",
        "https://youtu.be/vo_XZmKlJ3A",
        "https://youtu.be/7ALoFoQbVrU",
        "https://youtu.be/OfjWhEV9NpE",
        "https://youtu.be/o9vvbvcc3wo"
    };

    TextView txt;
    TextView canzone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();



        //codice per stampare il numero generato random nella main Activity
        txt = findViewById(R.id.textNumber);
        String numero = bundle.getString("String");
        if(numero!= null){
            txt.setText(numero);
        }else{
            txt.setText("Errore");
            return;
        }

        int songIndex = bundle.getInt("Num"); //recupero il valore sotto forma di numero per trovare il nome della canzone che voglio stampare
        canzone = findViewById(R.id.arrayCanzoni);
        canzone.setText(canzoni[songIndex]);

        bottoneYt = findViewById(R.id.link);
        bottoneYt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yt();
            }
        });


    }


    public void Yt(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkYt[getIntent().getIntExtra("Num",0)]));
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
