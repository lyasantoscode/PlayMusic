package com.lya.playmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //vamos associar a cada id criado aqui

    private ImageView pause, play, stop;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.global);

        //em seguida vamos colocaro evento de clique em cada um dos botoes

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer != null) {  //verificar se a musica é diferente de nulo, isso que dizer que tem alguma coisa a ser tocado
                    mediaPlayer.start();  //se nao estiver configurado, ele vai ser nulo por padrao, nao sendo possivel executar a musica
                //se for diferente de nulo quero executar uma musica
                    //start, executa a musica
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop(); //depois de parar, construimos novamente, usando o codigo abaixo, mediaplayer =

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.global); //passei para ele nao ser destruido
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mediaPlayer !=null) { //todas as vezes que abrir o app, vai exucutar a musica automaticamente, porque estamos usando o ciclo da vida onStart
            mediaPlayer.start();
        }
    }

    //ciclo de vida
    @Override
    protected void onStop() {
        super.onStop(); //, quando minimizar  o app,vai pasar pelo onpause e depois onstop e ai ele vai fazer essa verificão:
            if (mediaPlayer.isPlaying()){  // essa verificação, se o mediaPlayer estiver executando , ou seja se a nossa musica esiver tocando, ele vai pausar a musica, nesse caso a nossa
                    mediaPlayer.pause(); // ,,, musica vai esta tocando quando a gente minimizar o app, quando tiver tocando, nossa musica sera pausada, depois que iniciar novamente, dai vamos poder voltar de onde parou.
                }
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer !=null && mediaPlayer.isPlaying()) { // se a mediaplayer for diferente de nullo, quer dizer que tem alguma musica tocando , e precisamos fazer mais uma validação &&
            // usando o operador && , para fazer mais validação, se meu mediaPlayer estiver tocando, eu tambem quero parar a musica,  entao:
            mediaPlayer.stop();  //parar a musica
            mediaPlayer.release(); //liberar recursos, metodo release, liberar musica da memoria, remover caches do cell, remover a musica do cell
            mediaPlayer = null; //garantir que realmente nao tem nada, esta nulo
        //fazendo isso, quando destruir a atividade, vai parar  musica, vai liberar todo o recurso, e vai deixar o mediaPlayer nulo novamente

        }
    }
}
