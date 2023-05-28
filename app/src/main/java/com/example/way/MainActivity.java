package com.example.way;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLES30;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_challenge = findViewById(R.id.btn_challenge);

        // Challenge Activity 로 이동
        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chlngIntent = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(chlngIntent);
            }
        });

        // 하단 메뉴 레이아웃 가져오기
        View bottomSheet = findViewById(R.id.menu_bottomsheet);

// BottomSheetBehavior 설정
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

// BottomSheet 상태 변경 시 이벤트 리스너 설정
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    // 버튼 클릭시 History 액티비티로 전환
                    ImageButton historyButton = bottomSheet.findViewById(R.id.button_history);
                    historyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent historyIntent = new Intent(MainActivity.this, TravelHistoryActivity.class);
                            startActivity(historyIntent);
                        }
                    });

                    ImageButton livefeedButton = bottomSheet.findViewById(R.id.button_livefeed);
                    livefeedButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 버튼 클릭시 LiveFeed 액티비티로 전환
                            Intent livefeedIntent = new Intent(MainActivity.this, LiveFeedActivity.class);
                            startActivity(livefeedIntent);
                        }
                    });

                    ImageButton planButton = bottomSheet.findViewById(R.id.button_plan);
                    planButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 버튼 클릭시 Plan 액티비티로 전환
                            Intent planIntent = new Intent(MainActivity.this, PlanActivity.class);
                            startActivity(planIntent);
                        }
                    });
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) { }
        });

    }

}