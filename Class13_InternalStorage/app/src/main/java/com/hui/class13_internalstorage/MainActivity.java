package com.hui.class13_internalstorage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    TextView tvShow,tvPath;
    EditText etInput,etFileName;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSpinner();
    }

    void findviews(){
        sp=(Spinner)findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(spListener);
        tvShow=(TextView)findViewById(R.id.textView);
        tvPath=(TextView)findViewById(R.id.textView2);
        etInput=(EditText)findViewById(R.id.editText);
    }

    public void onSave(View v){
        displayDialog();
    }

    public void onAppend(View v){
        String fileName=((TextView)sp.getSelectedView()).getText().toString();
        try {
            FileOutputStream fos=openFileOutput(fileName,MODE_APPEND);
            fos.write((etInput.getText().toString()+"\n").getBytes());
            fos.close();
            Toast.makeText(MainActivity.this,"檔案附加成功",Toast.LENGTH_SHORT).show();
            setSpinner();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }

    }

    public void onDelete(View v){
        String fileName=((TextView)sp.getSelectedView()).getText().toString();
        if(deleteFile(fileName)){
            Toast.makeText(MainActivity.this,"檔案刪除成功",Toast.LENGTH_SHORT).show();
            setSpinner();
        }else{
            Toast.makeText(MainActivity.this,"檔案刪除失敗",Toast.LENGTH_SHORT).show();
        }


    }

    void displayDialog(){
        View myView=getLayoutInflater().inflate(R.layout.dialog_item,null);
        etFileName=(EditText)myView.findViewById(R.id.editText2);
        new AlertDialog.Builder(this)
                .setTitle("儲存檔案?")
                .setView(myView)
                .setPositiveButton("確定",btnClick)
                .setNegativeButton("取消",btnClick)
                .show();
    }

    DialogInterface.OnClickListener btnClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE){
                saveFile();
                setSpinner();
                Toast.makeText(MainActivity.this,"檔案儲存成功",Toast.LENGTH_SHORT).show();
            }
            if(which==DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(MainActivity.this,"取消檔案儲存",Toast.LENGTH_SHORT).show();
            }

        }
    };

    void saveFile(){
        try {
            FileOutputStream fos=openFileOutput(etFileName.getText().toString(),MODE_PRIVATE);
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(etInput.getText().toString()+"\n");
            bw.close();
            fos.close();
            tvPath.setText(getFilesDir().toString());

            //======我是分隔線============
//            FileOutputStream fos=openFileOutput(etFileName.getText().toString(),MODE_PRIVATE);
//            fos.write(etInput.getText().toString().getBytes());
//            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
    }

    void setSpinner(){
        String[] files=fileList();
        ArrayAdapter<String> adt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,files);
        adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adt);
        sp.setSelection(selected);
    }

    AdapterView.OnItemSelectedListener spListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selected=position;
            String fileName =((TextView)view).getText().toString();
            if(fileName.equals("") || fileName==null){
                return;
            }
            try {
                FileInputStream fis=openFileInput(fileName);
                byte[] buffer=new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                String text=new String(buffer);
                tvShow.setText(text);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
