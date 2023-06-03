package com.example.way;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.opengl.GLES30;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {
    private FrameLayout container;

    int firstStep = 0;
    int enableAsia = 0;
    int enableAmerica = 0;
    int enableAustralia = 0;

    int countNumOfCountry;

    TextView challenge;
    TextView numOfCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.fragmentFrame);

        // countNumOfCountry
        

        // Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MarkerFragment markerFragment = new MarkerFragment(MainActivity.this);
        fragmentTransaction.add(R.id.fragmentFrame, markerFragment);
        fragmentTransaction.commit();

        ImageButton btn_challenge = findViewById(R.id.btn_challenge);
        // Challenge Activity 로 이동
        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chlngIntent = new Intent(MainActivity.this, ChallengeActivity.class);
                chlngIntent.putExtra("firstStep",firstStep);
                chlngIntent.putExtra("enableAsia",enableAsia);
                chlngIntent.putExtra("enableAmerica",enableAmerica);
                chlngIntent.putExtra("enableAustralia",enableAustralia);

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

                // BottomSheet 이 위로 올라왔을 때
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                    //BottomSheet이 아래로 내려갔을 때 Fragment Globe로 변환
                    GlobeFragment globeFragment = new GlobeFragment();
                    replaceFragment(globeFragment);

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
                    
                } else {
                    MarkerFragment markerFragment = new MarkerFragment(MainActivity.this);
                    replaceFragment(markerFragment);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) { }
        });

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

    // Fragment 교체를 수행하는 메소드
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentFrame, fragment);
        fragmentTransaction.commit();
    }

    public void processDataFromFragment(int data) {
        // 하단 메뉴 레이아웃 가져오기
        View bottomSheet = findViewById(R.id.menu_bottomsheet);
        numOfCountry = bottomSheet.findViewById(R.id.txt_numOfCountry);

        numOfCountry.setText(String.valueOf(data));
    }

    // MarkerFragment에서 데이터의 변경을 수신하는 메서드입니다.
    public void setMarkerData(int data1, int data2, int data3, int data4) {
        firstStep = data1;
        enableAsia = data2;
        enableAmerica = data3;
        enableAustralia = data4;
    }

}