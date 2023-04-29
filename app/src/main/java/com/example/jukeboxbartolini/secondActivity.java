package com.example.jukeboxbartolini;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Notification;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class secondActivity extends AppCompatActivity {

    MediaPlayer player;

    private Button bottoneYt;
    Canzone[] c = Canzone.init();

    TextView txt;
    TextView canzone;
    ImageView image;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("channel", "channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        //codice per stampare il numero generato random nella main Activity
        txt = findViewById(R.id.textNumber);
        String numero = bundle.getString("String");
        if(numero!= null){
            txt.setText(numero );
        }else{
            txt.setText("Errore");
            return;
        }

        int Num = getIntent().getIntExtra("Num", 0); //recupero il valore sotto forma di numero per trovare il nome della canzone che voglio stampare

        //setto il nome della canzone nella textview apposita
        canzone = findViewById(R.id.arrayCanzoni);
        canzone.setText(c[Num].nome);

        image = findViewById(R.id.imageView);
        image.setImageResource(c[Num].img);

        bottoneYt = findViewById(R.id.link);
        bottoneYt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yt();
            }
        });



    }

    //non funge ---> sistemare
    public void notification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mianotifica");
        builder.setContentTitle("Signora");
        builder.setContentText("I LIMONIIIII");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }

    public void Yt(){
        int Num = getIntent().getIntExtra("Num",0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[Num].link));//creo una nuova activity e apro il link della canzone
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }



    public void play(View v){
        int Num = getIntent().getIntExtra("Num",0);
        if(player == null){
            player = MediaPlayer.create(this, c[Num].path);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }

            });
        }

        player.start();
    }


    public void pause(View v){

        if(player != null){
            player.pause();
        }

    }


    public void stop(View v){
        stopPlayer();
    }

    @Override
    public void onStop(){
        super.onStop();
        stopPlayer();
    }


    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
            Toast.makeText(this, "Memoria liberata", Toast.LENGTH_SHORT).show();
        }
    }


    public void lyrics(View v){
        int Num = getIntent().getIntExtra("Num",0);
        Intent toThird = new Intent(secondActivity.this,Testo.class);
        toThird.putExtra("titolo",c[Num].testo);
        startActivity(toThird);
    }



}
