package com.example.way;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class Event extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        Button plan_btn = (Button) findViewById(R.id.plan_btn);
        plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                startActivity(intent);
                finish();
            }

        });
        Button event_btn = (Button) findViewById(R.id.event_btn);
        event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), Event.class);
//                startActivity(intent);
            }

        });
    }
}
