package com.aplication.lots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;


import com.aplication.lots.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //instantiate each fragment: The Home, the Biblical, and the Info
    HomeFragment homeFragment = new HomeFragment();
    BiblicalFragment biblicalFragment = new BiblicalFragment();
    InfoFragment infoFragment = new InfoFragment();

    //Dialog is customable, whereas AlertDialog isn't
    Dialog selGender;

    //for indicating that the button is pressed or not
    ToggleButton malebtn, fembtn;

    boolean firstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        SharedPreferences sPref = getSharedPreferences("prefs", MODE_PRIVATE);


        String firstStart = sPref.getString("firstStart", "");

        System.out.println("AFTER HOME ONCREATE:" + firstStart);
        selGender = new Dialog(this);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);


        //by default, the homefragment is opened
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        //Change the Navigation Bar (found below the phone) color
        getWindow().setNavigationBarColor(getResources().getColor(R.color.darker_white));


        //Change the Status Bar (found below the phone) color
        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.home:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        return true;

                    case R.id.biblical:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, biblicalFragment).commit();
                        return true;

                    case R.id.info:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, infoFragment).commit();
                        return true;

                }
                return false;
            }
        });

        runGenderSelection();


    }

    private void runGenderSelection() {

        SharedPreferences sPref = getSharedPreferences("prefs", MODE_PRIVATE);
        String firstStart = sPref.getString("firstStart", "");

        selGender.setContentView(R.layout.gender_select);
        selGender.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selGender.setCanceledOnTouchOutside(false);
        selGender.setCancelable(false);

        malebtn = (ToggleButton) selGender.findViewById(R.id.male);
        fembtn = (ToggleButton)  selGender.findViewById(R.id.female);

        malebtn.setText(null);
        malebtn.setTextOn(null);
        malebtn.setTextOff(null);

        fembtn.setText(null);
        fembtn.setTextOn(null);
        fembtn.setTextOff(null);

        System.out.println("BEFORE GENDER SELECTION" + firstStart);

        if(firstStart.equals("YES")){

            System.out.println("nisuod sya dani gard");



            selGender.show();


            malebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    malebtn.setChecked(isChecked);

                    if(isChecked){

                        new AlertDialog.Builder(HomeScreen.this)
                                .setTitle("GENDER SELECTION")
                                .setMessage("Confirm Gender: MALE?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("gender", "MALE");
                                        editor.apply();

                                        changeFirstStart();

                                        selGender.dismiss();

                                    }
                                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {



                                        malebtn.setChecked(false);
                                    }
                                }).show();

                    }

                }
            });

            fembtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    fembtn.setChecked(isChecked);

                    if(isChecked){

                        new AlertDialog.Builder(HomeScreen.this)
                                .setTitle("GENDER SELECTION")
                                .setMessage("Confirm Gender: FEMALE?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("gender", "FEMALE");
                                        editor.apply();

                                       changeFirstStart();

                                        selGender.dismiss();

                                    }
                                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        fembtn.setChecked(false);

                                    }
                                }).show();
                    }

                }
            });



        }


    }

    public void changeFirstStart(){

        SharedPreferences sPref = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("firstStart", "NO");
        editor.apply();

        System.out.println("AFTER GENDER SELECT:" + firstStart);


    }

}