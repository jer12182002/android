package com.hui.class8_activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextView tvResult;
    final int RQ=100;
    final String ACC="abc";
    final String PW="234";
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findviews();
    }
    void findviews(){
        tvResult=(TextView)findViewById(R.id.textView);
    }

    public void onLogin(View v){
        Intent intent=new Intent(LoginActivity.this,AccountActivity.class);
//        startActivity(intent);
        startActivityForResult(intent,RQ);
//        Intent intent1=new Intent(LoginActivity.this,AccountActivity2.class);
//        startActivityForResult(intent,RQ1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RQ){
            if(resultCode==RESULT_OK){
                if(data.getStringExtra("acc").equals(ACC)&& data.getStringExtra("pw").equals(PW)){
                    tvResult.setText(getString(R.string.success));
                    count=0;
                }else{
                    count++;
                    if(count==3){
                        LoginActivity.this.finish();
                        Toast.makeText(this,"logout_System",Toast.LENGTH_SHORT).show();
                    }

                    tvResult.setText(getString(R.string.failure));
                    Toast.makeText(this,"login_error"+count,Toast.LENGTH_SHORT).show();
                }

            }
            if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,getString(R.string.login_canceled),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
