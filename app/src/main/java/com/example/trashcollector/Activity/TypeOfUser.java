package com.example.trashcollector.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trashcollector.R;

public class TypeOfUser extends AppCompatActivity {
TextView submitnosmart;
TextView smartuser,nonsmartuser;
LinearLayout nonsmartusrlayout,smartuserlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_user);
        submitnosmart=findViewById(R.id.submitnosmart);
        smartuser=findViewById(R.id.smartuser);
        nonsmartuser=findViewById(R.id.nonsmartuser);
        nonsmartusrlayout=findViewById(R.id.nonsmartusrlayout);
        smartuserlayout=findViewById(R.id.smartuserlayout);
        smartuserlayout.setVisibility(View.VISIBLE);
    }
    public void SubmitNonsmart(View view){
        Intent intent=new Intent(getApplicationContext(),NumberConfirmationScreen.class);
        startActivity(intent);
    }
    public void SmartUserClick(View view){
        nonsmartusrlayout.setVisibility(View.GONE);
        smartuserlayout.setVisibility(View.VISIBLE);
    }
    public void NonsmartClick(View view){
        nonsmartusrlayout.setVisibility(View.VISIBLE);
        smartuserlayout.setVisibility(View.GONE);
    }
}