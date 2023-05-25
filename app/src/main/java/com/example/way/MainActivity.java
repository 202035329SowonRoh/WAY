package com.example.way;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLES30;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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

//        View bottomSheetView = getLayoutInflater().inflate(R.layout.menu_bottomsheet, null);
//
//        // BottomSheet 안에 있는 버튼을 찾아옵니다.
//        ImageButton historyButton = bottomSheetView.findViewById(R.id.btn_history);
//        ImageButton livefeedButton = bottomSheetView.findViewById(R.id.btn_livefeed);
//        ImageButton planButton = bottomSheetView.findViewById(R.id.btn_plan);
//
//        // BottomSheet 안의 버튼 클릭 이벤트 처리
//        historyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // go to history activity
////                Intent historyIntent = new Intent(MainActivity.this, TravelHistoryActivity.class);
////                startActivity(historyIntent);
////                finish();
//
//                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

}