package com.aplication.lots;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    Button tutorialbtn, genderbtn;
    Dialog selGender;
    ToggleButton malebtn, fembtn;
    SwitchCompat selMode;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tutorialbtn = (Button) view.findViewById(R.id.btninfo);
        genderbtn = (Button) view.findViewById(R.id.btngender);

        selGender = new Dialog(requireContext());






        tutorialbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireContext(), OnBoarding.class);

                //Bundle B = ActivityOptions.makeCustomAnimation(requireActivity().toBundle())

                startActivity(intent);

            }
        });

        genderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                genderSelection();
            }
        });




    }


    private void genderSelection(){

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

            selGender.show();


            malebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                    malebtn.setChecked(isChecked);

                    if(isChecked){

                        new AlertDialog.Builder(requireContext())
                                .setTitle("GENDER SELECTION")
                                .setMessage("Confirm Gender: MALE?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences pref = requireContext().getSharedPreferences("pref", requireContext().MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("gender", "MALE");
                                        editor.apply();

                                        String gender_selected = pref.getString("gender", "");

                                        Toast.makeText(getContext(), "Gender selected:" + gender_selected, Toast.LENGTH_LONG).show();

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

                        new AlertDialog.Builder(requireContext())
                                .setTitle("GENDER SELECTION")
                                .setMessage("Confirm Gender: FEMALE?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences pref = requireContext().getSharedPreferences("pref", requireContext().MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("gender", "FEMALE");
                                        editor.apply();

                                        String gender_selected = pref.getString("gender", "");

                                        Toast.makeText(getContext(), "Gender selected:" + gender_selected, Toast.LENGTH_LONG).show();

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
