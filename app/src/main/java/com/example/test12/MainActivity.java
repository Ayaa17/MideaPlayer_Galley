package com.example.test12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {


    Button btn_a,btn_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_a = findViewById(R.id.button2);
        btn_b = findViewById(R.id.button3);
        Toast.makeText(MainActivity.this,"WELECOME",Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_LONG).show();

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(MainActivity.this, PActivity.class);
                startActivity(it);
            }
        });
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(MainActivity.this, VActivity.class);
                startActivity(it);
            }
        });

//        new Handler().postDelayed( new Runnable() {
//            public void run() {
//                Intent it =new Intent();
//                it.setClass(MainActivity.this, PActivity.class);
//                startActivity(it);
//
//            }},1000);
    }
}