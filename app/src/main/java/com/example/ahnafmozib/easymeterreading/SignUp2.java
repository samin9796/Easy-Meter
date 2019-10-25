package com.example.ahnafmozib.easymeterreading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener {

    private Button sign_up, log_in;
    private FirebaseAuth firebaseAuth;
    private EditText emailInput2;
    private EditText passwordInput2;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        firebaseAuth = FirebaseAuth.getInstance();


        emailInput2 = (EditText) findViewById(R.id.emailInput2);
        passwordInput2 = (EditText) findViewById(R.id.passwordInput2);
        progressDialog = new ProgressDialog(this);
        sign_up = (Button) findViewById(R.id.buttonSignUp2);
        log_in = (Button) findViewById(R.id.buttonLogIn2);



        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignUp2.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        //getting email and password from edit texts
        String email = emailInput2.getText().toString().trim();
        String password = passwordInput2.getText().toString().trim();

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
                            Toast.makeText(SignUp2.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUp2.this, BarCodeScanner.class);
                            startActivity(intent);
                        } else {
                            //display some message here
                            Toast.makeText(SignUp2.this, "Login failed", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = emailInput2.getText().toString().trim();
        String password = passwordInput2.getText().toString().trim();

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
                            Toast.makeText(SignUp2.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            Toast.makeText(SignUp2.this, "Registration Error", Toast.LENGTH_LONG).show();
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
