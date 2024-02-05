package com.aplication.lots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RelativeLayout splashRelative;
    Animation setAnimation;
    TextView splashtext;
    ImageView splashimg;

    /* FileWriter writer;

    {
        try {
            writer = new FileWriter("isFirstRun.txt");
            writer.write("true");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


     */

    HomeScreen home = new HomeScreen();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(home.firstStart);
        ;

        /*In the first run, the SharedPreferences is used to set the String valriable from "NO"
            to "YES"

         */
        SharedPreferences sPref = getSharedPreferences("prefs", MODE_PRIVATE);
        String firstStart = sPref.getString("firstStart", "YES");
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("firstStart", firstStart);
        editor.apply();


        //editor.putBoolean("firstStart", false);
        //editor.apply();


        //Change the Navigation Bar (found below the phone) color
        getWindow().setNavigationBarColor(getResources().getColor(R.color.SplashBackground));


        //Change the Status Bar (found below the phone) color
        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));

         splashRelative = (RelativeLayout) findViewById(R.id.Splashlayout);

         //loads the splash screen animation
         setAnimation = AnimationUtils.loadAnimation(this, R.anim.splashanim);
         splashimg = (ImageView) findViewById(R.id.koklogo);
         splashtext = (TextView) findViewById(R.id.koklogotext);

         //starts the splash screen animation
        splashRelative.startAnimation(setAnimation);


        //handlers are used to manage threads, delay execution of code, repeat task, and control flow code execution
        //In this case, it is used to delay the execution of code
        new Handler().postDelayed(new Runnable() {

            //runnable is used to execute tasks in the background or on a separate thread to avoid the
            // main thread and keep the app responsive
            @Override
            public void run() {

                System.out.println("HOMECREEN: " + firstStart);

                // This checks whether the app is running for the first time or not by checking the
                //condition of the "firstStart" string variable

                if (firstStart.equals("YES")) {

                    Intent intent = new Intent(MainActivity.this, OnBoarding.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);

                }




                // we use finish() to prevent on going back to the splash screen
                finish();
            }
            //delay for 3 seconds
        }, 3000);

        System.out.println("AFTER");
    }
}