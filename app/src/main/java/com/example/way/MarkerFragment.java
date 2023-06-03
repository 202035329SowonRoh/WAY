package com.example.way;

import static io.realm.Realm.getApplicationContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;

public class MarkerFragment extends Fragment {

    private MainActivity mainActivity;
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
    
    // challenge 에 전달할 변수
    int firstStep = 0;
    int enableAsia = 0;
    int enableAmerica = 0;
    int enableAustralia = 0;

    // 생성자에서 MainActivity 객체를 전달받습니다.
    public MarkerFragment(MainActivity activity) {
        mainActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.marker_map, container, false);

        pref = requireContext().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        vi_ch = pref.getInt("vi_ch", 0);
        vi_j = pref.getInt("vi_j", 0);
        vi_k = pref.getInt("vi_k", 0);
        vi_c = pref.getInt("vi_c", 0);
        vi_a = pref.getInt("vi_a", 0);
        vi_m = pref.getInt("vi_m", 0);

        Button apply = rootView.findViewById(R.id.apply_btn);
        ImageView t_map = rootView.findViewById(R.id.map);
        //textView = rootView.findViewById(R.id.textView);
        Button add_btn = rootView.findViewById(R.id.add_num);
        Button min_btn = rootView.findViewById(R.id.min_num);
        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        textView3.setText(con_now +"");

        VectorChildFinder vector = new VectorChildFinder(requireContext(), R.drawable.ic_blank_map,t_map);

        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("중국");
        VectorDrawableCompat.VFullPath path2 = vector.findPathByName("일본");
        VectorDrawableCompat.VFullPath path3 = vector.findPathByName("한국");
        VectorDrawableCompat.VFullPath path4 = vector.findPathByName("호주");
        VectorDrawableCompat.VFullPath path5 = vector.findPathByName("칠레");
        VectorDrawableCompat.VFullPath path6 = vector.findPathByName("멕시코");

        sendDataToMainActivity(countNumOfCountry());

        Spinner spinnerMenu = rootView.findViewById(R.id.country_spinner);
        final String[] countries = getResources().getStringArray(R.array.country);//get list form country

        ArrayAdapter menuAdapter = ArrayAdapter.createFromResource(requireContext(),R.array.country,android.R.layout.simple_spinner_item);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);//use dropdown
        spinnerMenu.setAdapter(menuAdapter);
        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//event when select spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textView.setText(countries[position]);//get string from array
                con_now=spinnerMenu.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Empty.class);

                startActivity(intent);

                sendDataToMainActivity(countNumOfCountry());
            }
        });


        add_btn.setOnClickListener(new View.OnClickListener() {//add +1 to int array visit
            @Override
            public void onClick(View view) {

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
                Log.i("vi_ch",String.valueOf(vi_ch));
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

        buttonEnabled();
        mainActivity.setMarkerData(firstStep, enableAsia, enableAmerica, enableAustralia);

        return rootView;
    }

    // 변수를 전달하고자 하는 로직이 있는 메서드
    private void sendDataToMainActivity(int data) {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.processDataFromFragment(data);
        }
    }

    public int countNumOfCountry() {
        int count = 0;

        if(vi_ch > 0){
            count++;
        }
        if(vi_j > 0){
            count++;
        }
        if(vi_k > 0){
            count++;
        }
        if(vi_c > 0){
            count++;
        }
        if(vi_m > 0){
            count++;
        }
        if(vi_a > 0){
            count++;
        }

        return count;
    }

    public void buttonEnabled() {

        int count = countNumOfCountry();

        Log.i("count",String.valueOf(count));

        Log.i("vi_ch",String.valueOf(vi_ch));

        // first step
        if (count != 0) {
            firstStep = 1;
        }

        // 아시아
        if(vi_ch > 0 || vi_j > 0 || vi_k > 0) {
            enableAsia = 1;
        }

        // 아메리카
        if(vi_m > 0 || vi_c > 0) {
            enableAmerica = 1;
        }

        // 오스트리아
        if(vi_a > 0) {
            enableAustralia = 1;
        }

    }

}
