package com.aplication.lots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Transition extends AppCompatActivity {

    Animation setAnimation, setAnimation2, setAnimation3, setAnimation4;
    TextView load1, load2, load3;
    ImageView loadbg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Change the Navigation Bar (found below the phone) color
        getWindow().setNavigationBarColor(getResources().getColor(R.color.darker_white));


        //Change the Status Bar (found below the phone) color
        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));


        setContentView(R.layout.activity_transition);

       load1 = (TextView) findViewById(R.id.load1txt);
        load2 = (TextView) findViewById(R.id.load2txt);
        load3 = (TextView) findViewById(R.id.load3txt);
        loadbg = (ImageView) findViewById(R.id.loadbg);

        AnimatorSet animatorSet = new AnimatorSet();

        setAnimation = AnimationUtils.loadAnimation(this, R.anim.loadanim1);
        setAnimation2 = AnimationUtils.loadAnimation(this, R.anim.loadanim2);
        setAnimation3 = AnimationUtils.loadAnimation(this, R.anim.loadanim3);
        setAnimation4 = AnimationUtils.loadAnimation(this, R.anim.fadeanim_load);


        setAnimation2.setStartOffset(800);
        setAnimation3.setStartOffset(1500);


        load1.setVisibility(View.VISIBLE);
        load1.startAnimation(setAnimation);

        loadbg.startAnimation(setAnimation4);



        load2.setVisibility(View.VISIBLE);
        load2.startAnimation(setAnimation2);


        setAnimation4.setFillAfter(true);





        load3.setVisibility(View.VISIBLE);
        load3.startAnimation(setAnimation3);





       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(Transition.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();

                Intent intent = new Intent(Transition.this, MainRhema.class);

                startActivity(intent, bundle);

                finish();



            }
        }, 4000);








    }

}