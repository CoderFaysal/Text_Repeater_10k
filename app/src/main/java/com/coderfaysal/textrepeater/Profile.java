package com.coderfaysal.textrepeater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.BuildConfig;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {


    LinearLayout facebook, whatsapp;
    ImageView back_to_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        facebook = findViewById(R.id.facebook);
        whatsapp = findViewById(R.id.whatsapp);


        facebook.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/coderfaysal")));
        });

        whatsapp.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+8801833732555")));
        });



        back_to_activity =findViewById(R.id.back_to_activity);
        back_to_activity.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });



    }
}