package com.example.ahnafmozib.customerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    private Button button,next_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        button = (Button) findViewById(R.id.buttonSignUp);
        next_button = (Button) findViewById(R.id.buttonNextOfSignIn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(SignIn.this,signUp.class);
                startActivity(intent);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(SignIn.this,Home.class);
                startActivity(intent);
            }
        });
    }

}
