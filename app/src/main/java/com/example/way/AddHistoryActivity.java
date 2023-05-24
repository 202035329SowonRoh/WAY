package com.example.way;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.hbb20.CountryCodePicker;

import io.realm.Realm;


public class AddHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        // id를 받아와서 변수에 해당 컴포넌트들을 할당
        CountryCodePicker countryInput = findViewById(R.id.country);
        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView flag = (ImageView) countryInput.getImageViewFlag();
                String country = countryInput.getSelectedCountryName();
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                History history = realm.createObject(History.class);
//                history.setFlag(flag);
//                history.setCountry(country.toString());
                history.setTitle(title);
                history.setDescription(description);
                history.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "History saved", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), country, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}