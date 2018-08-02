package com.hui.class3_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etTableNum;
    RadioButton rbCustomer,rbVip,rbCoffee,rbBlackTea;
    CheckBox cbA,cbB,cbC,cbD;
    ImageButton imgBtn;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        etTableNum=(EditText)findViewById(R.id.editText);
        rbCustomer=(RadioButton)findViewById(R.id.radioButton);
        rbVip=(RadioButton)findViewById(R.id.radioButton2);
        rbCoffee=(RadioButton)findViewById(R.id.radioButton3);
        rbBlackTea=(RadioButton)findViewById(R.id.radioButton4);

        cbA=(CheckBox)findViewById(R.id.checkBox);
        cbA.setTag(100.0);
        cbB=(CheckBox)findViewById(R.id.checkBox2);
        cbB.setTag(150.0);
        cbC=(CheckBox)findViewById(R.id.checkBox3);
        cbC.setTag(200.0);
        cbD=(CheckBox)findViewById(R.id.checkBox4);
        cbD.setTag(250.0);

        imgBtn=(ImageButton)findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(imgbtnClick);
        tvShow=(TextView)findViewById(R.id.textView);
    }

    View.OnClickListener imgbtnClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double total=0;
            StringBuilder sb=new StringBuilder();
            sb.append(getResources().getString(R.string.tableNumber)).append(etTableNum.getText().toString()).append("\n");
            if(rbCustomer.isChecked()){
                sb.append(rbCustomer.getText()).append("\n");
            }else{
                sb.append(rbVip.getText()).append("\n");
            }
            sb.append(getString(R.string.main)).append("\n").append("  ");
            if(cbA.isChecked()){
                total+=(Double)cbA.getTag();
                sb.append(cbA.getText()).append("  ");
            }
            if(cbB.isChecked()){
                total+=(Double)cbB.getTag();
                sb.append(cbB.getText()).append("  ");
            }
            if(cbC.isChecked()){
                total+=(Double)cbC.getTag();
                sb.append(cbC.getText()).append("  ");
            }
            if(cbD.isChecked()){
                total+=(Double)cbD.getTag();
                sb.append(cbD.getText()).append("  ");
            }
            sb.append("\n").append(getString(R.string.Beverage)).append("\n").append("  ");
            if(rbCoffee.isChecked()){
                sb.append(rbCoffee.getText()).append("\n");
            }else {
                sb.append(rbBlackTea.getText()).append("\n");
            }

            if(rbVip.isChecked()){
                sb.append("共計").append(total*0.9).append("\n");
            }else{
                sb.append("共計").append(total).append("\n");
            }

            tvShow.setText(sb);


        }
    };
}
