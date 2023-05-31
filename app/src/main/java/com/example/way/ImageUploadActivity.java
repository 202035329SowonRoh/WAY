package com.example.way;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageUploadActivity extends AppCompatActivity {

    private TextView selectPhotoTextView;
    private ImageView selectedImageView;
    private Button uploadButton;
    private EditText titleEditText;
    private EditText contentEditText;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        selectPhotoTextView = findViewById(R.id.selectPhotoTextView);
        selectedImageView = findViewById(R.id.selectedImageView);
        uploadButton = findViewById(R.id.uploadButton);
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);

        selectPhotoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_CODE);
    }




    private void uploadImage() {
        if (selectedImageView.getDrawable() != null) {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            Uri imageUri = getImageUri(selectedImageView);

            if (imageUri != null) {  // 이미지 URI가 null인 경우 처리
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("content", content);
                resultIntent.putExtra("imageUri", imageUri.toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                // 이미지 URI가 null일 때 오류 처리
                Toast.makeText(this, "Failed to upload image. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        selectedImageView.setImageBitmap(bitmap);
                        selectPhotoTextView.setVisibility(View.GONE);
                        selectedImageView.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    private Uri getImageUri(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }

        // 이미지를 저장할 임시 파일 생성
        File imageFile = new File(getCacheDir(), "temp_image.jpg");
        try (OutputStream outputStream = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FileProvider를 사용하여 콘텐츠 URI 생성
        Uri imageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", imageFile);

        return imageUri;
    }
}
