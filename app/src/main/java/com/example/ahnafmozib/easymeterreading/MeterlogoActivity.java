package com.example.ahnafmozib.easymeterreading;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MeterlogoActivity extends AppCompatActivity {

    private Button button;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.meterlogo);
        auth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.MeterReaderApp);
        button.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(MeterlogoActivity.this,SignUp2.class);
            startActivity(intent);
        }
        });
    }
}
