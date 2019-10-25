package com.example.ahnafmozib.easymeterreading;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Info2 extends AppCompatActivity {

    private Button save_button, search;
    EditText meterIdInput;
    String meter;
    String date;
    String currentReading;
    EditText CurrentDateofInfo;
    EditText CurrentMeterReadingofInfo;

    DatabaseReference databaseMeterId;

    List<MeterIdInput> listofId;

    EditText CustomerNameofInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info2);
        databaseMeterId = FirebaseDatabase.getInstance().getReference("meterReaderInput");

        CustomerNameofInfo = (EditText) findViewById(R.id.CustomerNameofInfo);

        save_button = (Button) findViewById(R.id.buttonSaveofInfo);
        search = (Button) findViewById(R.id.buttonSearchofInfo);
        meterIdInput = (EditText) findViewById(R.id.MeterIdInputofInfo);
        CurrentDateofInfo = (EditText) findViewById(R.id.CurrentDateofInfo);
        CurrentMeterReadingofInfo = (EditText) findViewById(R.id.CurrentMeterReadingofInfo);

        SharedPreferences sharedPref = getSharedPreferences("meterInfo", Context.MODE_PRIVATE);
        meter = sharedPref.getString("meterId","");
        meterIdInput.setText(meter);

        save_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveMeterId();
                Intent intent = new Intent(Info2.this,Upload.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                meter = meterIdInput.getText().toString().trim();
                SharedPreferences sharedPref1 = getSharedPreferences("meterInfo1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPref1.edit();
                editor1.putString("meterId",meter );
                editor1.apply();

                readFromDatabase();
            }
        });
        listofId = new ArrayList<>();
    }

    public void saveMeterId() {
        date = CurrentDateofInfo.getText().toString().trim();
        currentReading = CurrentMeterReadingofInfo.getText().toString().trim();
        MeterIdInput obj = new MeterIdInput(meter,date,currentReading);
        databaseMeterId.child(meter).setValue(obj);
    }



    public void readFromDatabase(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();


        DatabaseReference meterData = db.child("customers").child(meter);
        meterData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                //Toast.makeText(Info2.this, name, Toast.LENGTH_LONG).show();
                CustomerNameofInfo.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
