package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {

    TextView tv_fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_fname = findViewById(R.id.tv_fname);

        String email;
        Intent in= getIntent();
        email = in.getStringExtra("email");

        //Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();


        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "email";
                String[] data = new String[1];
                data[0] = email;
                PutData putData = new PutData("http://192.168.100.3/LoginRegister/select.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        //Log.i("PutData", result);
                        tv_fname.setText(result);
                        //Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}