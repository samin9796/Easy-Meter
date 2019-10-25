package com.example.ahnafmozib.customerapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class signUp extends AppCompatActivity {

    private Button button;
    EditText NameShowofSignUp;
    EditText AddressInputofSignUp;
    EditText MeterIDShowofSignUp;
    EditText SamityNoShowOfSignUp;
    EditText EmailShowofSignUp;
    EditText MobileNoShowofSignUp;
    EditText PasswordShowofSignUp;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseCustomer;

    List<InfoOfSignUp> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseCustomer = FirebaseDatabase.getInstance().getReference("customers");

        NameShowofSignUp = (EditText) findViewById(R.id.NameShowofSignUp);
        AddressInputofSignUp = (EditText) findViewById(R.id.AddressInputofSignUp);
        MeterIDShowofSignUp = (EditText) findViewById(R.id.MeterIDShowofSignUp);
        SamityNoShowOfSignUp = (EditText) findViewById(R.id.SamityNoShowOfSignUp);
        EmailShowofSignUp = (EditText) findViewById(R.id.EmailShowofSignUp);
        MobileNoShowofSignUp = (EditText) findViewById(R.id.MobileNoShowofSignUp);
        PasswordShowofSignUp = (EditText) findViewById(R.id.PasswordShowofSignUp);


        button = (Button) findViewById(R.id.SignUpButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                registerUser();
                addCustomer();
                Intent intent = new Intent(signUp.this,SignIn.class);
                startActivity(intent);
            }
        });

        customers = new ArrayList<>();
    }


    private void addCustomer() {
        //getting the values to save
        String name = NameShowofSignUp.getText().toString().trim();
        String address = AddressInputofSignUp.getText().toString().trim();
        String meterId = MeterIDShowofSignUp.getText().toString().trim();
        String samityNo = SamityNoShowOfSignUp.getText().toString().trim();
        String email = EmailShowofSignUp.getText().toString().trim();
        String mobileNo = MobileNoShowofSignUp.getText().toString().trim();


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseCustomer.push().getKey();

            //creating an Artist Object
            InfoOfSignUp customer = new InfoOfSignUp(name, address, meterId,samityNo,email,mobileNo);

            //Saving the Artist
            databaseCustomer.child(meterId).setValue(customer);

            //setting edittext to blank again
            NameShowofSignUp.setText("");
            AddressInputofSignUp.setText("");
            MeterIDShowofSignUp.setText("");
            SamityNoShowOfSignUp.setText("");
            EmailShowofSignUp.setText("");
            MobileNoShowofSignUp.setText("");

            //displaying a success toast
            Toast.makeText(this, "Information added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter your information", Toast.LENGTH_LONG).show();
        }
    }



    String email, password;

    private void registerUser(){

        //getting email and password from edit texts
        email = EmailShowofSignUp.getText().toString().trim();
        password  = PasswordShowofSignUp.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }



        //if the email and password are not empty
        //displaying a progress dialog

        //progressDialog.setMessage("Registering Please Wait...");
        //progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(signUp.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(signUp.this,"Registration Error",Toast.LENGTH_LONG).show();

                            //Toast.makeText(CreateAccount.this, ""+email+" "+password, Toast.LENGTH_LONG).show();

                        }
                        //progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("tada", e.getMessage());
            }
        });

    }
}
