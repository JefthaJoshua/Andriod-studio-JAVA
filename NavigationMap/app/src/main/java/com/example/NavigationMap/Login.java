package com.example.NavigationMap;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
        EditText mail, pass;
        Button Btn_si,Other;
        TextView F_Btn_L;
        ProgressBar Pbaar;
        FirebaseAuth mAuth;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mail = findViewById(R.id.fld_email);
            pass = findViewById(R.id.fld_password);
            Pbaar = findViewById(R.id.progressBar2);
            Btn_si = findViewById(R.id.Btn_sign);
            Other = (Button) findViewById(R.id.RigE);

            Other.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    openActivityOther();}
            });

            Btn_si.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = mail.getText().toString().trim();
                    String password = pass.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        mail.setError("Plz insert Correct Email");
                        return;
                    }
                    if (TextUtils.isEmpty(email)) {
                        pass.setError("Plz insert Correct Password");
                        return;
                    }
                    if (password.length() < 6) {
                        pass.setError("Password Must be more than 6 Characters my FRIEND");
                        return;
                    }
                    Pbaar.setVisibility(View.VISIBLE);

                    //authenticate the user
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                            else{
                                Toast.makeText(com.example.NavigationMap.Login.this, "Oh No We have a problem my Friend " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                Pbaar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });
        }
    private void openActivityOther() {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }
        }