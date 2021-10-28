package com.example.fitandfine.Ex_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fitandfine.DatabaseUser;
import com.example.fitandfine.R;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private AppCompatButton  btnLogin;

    DatabaseUser databaseUser= new DatabaseUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);
        if(isLoggedIn){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (AppCompatButton) findViewById(R.id.register);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                String Password = inputPassword.getText().toString();

                Boolean chkemailpass = databaseUser.emailpassword(email, Password);

                if (chkemailpass == true) {
                    SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                    editor.putString("email", email);
                    editor.putString("password", Password);
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_LONG).show();

                }


            }
        });



    }

    public void register(View view) {

        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}
