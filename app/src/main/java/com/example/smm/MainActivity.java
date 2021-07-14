package com.example.smm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnCancel;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();

                if(email.isEmpty() || pass.isEmpty())
                {
//                    etEmail.setError("Invalid Email");
//                    etPassword.setError("Invalid Password");

                    Toast.makeText(MainActivity.this, "Email or password is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    SharedPreferences spref = getSharedPreferences("myFile", MODE_PRIVATE);
                    String userEmail = spref.getString("Email", "");
                    String userPassword = spref.getString("Password", "");

                    if(email.equals(userEmail) && pass.equals(userPassword))
                    {
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                        clear();
                        finish();
                    }
                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });


    }

    public void clear()
    {
        etEmail.setText("");
        etPassword.setText("");
    }

    public void init()
    {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnCancel = findViewById(R.id.btnCancel);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }




}