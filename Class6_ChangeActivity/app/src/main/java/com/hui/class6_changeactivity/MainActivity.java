package com.hui.class6_changeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etChinese,etEnglish,etMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        etChinese=(EditText)findViewById(R.id.editText);
        etEnglish=(EditText)findViewById(R.id.editText2);
        etMath=(EditText)findViewById(R.id.editText3);
    }


    public void onCaculate(View v){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, SecondActivity.class);

        Bundle b=new Bundle();
        b.putString("ch",etChinese.getText().toString());
        b.putString("en",etEnglish.getText().toString());
        b.putString("ma",etMath.getText().toString());
        intent.putExtras(b);

        startActivity(intent);

    }
}
