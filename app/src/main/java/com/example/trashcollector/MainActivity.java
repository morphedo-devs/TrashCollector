package com.example.trashcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.trashcollector.Activity.WhatWouldYouLike;
import com.example.trashcollector.Utils.LocaleHelper;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
LinearLayout english,hindi;
    private String mLanguageCode = "hi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        english=findViewById(R.id.english);
        hindi=findViewById(R.id.hindi);
    }
    public void SelectHindi(View view){
        LocaleHelper.setLocale(this,"hi");
        Intent intent=new Intent(getApplicationContext(), WhatWouldYouLike.class);
        startActivity(intent);
    }

    public  void English(View view){
        LocaleHelper.setLocale(this,"");
        Intent intent=new Intent(getApplicationContext(), WhatWouldYouLike.class);
        startActivity(intent);
    }
}