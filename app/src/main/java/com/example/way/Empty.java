package com.example.way;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Empty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this,Marker.class);
        startActivity(intent);
        finish();
    }
}