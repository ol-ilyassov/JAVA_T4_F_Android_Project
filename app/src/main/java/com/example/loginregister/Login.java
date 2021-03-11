package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextPassword, textInputEditTextEmail;
    Button buttonLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progress);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if (!email.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.100.3/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //Log.i("PutData", result);
                                    if (result.equals("Login Success")) {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("email", email);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}