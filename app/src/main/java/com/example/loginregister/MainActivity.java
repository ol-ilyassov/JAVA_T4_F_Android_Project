package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

public class MainActivity extends AppCompatActivity {

    TextView tv_fname, tv_lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_fname = findViewById(R.id.tv_fname);
        tv_lname = findViewById(R.id.tv_lname);

        String email;
        Intent in= getIntent();
        email = in.getStringExtra("email");

        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();


        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("https://192.168.100.3/LoginRegister/readTest.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
            }
        });
    }
}