package com.example.ryanchan.handoffhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Kody on 11/20/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button SignIn;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editTextPassword);

        SignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        SignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    private void Login(){
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Log.d("TESTING", "Registration SUCCESS");
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
    }


    @Override
    public void onClick(View view){
        if (view == SignIn){
            Log.d("TESTING", "LOGIN SUCCESS");
            Login();
        }

        if (view == textViewSignUp){
            finish();
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

}
