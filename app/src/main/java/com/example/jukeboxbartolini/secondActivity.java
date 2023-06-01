package com.example.jukeboxbartolini;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
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

        // Codice per stampare il numero generato random nella MainActivity
        txt = findViewById(R.id.textNumber);
        String numero = bundle.getString("String");
        if (numero != null) {
            txt.setText(numero);
        } else {
            txt.setText("Errore");
            return;
        }

        int Num = getIntent().getIntExtra("Num", 0); // Recupero il valore sotto forma di numero per trovare il nome della canzone che voglio stampare

        // Setto il nome della canzone nella TextView apposita
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

        seekBar = findViewById(R.id.seekBar);
        player = MediaPlayer.create(this, c[Num].path);
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
                if (b) {
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

    public void Yt() {
        int Num = getIntent().getIntExtra("Num", 0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[Num].link)); // Creo una nuova activity e apro il link della canzone
        startActivity(intent);
    }

    public void onClick(View view) {
        // Reindirizzo sulla nuova activity
        Intent toFirst = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toFirst);
    }

    public void play(View v) {
        int Num = getIntent().getIntExtra("Num", 0);
        if (player == null) {
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

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    public void lyrics(View v) {
        int Num = getIntent().getIntExtra("Num", 0);
        Intent intent = new Intent(secondActivity.this, Testo.class);
        intent.putExtra("titolo", c[Num].testo);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.pause();
            player.release();
            player = null;
        }
        super.onDestroy();
    }

    public boolean onSupportNavigateUp(){
        if (player != null) {
            player.pause();
            player.release();
            player = null;
        }
        onBackPressed();
        return true;
    }
}
