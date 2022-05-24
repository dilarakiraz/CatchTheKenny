package com.dilara.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText=(TextView) findViewById(R.id.timeText);
        scoreText=(TextView) findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[]{
                imageView,imageView2,imageView3,
                imageView4,imageView5,imageView6,
                imageView7,imageView8,imageView9
        };
        hideImages();

        score=0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("time off");
                handler.removeCallbacks(runnable);//runnableı çalışmayı durdurur
                for(ImageView image:imageArray){ //kennyi tamamen görünmez yapar zaman bitince
                    image.setVisibility(View.INVISIBLE);//görünmez olur
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Are you sure to restart game");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {//yes butonuna tıklarsa ne olacak
                        //restart
                        Intent intent=getIntent();
                        finish();//aktiviteyi bitirir
                        startActivity(intent);//kendi aktivetini baştan başlatır


                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();


            }
        }.start();

    }

    public void increaseScore(View view){
        score++;
        scoreText.setText("Score "+score);

    }
    public void hideImages(){
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);//görünmez olur
                }
                Random random=new Random();
                int i=random.nextInt(9);//0 ile 8 arasında herhangi değer
                imageArray[i].setVisibility(View.VISIBLE);//rastgele image görüntüler

                handler.postDelayed(this,400);//runnableı yazdığım periyotta çalıştır
            }
        };
        handler.post(runnable);//başlatır



    }
}