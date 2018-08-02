package com.hui.class10_datepickerdialog_timepickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etDate,etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        etDate=(EditText)findViewById(R.id.editText);
        etDate.setOnLongClickListener(etLongClick);
        etTime=(EditText)findViewById(R.id.editText2);
        etTime.setOnLongClickListener(etLongClick);
    }


    View.OnLongClickListener etLongClick=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            int hour=c.get(Calendar.HOUR);
            int minute=c.get(Calendar.MINUTE);

            switch (v.getId()){
                case R.id.editText:
                    DatePickerDialog datePickerDialog=
                            new DatePickerDialog(MainActivity.this,setDateListener,year,month,day);
                    datePickerDialog.show();
                    break;
                case R.id.editText2:
                    TimePickerDialog timePickerDialog=
                            new TimePickerDialog(MainActivity.this,setTimeListener,hour,minute,false);
                    timePickerDialog.show();
                    break;
            }
            return false;
        }
    };

    DatePickerDialog.OnDateSetListener setDateListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            String strMonth="";
//            if((monthOfYear+1)<10){
//                strMonth="0"+(monthOfYear+1);
//            }else{
//                strMonth=""+(monthOfYear+1);
//            }
            String strDate=String.format("%04d - %02d - %02d",year,(monthOfYear+1),dayOfMonth);
            etDate.setText(strDate);
        }
    };

    TimePickerDialog.OnTimeSetListener setTimeListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String strTime=String.format("%02d : %02d",hourOfDay,minute);
            etTime.setText(strTime);
        }
    };

}
