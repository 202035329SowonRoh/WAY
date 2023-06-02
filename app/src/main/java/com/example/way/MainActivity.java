package com.example.way;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.opengl.GLES30;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    TextView challenge;

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
                startActivityForResult(chlngIntent,100);
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

        // Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MarkerFragment markerFragment = new MarkerFragment();
        fragmentTransaction.replace(R.id.fragmentFrame, markerFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 하단 메뉴 레이아웃 가져오기
        View bottomSheet = findViewById(R.id.menu_bottomsheet);
        challenge = bottomSheet.findViewById(R.id.txt_challenge);

        if (requestCode == 100 & resultCode == RESULT_OK && data != null) {
            int stringId = data.getIntExtra("selectedButton", R.string.chlng1);

            if (stringId != 0xffffffff) {
                String selectedButtonText = getString(stringId);

                // 선택한 버튼의 텍스트를 TextView에 설정
                challenge.setText(selectedButtonText);
            }
        }
    }
}