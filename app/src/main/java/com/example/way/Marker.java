package com.example.way;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import android.os.Bundle;


public class Marker extends AppCompatActivity {


    SharedPreferences pref;//프리퍼런스
    SharedPreferences.Editor editor;//에디터
    int vi_ch; //여행지 정보를 저장할 변수
    int vi_j;
    int vi_k;
    int vi_c;
    int vi_a;
    int vi_m;

    TextView textView;
    TextView textView3;
    String[] compare={"china","japan","korea","australia","chile","mexico"};
    int[] count={0,0,0,0,0,0};
    int[] visit={0,0,0,0,0,0};//number of visits by country
    String con_now="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        vi_ch = pref.getInt("vi_ch", 0);
        vi_j = pref.getInt("vi_j", 0);
        vi_k = pref.getInt("vi_k", 0);
        vi_c = pref.getInt("vi_c", 0);
        vi_a = pref.getInt("vi_a", 0);
        vi_m = pref.getInt("vi_m", 0);
        setContentView(R.layout.marker_map);

        Button apply = findViewById(R.id.apply_btn);
        ImageView t_map = findViewById(R.id.map);
        //textView = findViewById(R.id.textView);
        Button add_btn = findViewById(R.id.add_num);
        Button min_btn = findViewById(R.id.min_num);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(con_now +"");

        VectorChildFinder vector = new VectorChildFinder(this, R.drawable.ic_blank_map,t_map);

        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("중국");
        VectorDrawableCompat.VFullPath path2 = vector.findPathByName("일본");
        VectorDrawableCompat.VFullPath path3 = vector.findPathByName("한국");
        VectorDrawableCompat.VFullPath path4 = vector.findPathByName("호주");
        VectorDrawableCompat.VFullPath path5 = vector.findPathByName("칠레");
        VectorDrawableCompat.VFullPath path6 = vector.findPathByName("멕시코");



        Spinner spinnerMenu = findViewById(R.id.country_spinner);
        final String[] countries = getResources().getStringArray(R.array.country);//get list form country

        ArrayAdapter menuAdapter = ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_spinner_item);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);//use dropdown
        spinnerMenu.setAdapter(menuAdapter);
        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//event when select spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textView.setText(countries[position]);//get string from array
                con_now=spinnerMenu.getSelectedItem().toString();
                Toast.makeText(Marker.this , con_now, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Empty.class);

                startActivity(intent);

            }
        });


        add_btn.setOnClickListener(new View.OnClickListener() {//add +1 to int array visit
            @Override
            public void onClick(View view) {
                Toast.makeText(Marker.this, con_now, Toast.LENGTH_SHORT).show();

                if(con_now.equals(compare[0])){

                    vi_ch++;
                    textView3.setText(vi_ch+"");//각각의 카운터의 가감을 보여줌
                    editor.putInt("vi_ch",vi_ch);
                    editor.apply();


                }else if(con_now.equals(compare[1])){

                    vi_j++;
                    textView3.setText(vi_j+"");
                    editor.putInt("vi_j",vi_j);
                    editor.apply();


                }else if(con_now.equals(compare[2])){

                    vi_k++;
                    textView3.setText(vi_k+"");
                    editor.putInt("vi_k",vi_k);
                    editor.apply();


                }else if(con_now.equals(compare[3])){

                    vi_a++;
                    textView3.setText(vi_a+"");
                    editor.putInt("vi_a",vi_a);
                    editor.apply();


                }else if(con_now.equals(compare[4])){

                    vi_c++;
                    textView3.setText(vi_c+"");
                    editor.putInt("vi_c",vi_c);
                    editor.apply();


                }else if(con_now.equals(compare[5])){

                    vi_m++;
                    textView3.setText(vi_m+"");
                    editor.putInt("vi_m",vi_m);
                    editor.apply();

                }
            }
        });
        min_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(con_now.equals(compare[0])){

                    vi_ch--;
                    textView3.setText(vi_ch+"");
                    editor.putInt("vi_ch",vi_ch);
                    editor.apply();


                }else if(con_now.equals(compare[1])){

                    vi_j--;
                    textView3.setText(vi_j+"");
                    editor.putInt("vi_j",vi_j);
                    editor.apply();


                }else if(con_now.equals(compare[2])){

                    vi_k--;
                    textView3.setText(vi_k+"");
                    editor.putInt("vi_k",vi_k);
                    editor.apply();


                }else if(con_now.equals(compare[3])){

                    vi_a--;
                    textView3.setText(vi_a+"");
                    editor.putInt("vi_a",vi_a);
                    editor.apply();


                }else if(con_now.equals(compare[4])){

                    vi_c--;
                    textView3.setText(vi_c+"");
                    editor.putInt("vi_c",vi_c);
                    editor.apply();


                }else if(con_now.equals(compare[5])){

                    vi_m--;
                    textView3.setText(vi_m+"");
                    editor.putInt("vi_m",vi_m);
                    editor.apply();

                }
            }
        });

        if(vi_ch>2&&vi_ch<6){
            path1.setFillColor(Color.RED);
        }else if(vi_ch>=6&&vi_ch<9){
            path1.setFillColor(Color.YELLOW);
        }else if(vi_ch>=9){
            path1.setFillColor(Color.BLUE);
        }

        if(vi_j>2&&vi_j<6){
            path2.setFillColor(Color.RED);
        }else if(vi_j>=6&&vi_j<9){
            path2.setFillColor(Color.YELLOW);
        }else if(vi_j>=9){
            path1.setFillColor(Color.BLUE);
        }

        if(vi_k>2&&vi_k<6){
            path3.setFillColor(Color.RED);
        }else if(vi_k>=6&&vi_k<9){
            path3.setFillColor(Color.YELLOW);
        }else if(vi_k>=9){
            path1.setFillColor(Color.BLUE);
        }

        if(vi_a>2&&vi_a<6){
            path4.setFillColor(Color.RED);
        }else if(vi_a>=6&&vi_a<9){
            path4.setFillColor(Color.YELLOW);
        }else if(vi_a>=9){
            path1.setFillColor(Color.BLUE);
        }

        if(vi_c>2&&vi_c<6){
            path5.setFillColor(Color.RED);
        }else if(vi_c>=6&&vi_c<9){
            path5.setFillColor(Color.YELLOW);
        }else if(vi_c>=9){
            path1.setFillColor(Color.BLUE);
        }

        if(vi_m>2&&vi_m<6){
            path6.setFillColor(Color.RED);
        }else if(vi_m>=6&&vi_m<9){
            path6.setFillColor(Color.YELLOW);
        }else if(vi_m>=9){
            path1.setFillColor(Color.BLUE);
        }



    }
}