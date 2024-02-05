package com.aplication.lots;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


//Pager Adapter is the Base Class for adapters that provide a way to display a series of views in a
//Viwpager. It provides methods to instantiate and destroy views, and determine the number of views
//to display

//We create a class "SliderAdapter" that inherits the methods in PagerAdapter, enabling it to
//display a series of views specifically for a slider or carousel view.

//Pager Adapter used for ViewPager. Viewpager is a widget in android that provides horizontal
//swipable container for views, allowing the user to swipe left or right to navigate between views.


public class SliderAdapter extends PagerAdapter {


    //context is an object refers to the current state of the application, we use it to get information
    //of the applcation's environment
    Context context;

    //inflater is used to inflate or create a layout
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    public int[] slide_images = {

            R.drawable.lots1,
            R.drawable.lots2,
            R.drawable.lots3,
            R.drawable.lots4

    };


    public int[] slide_bimage = {

            R.drawable.obscreen1,
            R.drawable.obscreen2,
            R.drawable.obscreen3,
            R.drawable.obscreen4

    };

    public String[] stepstxt = {

            " 0)",
            " 1)",
            " 2)",
            " 3)"

    };

    public String[] slide_headings = {

            "Welcome to Lots!",
            "Confess your Sins",
            "Bind All Lying Spirits",
            "Ask Holy Spirit"
    };

    public String[] slide_des = {

            "get messages from God",

            "Confess all sins. It is written that if there’s any unforgiveness, resentment," + " or" +
            " any other wrong attitude that should be dealt with before praying, it must be laid at the foot of Jesus."
            + " Forgive those who have done you wrong, and ask God to forgive you of those who you have done wrong.",

            "When you get a rhema from the Lord, Lying Spirits may interfere and give you thoughts which are far from what the Lord intended to say to you . Remember, even Satan can disguise himself as an Angel of Light." +
                    "Ask the Holy Spirit to bind them in the name of Jesus",

            "Ask the Holy Spirit to please show you your status. A simple prayer like," +
            " “What is your rhema for me this morning/evening Lord? Speak Lord, I am listening”." +
            " After that, you may then choose a rhema"

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        //we use one of the services in the System called "Layout_inflater_service" which is a service
        //that has a purpose of inflating layout resources
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);


        ImageView slideImageView = (ImageView) view.findViewById(R.id.lotslogo3);
        ImageView slideImageView2 = (ImageView) view.findViewById(R.id.headimg);
        RelativeLayout slideBGview  = (RelativeLayout) view.findViewById(R.id.view);
        TextView slideHeading2 =  (TextView) view.findViewById(R.id.headtxt);
        TextView slideDescription2  = (TextView) view.findViewById(R.id.destxt);
        TextView slideHeading =  (TextView) view.findViewById(R.id.welcometxt);
        TextView slideDescription  = (TextView) view.findViewById(R.id.subtxt);
        TextView steps = (TextView) view.findViewById(R.id.steps_des);

        if (position >= 1){

            steps.setVisibility(View.VISIBLE);
            slideImageView.setVisibility(View.GONE);
            slideDescription.setVisibility(View.GONE);
            slideHeading.setVisibility(View.GONE);
            slideHeading2.setVisibility(View.VISIBLE);
            slideDescription2.setVisibility(View.VISIBLE);
            slideImageView2.setImageResource(slide_images[position]);
            slideHeading2.setText(slide_headings[position]);
            slideDescription2.setText(slide_des[position]);
            steps.append(stepstxt[position]);


        }else {
            slideImageView.setImageResource(slide_images[0]);
            slideHeading.setText(slide_headings[0]);
            slideDescription.setText(slide_des[0]);
        }


        Drawable bimage = context.getResources().getDrawable(slide_bimage[position]);
        slideBGview.setBackground(bimage);


        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ((RelativeLayout)object);
    }
}
