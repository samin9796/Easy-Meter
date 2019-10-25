package com.example.ahnafmozib.easymeterreading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Info1 extends AppCompatActivity {

    private Button button;
    EditText meterIdInput;
    DatabaseReference databaseMeterId;

    List<MeterIdInput> listofId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info1);

        databaseMeterId = FirebaseDatabase.getInstance().getReference("meterReaderInput");

        meterIdInput = (EditText) findViewById(R.id.meterIdInput);
        button = (Button) findViewById(R.id.buttonVerify);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveMeterId();
                Intent intent = new Intent(Info1.this,Info2.class);
                startActivity(intent);
            }
        });
        listofId = new ArrayList<>();
    }
    public void saveMeterId() {
        //String meterId = meterIdInput.getText().toString().trim();
        //MeterIdInput obj = new MeterIdInput(meterId);
        //databaseMeterId.child(meterId).setValue(obj);
    }
}
