package com.example.fitandfine.Ex_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitandfine.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();
    }

    public void signin(View view) {
        Intent intent=new Intent(Welcome.this,Login.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent=new Intent(Welcome.this,Register.class);
        startActivity(intent);
    }

}
