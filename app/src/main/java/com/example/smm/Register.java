package com.example.smm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText etFname, etLname, etAddress, etSignUpEmail,
            etSignUpPassword, etSignUpCPassword,
            etPhone;
    Button btnSignUpCancel, btnSignUp;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSignUpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences.Editor spEditor = getSharedPreferences("myFile", MODE_PRIVATE).edit();
//                spEditor.putString("Username", etFname.getText().toString()+" "+etLname.getText().toString());
//                spEditor.putString("Email", etSignUpEmail.getText().toString());
//                spEditor.putString("Password", etSignUpPassword.getText().toString());
//                spEditor.apply();

                HashMap<String, Object> data = new HashMap<>();
                data.put("username", etFname.getText().toString()+" "+etLname.getText().toString());
                data.put("useremail",etSignUpEmail.getText().toString());
                data.put("password", etSignUpPassword.getText().toString());
                data.put("address",etAddress.getText().toString());
                data.put("phone",etPhone.getText().toString());

               // FirebaseApp.initializeApp();
                FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(etPhone.getText().toString())
                        .setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent zl = new Intent(Register.this, MainActivity.class);
                                startActivity(zl);
                                clear();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void clear()
    {
        etFname.setText("");
        etLname.setText("");
        etAddress.setText("");
        etSignUpEmail.setText("");
        etSignUpPassword.setText("");
                etSignUpCPassword.setText("");
        etPhone.setText("");
                btnSignUp.setText("");
        btnSignUpCancel.setText("");
                tvLogin.setText("");
    }

    public void init()
    {
        etFname           = findViewById(R.id.etFname);
        etLname           = findViewById(R.id.etLname);
        etAddress         = findViewById(R.id.etAddress);
        etSignUpEmail     = findViewById(R.id.etSignUpEmail);
        etSignUpPassword  = findViewById(R.id.etSignupPassword);
        etSignUpCPassword = findViewById(R.id.etSignUpCPassword);
        etPhone           = findViewById(R.id.etPhone);
        btnSignUp         = findViewById(R.id.btnSignup);
        btnSignUpCancel   = findViewById(R.id.btnSignUpCancel);
        tvLogin           = findViewById(R.id.tvLogin);

    }
}