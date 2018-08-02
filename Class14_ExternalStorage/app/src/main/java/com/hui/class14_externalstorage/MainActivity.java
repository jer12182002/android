package com.hui.class14_externalstorage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    RadioButton rbPublic,rbPrivate;
    TextView tvShow,tvPath;
    EditText etInput,etFileName;

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
        rbPublic=(RadioButton)findViewById(R.id.radioButton);
        rbPublic.setOnClickListener(rbClick);
        rbPrivate=(RadioButton)findViewById(R.id.radioButton2);
        rbPrivate.setOnClickListener(rbClick);
        tvShow=(TextView)findViewById(R.id.textView);
        tvPath=(TextView)findViewById(R.id.textView2);
        etInput=(EditText)findViewById(R.id.editText);
    }

    public void onSave(View v){
        displayDialog();
    }
    public void onAppend(View v){
        File path=null;
        if(rbPublic.isChecked()) {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }else{
            path=getExternalFilesDir(null);
        }
        String fileName=((TextView)sp.getSelectedView()).getText().toString();
        File file=new File(path,fileName);
        if(!isSDcardExisted()){
            return;
        }
        try {
            FileOutputStream fos=new FileOutputStream(file,true);
            fos.write((etInput.getText().toString()+"\n").getBytes());
            fos.close();
            Toast.makeText(MainActivity.this,"File is appended!",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
    }

    public void onDelete(View v){
        File path=null;
        if(rbPublic.isChecked()) {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }else{
            path=getExternalFilesDir(null);
        }
        String fileName=((TextView)sp.getSelectedView()).getText().toString();
        File file=new File(path,fileName);
        if(file.delete()){
            Toast.makeText(MainActivity.this,"File is deleted!",Toast.LENGTH_SHORT).show();
            setSpinner();
        }else{
            Toast.makeText(MainActivity.this,"File deleted failure!",Toast.LENGTH_SHORT).show();
        }

    }

    void displayDialog(){
        View myView=getLayoutInflater().inflate(R.layout.dialog_item,null);
        etFileName=(EditText)myView.findViewById(R.id.editText2);
        new AlertDialog.Builder(this)
                .setTitle("Svae file?")
                .setView(myView)
                .setPositiveButton("Ok",btnClick)
                .setNegativeButton("Cancel",btnClick)
                .show();

    }
    DialogInterface.OnClickListener btnClick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE){
                if(!isSDcardExisted()){
                    Toast.makeText(MainActivity.this,"SD card is not Existed!",Toast.LENGTH_SHORT).show();
                }
                saveFile();
                setSpinner();
            }
            if(which==DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(MainActivity.this,"Save canceled",Toast.LENGTH_SHORT).show();
            }

        }
    };

    boolean isSDcardExisted(){
        String state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    void saveFile(){
        File path=null;
        if(rbPublic.isChecked()) {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File path2=Environment.getRootDirectory();
            File parentPath=path.getParentFile(); //取得上一層的路徑
            File path1=new File(parentPath,"hui/a");
            path1.mkdir(); //建立資料夾
        }else{
            path=getExternalFilesDir(null);
        }
        String fileName=etFileName.getText().toString();
        if(fileName.equals("")||fileName==null){
            Toast.makeText(MainActivity.this, "Please give a file name!", Toast.LENGTH_SHORT).show();
            return;
        }
        File file=new File(path,fileName);
        try {
            FileOutputStream fos=new FileOutputStream(file);
            fos.write((etInput.getText().toString()+"\n").getBytes());
            fos.close();
            Toast.makeText(MainActivity.this, "File Saved!", Toast.LENGTH_SHORT).show();
            tvPath.setText(file.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
    }

    void setSpinner(){
        File path=null;
        if(rbPublic.isChecked()){
            path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }else{
            path=getExternalFilesDir(null);
        }
        String[] fileNames=path.list();
        ArrayAdapter<String> adt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fileNames);
        adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adt);
    }

    AdapterView.OnItemSelectedListener spListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(!isSDcardExisted()){
                return;
            }
            File path=null;
            if(rbPublic.isChecked()) {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            }else{
                path=getExternalFilesDir(null);
            }
            String fileName=((TextView)view).getText().toString();
            File file=new File(path,fileName);
            try {
                FileInputStream fis =new FileInputStream(file);
                byte[] buffer=new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                String text=new String(buffer);
                tvShow.setText(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener rbClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setSpinner();
        }
    };
}
