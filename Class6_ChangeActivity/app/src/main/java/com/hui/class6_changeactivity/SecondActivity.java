package com.hui.class6_changeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by student on 2015/12/11.
 */
public class SecondActivity extends AppCompatActivity {
    TextView tvChinese,tvEnglish,tvMath,tvTotal;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findviews();
        Intent it=getIntent();
        if(it!=null){
            b=it.getExtras();
        }
        setDataToView();
    }

    void findviews(){
        tvChinese=(TextView)findViewById(R.id.textView);
        tvEnglish=(TextView)findViewById(R.id.textView2);
        tvMath=(TextView)findViewById(R.id.textView3);
        tvTotal=(TextView)findViewById(R.id.textView4);
    }

    void setDataToView(){
        tvChinese.setText(b.getString("ch"));
        tvEnglish.setText(b.getString("en"));
        tvMath.setText(b.getString("ma"));
        int total=Integer.parseInt(b.getString("ch"))+
                Integer.parseInt(b.getString("en"))+
                Integer.parseInt(b.getString("ma"));
        tvTotal.setText(String.valueOf(total));
    }

    public void onCaculate(View v){
//        Intent it=new Intent();
//        it.setClass(SecondActivity.this,MainActivity.class);

//        Intent intent=new Intent(SecondActivity.this,MainActivity.class);
//        startActivity(intent);

        SecondActivity.this.finish();



    }
}
