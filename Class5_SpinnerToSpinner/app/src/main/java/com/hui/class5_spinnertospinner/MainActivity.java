package com.hui.class5_spinnertospinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner sp1,sp2;
    TextView tvShow;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
       setDataToSpinner();
    }

    void findviews(){
        sp1=(Spinner)findViewById(R.id.spinner);
        sp1.setOnItemSelectedListener(spinnerListener);
        sp2=(Spinner)findViewById(R.id.spinner2);
        sp2.setOnItemSelectedListener(spinnerListener);
        tvShow=(TextView)findViewById(R.id.textView);
    }

    void setDataToSpinner(){
        String[] data={"a","b","c"};
        ArrayAdapter<String> adt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data);
        adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adt);
    }

    AdapterView.OnItemSelectedListener spinnerListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId()==sp1.getId()) {
               text=((TextView)view).getText().toString();
//                text = parent.getItemAtPosition(position).toString();
//            Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();


                String[] strSubCitys = null;
                if (position == 0) {
                    strSubCitys = getResources().getStringArray(R.array.SubCity1);
                }
                if (position == 1) {
                    strSubCitys = getResources().getStringArray(R.array.SubCity2);
                }
                if (position == 2) {
                    strSubCitys = getResources().getStringArray(R.array.SubCity3);
                }
                ArrayAdapter<String> adt = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_item,
                        strSubCitys);
                adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp2.setAdapter(adt);
            }else{
//                String subCity=((TextView)view).getText().toString();
//                tvShow.setText(text+subCity);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
