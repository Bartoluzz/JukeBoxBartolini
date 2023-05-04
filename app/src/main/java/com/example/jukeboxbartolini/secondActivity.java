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
import android.provider.MediaStore;
import android.view.KeyEvent;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class secondActivity extends AppCompatActivity {

    private MediaPlayer player;
    private boolean isPrepared = false;

    private Button bottoneYt;
    Canzone[] c = Canzone.init();

    private TextView txt, canzone, indietro;
    private ImageView image;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();

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


        indietro = findViewById(R.id.button3);

        seekBar = findViewById(R.id.seekBar);
        player = MediaPlayer.create(this,c[Num].path);
        seekBar.setMax(player.getDuration());


        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                isPrepared = true;
                seekBar.setMax(player.getDuration());
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isPrepared && player != null) {
                    if (player.isPlaying() || player.getCurrentPosition() > 0) {
                        seekBar.setProgress(player.getCurrentPosition());
                    }
                }
            }

        }, 0, 900);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if(b) {
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void Yt(){
        int Num = getIntent().getIntExtra("Num",0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[Num].link));//creo una nuova activity e apro il link della canzone
        startActivity(intent);
    }


    public void onClick(View view) {
        //reindirizzo sulla nuova activity
        Intent toFirst = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toFirst);

    }

    public void play(View v){
        int Num = getIntent().getIntExtra("Num",0);
        if(player == null){
            player = MediaPlayer.create(this, c[Num].path);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onStop();
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
        super.onStop();

    }

    public void releasePlayer(){
        try {
            player.stop();
            player.release();
        }catch (Exception e){

        }
    }

    protected void onDestroy(){
        releasePlayer();
        super.onDestroy();
    }


    public void lyrics(View v){
        int Num = getIntent().getIntExtra("Num",0);
        Intent intent = new Intent(secondActivity.this,Testo.class);
        intent.putExtra("titolo",c[Num].testo);
        startActivity(intent);
    }



}
