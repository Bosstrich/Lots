package com.aplication.lots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.animation.ValueAnimator;

import com.google.android.material.slider.Slider;


public class OnBoarding extends AppCompatActivity {

    ArgbEvaluator argbEvaluator;

    private Button nxtButton;
    private ViewPager viewPager;
    private LinearLayout layoutDots;
    private int custom_position = 0;
    private int current_page;

  //   Integer[] colors = null;
    // Integer[] colors2 = null;



    private SliderAdapter sliderAdapter;

    /* private void setUpColors(){

        int color11 = getResources().getColor(R.color.ob1);
        int color12 = getResources().getColor(R.color.ob12);
        int color13 = getResources().getColor(R.color.ob3);
        int color14 = getResources().getColor(R.color.ob4);

        Integer[] colors_temp = {color11, color12, color13, color14};
        colors = colors_temp;

    }

     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);



        //Change the Navigation Bar (found below the phone) color
        getWindow().setNavigationBarColor(getResources().getColor(R.color.darker_white));


        //Change the Status Bar (found below the phone) color
        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));



        viewPager = (ViewPager) findViewById(R.id.obViewPager);
        layoutDots = (LinearLayout) findViewById(R.id.dots);
        nxtButton = (Button) findViewById(R.id.nxttxt);

        sliderAdapter = new SliderAdapter(this );

        viewPager.setAdapter(sliderAdapter);

        prepareDots(custom_position++);

        //setUpColors();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

              /*  if (position < (sliderAdapter.getCount() - 1) && position < (colors.length - 1)) {

                    getWindow().setNavigationBarColor( getResources().getColor ((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1])));
                } else {


                   getWindow().setNavigationBarColor(getResources().getColor(colors[colors.length -1]));

                }

          */
            }

            @Override
            public void onPageSelected(int position) {

                current_page = position;
                prepareDots(current_page);

                if(position == 3){

                    nxtButton.setVisibility(View.VISIBLE);
                    nxtButton.setText("Finish");

                    nxtButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(OnBoarding.this, HomeScreen.class);

                            startActivity(intent);
                            finish();

                        }
                    });


                }else
                    nxtButton.setVisibility(View.GONE);

               /*  switch(position){

                    case 0:

                        getWindow().setNavigationBarColor(getResources().getColor(R.color.darker_white));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));
                        break;

                    case 1:

                        getWindow().setNavigationBarColor(getResources().getColor(R.color.SplashBackground));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.SplashBackground));
                        break;

                    case 2:

                        getWindow().setNavigationBarColor(getResources().getColor(R.color.ob3));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.ob3));
                        break;

                    case 3:

                        getWindow().setNavigationBarColor(getResources().getColor(R.color.ob4));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.ob4));

                        break;

                }

                */

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }




    private void prepareDots(int currentSlidePosition){


        if(layoutDots.getChildCount() > 0){

            layoutDots.removeAllViews();
        }
        ImageView dots[] = new ImageView[4];

        for (int i = 0; i < 4; i++) {

            dots[i] = new ImageView(this);
            if (i == currentSlidePosition){


                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));

            }else

                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(15, 0, 0, 0);

            layoutDots.addView(dots[i], layoutParams);




            
        }
    }
}