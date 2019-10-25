package com.example.ahnafmozib.easymeterreading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private Button button, next_button;
    private FirebaseAuth firebaseAuth;
    private EditText emailInput;
    private EditText passwordInput;
    private ProgressDialog progressDialog;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();


        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        progressDialog = new ProgressDialog(this);
        button = (Button) findViewById(R.id.buttonSignUp);
        next_button = (Button) findViewById(R.id.next);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        //getting email and password from edit texts
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        //checking if email and passwords are empty
        /*if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        */
        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Logging in. Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(signup.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(signup.this, Info1.class);
                            startActivity(intent);
                        } else {
                            //display some message here
                            Toast.makeText(signup.this, "Login failed", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        //checking if email and passwords are empty
        /*if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        */

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(signup.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            Toast.makeText(signup.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }


    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }

}
