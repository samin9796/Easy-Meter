package com.example.ahnafmozib.easymeterreading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Exit extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        Button btn1 = (Button) findViewById(R.id.ExitButton);

        btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Exit.this,MeterlogoActivity.class);
                startActivity(intent);
            }
        });
    }
    }

