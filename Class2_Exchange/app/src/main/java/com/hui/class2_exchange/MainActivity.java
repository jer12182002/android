package com.hui.class2_exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etInput;
    TextView tvResult;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        etInput=(EditText)findViewById(R.id.editText);
        tvResult=(TextView)findViewById(R.id.textView3);
        //建立元件
        btn=(Button)findViewById(R.id.button);
        //註冊監聽器
        btn.setOnClickListener(btnClick);
//        btn.setOnClickListener(new A());
//        btn.setOnClickListener(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    //建立監聽器
    View.OnClickListener btnClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strUsd=etInput.getText().toString();
            double usd=0;
            try {
                usd = Double.parseDouble(strUsd);
            }catch (NumberFormatException e){
                Toast.makeText(MainActivity.this,"請輸入數字",Toast.LENGTH_SHORT).show();
            }
            double twd=usd*30.0;
            tvResult.setText(String.valueOf(twd));
        }
    };

    @Override
    public void onClick(View v) {

    }

    //===============
    class A implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }


}
