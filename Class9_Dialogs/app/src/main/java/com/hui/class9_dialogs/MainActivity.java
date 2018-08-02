package com.hui.class9_dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        etInput=(EditText)findViewById(R.id.editText);
//        etInput.findViewById(R.id.editText);
    }
    //============ AlertDialog ===============
    public void onAlertDialog(View v){
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("標題")
                .setMessage("訊息內容")
                .setCancelable(false)  //設定不能被取消
                .setPositiveButton("Ok", btnClick)
                .setNeutralButton("中性",btnClick)
                .setNegativeButton("Cancel",btnClick)
                .create();
        alertDialog.show();
    }

    DialogInterface.OnClickListener btnClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    Toast.makeText(MainActivity.this,"中性",Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(MainActivity.this,"cancel",Toast.LENGTH_SHORT).show();
                    break;


                case 0:
                    Toast.makeText(MainActivity.this,"Red",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(MainActivity.this,"Green",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"Blue",Toast.LENGTH_SHORT).show();
                    break;


            }

        }
    };

//    AlertDialog getDialog(CharSequence title,CharSequence[] list){
//        return new AlertDialog.Builder(this).create();
//    }

    //============ Dialog with List ===============
    public void onDialogWithList(View v){
        String[] colors={"Red","Green","Blue"};
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("選取一個顏色")

//                .setMessage("訊息內容")  //用setItems取代
                .setItems(colors,btnClick)

                .setCancelable(false)  //設定不能被取消
                .create();
        alertDialog.show();

//        AlertDialog alertDialog1=getDialog("對話視窗","訊息");
//        alertDialog1.show();
    }

    //============ Persistent_Single ===============
    int checkedItem;
    int selected;
    public void onPersistent_Single(View v){
        String[] days={"Mon","Tue","Wed","Thr","Fri","Sat","Sun"};
        AlertDialog alertDialog =new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("選取一天")

                .setSingleChoiceItems(days, checkedItem, singleClick)

                .setCancelable(false)  //設定不能被取消
                .setPositiveButton("Ok", singleClick)
                .setNegativeButton("Cancel", singleClick)
                .create();
        alertDialog.show();

    }

    DialogInterface.OnClickListener singleClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which>=0) {
                selected = which;            }
            if(which==DialogInterface.BUTTON_POSITIVE){
                checkedItem=selected;
            }
        }
    };

    //============ Persistent_Multiple ===============
    boolean[] checkedItems;
    ArrayList<Integer> list=new ArrayList<>();
    ArrayList<Integer> temp=new ArrayList<>();
    public void onPersistent_Multiple(View v){
        String[] days={"Mon","Tue","Wed","Thr","Fri","Sat","Sun"};
        checkedItems=new boolean[days.length];
        for(int i=0;i<list.size();i++){
            checkedItems[list.get(i)]=true;
        }
        AlertDialog alertDialog =new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("選取多天")

                .setMultiChoiceItems(days,checkedItems,multiChoice)

                .setCancelable(false)  //設定不能被取消
                .setPositiveButton("Ok", multiClick)
                .setNegativeButton("Cancel", multiClick)
                .create();
        alertDialog.show();

    }

    DialogInterface.OnMultiChoiceClickListener multiChoice=new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//            checkedItems[which]=isChecked;

            if(isChecked){
                temp.add(which);
            }else{
                temp.remove(Integer.valueOf(which));
            }

        }
    };

    DialogInterface.OnClickListener multiClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE){
                list=temp;
                temp=new ArrayList<>();
            }
        }
    };

    //============ CustomLayout ===============
    EditText etPW;
    SeekBar sBar;
    public void onCustomLayout(View v){
        LayoutInflater inflater=getLayoutInflater();
        View myView=inflater.inflate(R.layout.dialog_item, null);
        etPW=(EditText)myView.findViewById(R.id.editText3);
        sBar=(SeekBar)myView.findViewById(R.id.seekBar);
        AlertDialog alertDialog =new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("帳號密碼")
                .setView(myView)
                .setCancelable(false)  //設定不能被取消
                .setPositiveButton("Ok", customClick)
                .setNegativeButton("Cancel", customClick)
                .create();
        alertDialog.show();

    }

    DialogInterface.OnClickListener customClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE){
//                etInput.setText(etPW.getText().toString());
                etInput.setText(String.valueOf(sBar.getProgress()));
            }

        }
    };

}
