package com.example.ahnafmozib.easymeterreading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Camera extends AppCompatActivity {

    private Button button,retry_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button = (Button) findViewById(R.id.buttonProceed);
        retry_button = (Button) findViewById(R.id.buttonRetry);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Camera.this,Exit.class);
                startActivity(intent);
            }
        });

        retry_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Camera.this,Camera.class);
                startActivity(intent);
            }
        });
    }
}
