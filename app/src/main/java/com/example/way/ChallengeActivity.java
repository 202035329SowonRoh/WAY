package com.example.way;

import static androidx.core.app.NavUtils.navigateUpFromSameTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.w3c.dom.Text;

import java.util.concurrent.atomic.AtomicReference;

public class ChallengeActivity extends AppCompatActivity {

    int selectedButtonId = -1;
    int selectedButtonText = -1;

    int firstStep = 0;
    int enableAsia = 0;
    int enableAmerica = 0;
    int enableAustralia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        View bottomSheet = findViewById(R.id.menu_challenge_bottomsheet);
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        TextView titleText = bottomSheet.findViewById(R.id.chlng_title);
        TextView detailText = bottomSheet.findViewById(R.id.chlng_detail);

        Button cancel = findViewById(R.id.cancel_chlng);
        Button save = findViewById(R.id.save_chlng);

        Button btn_chlng1 = findViewById(R.id.chlng1);
        Button btn_chlng2 = findViewById(R.id.chlng2);
        Button btn_chlng3 = findViewById(R.id.chlng3);
        Button btn_chlng4 = findViewById(R.id.chlng4);
        Button btn_chlng5 = findViewById(R.id.chlng5);
        Button btn_chlng6 = findViewById(R.id.chlng6);

        // challenge 조건 확인 및 enabled 설정 변경
        Intent intent = getIntent();
        firstStep = intent.getIntExtra("firstStep",firstStep);
        enableAsia = intent.getIntExtra("enableAsia",enableAsia);
        enableAmerica = intent.getIntExtra("enableAmerica",enableAmerica);
        enableAustralia = intent.getIntExtra("enableAustralia",enableAustralia);

        if (firstStep != 0) {
            //Toast.makeText(ChallengeActivity.this,getResources().getString(R.string.chlng2) + " is unlocked", Toast.LENGTH_SHORT).show();
            btn_chlng2.setEnabled(true);
        }
        if(enableAsia != 0) {
            //Toast.makeText(ChallengeActivity.this,getResources().getString(R.string.chlng3) + " is unlocked", Toast.LENGTH_SHORT).show();
            btn_chlng3.setEnabled(true);
        }
        if(enableAmerica != 0) {
            //Toast.makeText(ChallengeActivity.this,getResources().getString(R.string.chlng5) + " is unlocked", Toast.LENGTH_SHORT).show();
            btn_chlng5.setEnabled(true);
        }
        if(enableAustralia != 0) {
            //Toast.makeText(ChallengeActivity.this,getResources().getString(R.string.chlng6) + " is unlocked", Toast.LENGTH_SHORT).show();
            btn_chlng6.setEnabled(true);
        }


        btn_chlng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng1;
                titleText.setText(R.string.chlng1);
                detailText.setText(R.string.chlng1_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_chlng2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng2;
                titleText.setText(R.string.chlng2);
                detailText.setText(R.string.chlng2_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_chlng3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng3;
                titleText.setText(R.string.chlng3);
                detailText.setText(R.string.chlng3_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_chlng4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng4;
                titleText.setText(R.string.chlng4);
                detailText.setText(R.string.chlng4_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_chlng5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng5;
                titleText.setText(R.string.chlng5);
                detailText.setText(R.string.chlng5_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_chlng6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedButtonId = R.id.chlng6;
                titleText.setText(R.string.chlng6);
                detailText.setText(R.string.chlng6_detail);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {

                switch (selectedButtonId) {
                    case R.id.chlng1:
                        selectedButtonText = R.string.chlng1;
                        break;
                    case R.id.chlng2:
                        selectedButtonText = R.string.chlng2;
                        break;
                    case R.id.chlng3:
                        selectedButtonText = R.string.chlng3;
                        break;
                    case R.id.chlng4:
                        selectedButtonText = R.string.chlng4;
                        break;
                    case R.id.chlng5:
                        selectedButtonText = R.string.chlng5;
                        break;
                    case R.id.chlng6:
                        selectedButtonText = R.string.chlng6;
                        break;
                    default:
                        break;
                }

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.i("test", Integer.toString(selectedButtonText));
        Log.i("test", Integer.toString(R.string.chlng2));

        Intent intent = new Intent();
        intent.putExtra("selectedButton",selectedButtonText);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

//    public void buttonEnabled() {
//
//        MarkerFragment markerFragment = new MarkerFragment();
//
//        Button btn_chlng2 = findViewById(R.id.chlng2);
//        Button btn_chlng3 = findViewById(R.id.chlng3);
//        Button btn_chlng5 = findViewById(R.id.chlng5);
//        Button btn_chlng6 = findViewById(R.id.chlng6);
//
//        int count = markerFragment.countNumOfCountry();
//
//        Log.i("count",String.valueOf(count));
//
//        int[] countries = {markerFragment.vi_ch, markerFragment.vi_j, markerFragment.vi_k, markerFragment.vi_c, markerFragment.vi_a, markerFragment.vi_m};
//
//        Log.i("vi_ch",String.valueOf(countries[0]));
//
//        // first step
//        if (count != 0) {
//            btn_chlng2.setEnabled(true);
//        }
//
//        // 아시아
//        if(countries[0] > 0 || countries[1] > 0 || countries[2] > 0) {
//            btn_chlng3.setEnabled(true);
//        }
//
//        // 아메리카
//        if(countries[3] > 0 || countries[5] > 0) {
//            btn_chlng5.setEnabled(true);
//        }
//
//        // 오스트리아
//        if(countries[4] > 0) {
//            btn_chlng6.setEnabled(true);
//        }
//
//    }
}