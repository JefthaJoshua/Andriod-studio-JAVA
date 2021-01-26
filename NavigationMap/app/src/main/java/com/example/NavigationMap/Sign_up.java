package com.example.NavigationMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Sign_up extends AppCompatActivity {
    EditText F_FN, F_SUR, F_Email, F_Password;
    Button F_Btn_Reg,Other;
    ProgressBar P_bar;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        F_FN = findViewById(R.id.Fname);
        F_SUR = findViewById(R.id.Surname);
        F_Email = findViewById(R.id.Email);
        F_Password = findViewById(R.id.Password);
        F_Btn_Reg = findViewById(R.id.Btn_register);
        Other = (Button) findViewById(R.id.btn_bl);


        P_bar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        F_Btn_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = F_Email.getText().toString().trim();
                String FirstN = F_FN.getText().toString().trim();
                String Surname = F_SUR.getText().toString().trim();
                String password = F_Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    F_Email.setError("Plz insert Correct Email");
                    return;
                }
                if (TextUtils.isEmpty(FirstN)) {
                    F_FN.setError("Plz insert Correct name");
                    return;
                }
                if (TextUtils.isEmpty(Surname)) {
                    F_SUR.setError("Plz insert Correct Surname");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    F_Password.setError("Plz insert Correct Password");
                    return;
                }

                if (password.length() < 6) {
                    F_Password.setError("Password Must be more than 6 Characters my FRIEND");
                    return;
                }

                P_bar.setVisibility(View.VISIBLE);


                User user = new User();
                user.email = email;
                user.FirstN = FirstN;
                user.Surname = Surname;
                user.password = password;



                // this is used to register the user in the firebase DB
                Toast.makeText(Sign_up.this, "User was happily Created. Enjoy!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
        Other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}