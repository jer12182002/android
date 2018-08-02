package com.hui.class12_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    RadioButton rbA,rbB,rbC;
    CheckBox cbD,cbE;
    Switch mSwitch;
    SeekBar sBar;
    EditText et;
    final String FILENAME="settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        rbA=(RadioButton)findViewById(R.id.radioButton);
        rbB=(RadioButton)findViewById(R.id.radioButton2);
        rbC=(RadioButton)findViewById(R.id.radioButton3);
        cbD=(CheckBox)findViewById(R.id.checkBox);
        cbE=(CheckBox)findViewById(R.id.checkBox2);
        mSwitch=(Switch)findViewById(R.id.switch1);
        sBar=(SeekBar)findViewById(R.id.seekBar);
        et=(EditText)findViewById(R.id.editText);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        SharedPreferences settings=getSharedPreferences(FILENAME,MODE_PRIVATE);
        SharedPreferences settings=getPreferences(MODE_PRIVATE);
        rbA.setChecked(settings.getBoolean("rbA",false));
        rbB.setChecked(settings.getBoolean("rbB",false));
        rbC.setChecked(settings.getBoolean("rbC",false));
        cbD.setChecked(settings.getBoolean("cbD",false));
        cbE.setChecked(settings.getBoolean("cbE",false));
        mSwitch.setChecked(settings.getBoolean("mSwitch",false));
        sBar.setProgress(settings.getInt("sBar", 0));
        et.setText(settings.getString("et",""));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings=getPreferences(MODE_PRIVATE);
//        SharedPreferences settings=getSharedPreferences(FILENAME,MODE_PRIVATE);
        SharedPreferences.Editor editor =settings.edit();

        editor.putBoolean("rbA",rbA.isChecked());
        editor.putBoolean("rbB",rbB.isChecked());
        editor.putBoolean("rbC",rbC.isChecked());
        editor.putBoolean("cbD",cbD.isChecked());
        editor.putBoolean("cbE",cbE.isChecked());
        editor.putBoolean("mSwitch",mSwitch.isChecked());
        editor.putInt("sBar", sBar.getProgress());
        editor.putString("et", et.getText().toString());
        editor.commit();
    }
}
