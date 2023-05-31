package com.example.way;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LiveFeedActivity extends AppCompatActivity {

    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedItemList;

    private static final int REQUEST_IMAGE_UPLOAD = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.live_feed_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_image) {
            openImageUpload();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openImageUpload() {
        Intent intent = new Intent(this, ImageUploadActivity.class);
        startActivityForResult(intent, REQUEST_IMAGE_UPLOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_UPLOAD && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                Uri imageUri = Uri.parse(data.getStringExtra("imageUri"));

                FeedItem newFeedItem = new FeedItem(title, content, imageUri);

                feedItemList.add(newFeedItem);
                feedAdapter.notifyItemInserted(feedItemList.size() - 1);
                feedRecyclerView.scrollToPosition(feedItemList.size() - 1);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedItemList = new ArrayList<>();
        feedItemList.add(new FeedItem("피드 1", "피드 1 내용", Uri.parse("android.resource://com.example.way/drawable/travel1")));
        feedItemList.add(new FeedItem("피드 2", "피드 2 내용", Uri.parse("android.resource://com.example.way/drawable/travel2")));
        feedItemList.add(new FeedItem("피드 3", "피드 3 내용", Uri.parse("android.resource://com.example.way/drawable/travel3")));

        feedAdapter = new FeedAdapter(feedItemList);

        feedRecyclerView.setAdapter(feedAdapter);
    }
}
