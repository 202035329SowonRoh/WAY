package com.example.way;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LiveFeedActivity extends AppCompatActivity {

    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedItemList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.live_feed_menu, menu);
        return true;
    }
    //@Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_add_image) {
//            // 이미지 업로드 로직을 처리하는 메서드를 호출합니다.
//            openImageUpload();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void openImageUpload() {
//        // 이미지 업로드 화면으로 이동하는 인텐트를 생성하고 실행합니다.
//        Intent intent = new Intent(this, ImageUploadActivity.class);
//        startActivityForResult(intent, REQUEST_IMAGE_UPLOAD);
//    }
//
//    private static final int REQUEST_IMAGE_UPLOAD = 1;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_UPLOAD && resultCode == RESULT_OK) {
//            // 이미지 업로드가 성공적으로 완료되었을 때 처리할 로직을 구현합니다.
//            // 업로드된 이미지와 피드 정보를 받아와서 RecyclerView에 추가하는 작업 등을 수행합니다.
//        }
//    }
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView 초기화
        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 피드 아이템 목록 생성
        feedItemList = new ArrayList<>();
        feedItemList.add(new FeedItem("피드 1", "피드 1 내용", R.drawable.travel1));
        feedItemList.add(new FeedItem("피드 2", "피드 2 내용", R.drawable.travel2));
        feedItemList.add(new FeedItem("피드 3", "피드 3 내용", R.drawable.travel3));

        // 어댑터 초기화
        feedAdapter = new FeedAdapter(feedItemList);

        // RecyclerView에 어댑터 설정
        feedRecyclerView.setAdapter(feedAdapter);
    }
}
