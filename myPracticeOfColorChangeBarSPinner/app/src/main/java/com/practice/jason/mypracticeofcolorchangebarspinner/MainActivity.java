package com.practice.jason.mypracticeofcolorchangebarspinner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    TextView txshow;
    SeekBar skbar;
    RadioButton rdRed, rdBlue, rdGreen;
    int pos, SKRed, SKBlue, SKGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        skbar = (SeekBar) findViewById(R.id.seekBar);
        skbar.setOnSeekBarChangeListener(skbarchange);
        txshow = (TextView) findViewById(R.id.textView4);
        rdRed = (RadioButton) findViewById(R.id.radioButton);
        rdRed.setOnClickListener(rdClick);
        rdBlue = (RadioButton) findViewById(R.id.radioButton2);
        rdBlue.setOnClickListener(rdClick);
        rdGreen = (RadioButton) findViewById(R.id.radioButton3);
        rdGreen.setOnClickListener(rdClick);
    }

    RadioButton.OnClickListener rdClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (pos == 0) {
                if (rdRed.isChecked()) {
                    skbar.setProgress(SKRed);


                }
                if (rdGreen.isChecked()) {
                    skbar.setProgress(SKGreen);
                }
                if (rdBlue.isChecked()) {
                    skbar.setProgress(SKRed);
                }
            }
            if (pos == 1) {

                if (rdRed.isChecked()) {
                }
                if (rdGreen.isChecked()) {
                }
                if (rdBlue.isChecked()) {

                }
            }


        }


    };
    SeekBar.OnSeekBarChangeListener skbarchange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (rdRed.isChecked()) {
                SKRed = progress;

            }
            if (rdGreen.isChecked()) {
                SKGreen = progress;

            }
            if (rdBlue.isChecked()) {
                SKBlue = progress;
            }

            txshow.setTextColor(Color.rgb(SKRed,SKGreen,SKBlue));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
