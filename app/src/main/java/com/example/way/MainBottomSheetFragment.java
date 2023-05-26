package com.example.way;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.way.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainBottomSheetFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            // BottomSheet 슬라이드 변화 감지
            if (slideOffset == 1) {

                // BottomSheet가 전부 열렸을 때
                // 버튼 클릭 이벤트 처리

                View bottomSheetView = getLayoutInflater().inflate(R.layout.menu_bottomsheet, null);

                // BottomSheet 안에 있는 버튼을 찾아옵니다.
                ImageButton historyButton = bottomSheetView.findViewById(R.id.btn_history);
                ImageButton livefeedButton = bottomSheetView.findViewById(R.id.btn_livefeed);
                ImageButton planButton = bottomSheetView.findViewById(R.id.btn_plan);

                historyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 버튼 1 클릭 이벤트 처리

                    }
                });

                livefeedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 버튼 2 클릭 이벤트 처리
                    }
                });

                planButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 버튼 3 클릭 이벤트 처리
                    }
                });
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.menu_bottomsheet, container, false);
    }

}
