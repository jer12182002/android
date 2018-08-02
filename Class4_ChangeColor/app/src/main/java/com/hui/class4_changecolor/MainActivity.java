package com.hui.class4_changecolor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    RadioButton rbRed,rbGreen,rbBlue;
    SeekBar sBar;
    TextView tvSelected,tvShow;
    ToggleButton tgbBtn;
    LinearLayout linear;

    int fRed,fGreen,fBlue;
    int bRed,bGreen,bBlue;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(spinnerListener);

        rbRed=(RadioButton)findViewById(R.id.radioButton);
        rbRed.setOnClickListener(rbsClick);
        rbGreen=(RadioButton)findViewById(R.id.radioButton2);
        rbGreen.setOnClickListener(rbsClick);
        rbBlue=(RadioButton)findViewById(R.id.radioButton3);
        rbBlue.setOnClickListener(rbsClick);

        sBar=(SeekBar)findViewById(R.id.seekBar);
        sBar.setOnSeekBarChangeListener(sBarListener);

        tvSelected=(TextView)findViewById(R.id.textView);
        tvShow=(TextView)findViewById(R.id.textView2);

        tgbBtn=(ToggleButton)findViewById(R.id.toggleButton);
        tgbBtn.setOnCheckedChangeListener(tgbListener);

        linear=(LinearLayout)findViewById(R.id.linear);
    }

    View.OnClickListener rbsClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.radioButton:
                    if(pos==0) {
                        sBar.setProgress(fRed);
                    }else{
                        sBar.setProgress(bRed);
                    }
                    tvSelected.setText(rbRed.getText());
//                    tvSelected.setTextColor(0xFFFF0000);
                    tvSelected.setTextColor(Color.RED);
                    break;
                case R.id.radioButton2:
                    if(pos==0) {
                        sBar.setProgress(fGreen);
                    }else{
                        sBar.setProgress(bGreen);
                    }
                    tvSelected.setText(rbGreen.getText());
                    tvSelected.setTextColor(Color.GREEN);
                    break;
                case R.id.radioButton3:
                    if(pos==0) {
                        sBar.setProgress(fBlue);
                    }else{
                        sBar.setProgress(bBlue);
                    }
                    tvSelected.setText(rbBlue.getText());
                    tvSelected.setTextColor(Color.BLUE);
                    break;
            }
        }
    };

    SeekBar.OnSeekBarChangeListener sBarListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //正在拖拉時，會呼叫此方法
            if(pos==0) {
                if (rbRed.isChecked()) {
                    fRed = progress;
                }
                if (rbGreen.isChecked()) {
                    fGreen = progress;
                }
                if (rbBlue.isChecked()) {
                    fBlue = progress;
                }
                tvShow.setTextColor(Color.rgb(fRed, fGreen, fBlue));
            }else{
                if (rbRed.isChecked()) {
                    bRed = progress;
                }
                if (rbGreen.isChecked()) {
                    bGreen = progress;
                }
                if (rbBlue.isChecked()) {
                    bBlue = progress;
                }
                tvShow.setBackgroundColor(Color.rgb(bRed,bGreen,bBlue));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //開始拖拉時，會呼叫此方法

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //停止拖拉時，會呼叫此方法

        }
    };

    AdapterView.OnItemSelectedListener spinnerListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            pos=position;
            if(position==0){
                if(rbRed.isChecked()){
                    sBar.setProgress(fRed);
                }
                if(rbGreen.isChecked()){
                    sBar.setProgress(fGreen);
                }
                if(rbBlue.isChecked()){
                    sBar.setProgress(fBlue);
                }

            }else{
                if(rbRed.isChecked()){
                    sBar.setProgress(bRed);
                }
                if(rbGreen.isChecked()){
                    sBar.setProgress(bGreen);
                }
                if(rbBlue.isChecked()){
                    sBar.setProgress(bBlue);
                }

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    CompoundButton.OnCheckedChangeListener tgbListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                linear.setBackgroundColor(Color.WHITE);
            }else{
                linear.setBackgroundColor(Color.BLACK);
            }

        }
    };




}
