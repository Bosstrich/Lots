package com.aplication.lots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainRhema extends AppCompatActivity {

    TextView heading;

    Button rhemabtn, homebtn;
    ImageView rhema;

    Random r = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rhema);

        //Change the Navigation Bar (found below the phone) color
        getWindow().setNavigationBarColor(getResources().getColor(R.color.darker_white));


        //Change the Status Bar (found below the phone) color
        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));

        heading = (TextView) findViewById(R.id.mr_heading);
        rhemabtn = (Button) findViewById(R.id.mr_rhemabtn);
        homebtn = (Button) findViewById(R.id.mr_rhemabtn2);
        rhema = (ImageView) findViewById(R.id.mr_rhema);

        int num_of_rhemas = 203;

        int [] rhema_pics = new int[num_of_rhemas];

        for(int i = 0; i < num_of_rhemas; i++){

            String rhema_name = "rhema_" + i;
            int rhema_ids = getResources().getIdentifier(rhema_name, "drawable", getPackageName());
            rhema_pics[i] = rhema_ids;
        }

        rhema.setImageResource(rhema_pics[r.nextInt(rhema_pics.length)]);

        appendHeading();
        



        rhemabtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainRhema.this, Transition.class);
                startActivity(intent);
                finish();

            }



        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainRhema.this, HomeScreen.class);
                startActivity(intent);
                finish();


            }
        });

    }

    private void appendHeading() {

        SharedPreferences spref = getSharedPreferences("pref", MODE_PRIVATE);
        String gender = spref.getString("gender", "");


        if(gender.equals("MALE")){

            heading.append(" Son");

        }else
            heading.append(" Daughter");


    }


}

