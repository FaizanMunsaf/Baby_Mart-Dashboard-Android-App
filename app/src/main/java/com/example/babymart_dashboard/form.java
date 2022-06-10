package com.example.babymart_dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class form extends AppCompatActivity {
    private EditText et_email,et_pass;
    //private TextView forgotpass;
    private Button btn_login,forgotpass;
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        et_email = findViewById(R.id.email);
        et_pass = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }




        btn_login.setOnClickListener(view -> {
            loginuser();
        });

    }
    private void loginuser() {
        String email = et_email.getText().toString();
        String password = et_pass.getText().toString();



        if (et_email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter your Username", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
        } else if (et_pass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter your Username", Toast.LENGTH_SHORT).show();
            et_pass.requestFocus();
        } else {
                        Toast.makeText(form.this,"Login SuccessFully",Toast.LENGTH_SHORT).show();

                        Usersession usersession= new Usersession(12,email);
                        SessionManagement sessionManagement = new SessionManagement(form.this);
                        sessionManagement.saveSesion(usersession);

                        Movetoactivity();

                }
    }
    public  void onStart(){
        super.onStart();
        //user is loged in or not?
        checksession();

    }
    private  void checksession(){
        SessionManagement sessionManagement = new SessionManagement(form.this);
        int Userid = sessionManagement.getSession();

        if(Userid != -1){
            Movetoactivity();
        }else{

        }
    }
    public void Movetoactivity(){
        Intent i = new Intent(form.this,dashboard.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}