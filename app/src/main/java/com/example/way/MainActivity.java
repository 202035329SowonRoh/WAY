package com.example.way;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLES30;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_challenge = findViewById(R.id.btn_challenge);

        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chlngIntent = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(chlngIntent);
                finish();
            }
        });
    }
}