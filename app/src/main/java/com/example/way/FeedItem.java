package com.example.way;

import android.net.Uri;

public class FeedItem {
    private String title;
    private String content;
    private Uri imageUri;

    public FeedItem(String title, String content, Uri imageUri) {
        this.title = title;
        this.content = content;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}


