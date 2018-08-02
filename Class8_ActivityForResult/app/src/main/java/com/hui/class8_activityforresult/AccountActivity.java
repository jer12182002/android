package com.hui.class8_activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AccountActivity extends AppCompatActivity {
    EditText etAcc,etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        findviews();
    }

    void findviews(){
        etAcc=(EditText)findViewById(R.id.editText);
        etPw=(EditText)findViewById(R.id.editText2);
    }

    public void onOK(View v){
        Intent data=new Intent();
        data.putExtra("acc",etAcc.getText().toString());
        data.putExtra("pw",etPw.getText().toString());
        setResult(RESULT_OK, data);
        AccountActivity.this.finish();

    }
    public void onCancel(View v){
        setResult(RESULT_CANCELED);
        AccountActivity.this.finish();


    }
}
