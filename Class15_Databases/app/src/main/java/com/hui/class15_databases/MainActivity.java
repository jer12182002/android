package com.hui.class15_databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText etId,etName,etPhone,etAddress;
    Button btnPrevious,btnNext;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        loadDatabase();
        //第一種做法，利用SQLiteOpenHelper
//        setDatabase();

        //第二種做法，利用Context 要注意IF NOT EXISTS
//        db=openOrCreateDatabase("MyDB1.db",MODE_PRIVATE,null);
//        db.execSQL(Util.CREATE_TABLE);

        //第三種做法，利用SQLiteDatabases 要注意IF NOT EXISTS，還要注意<uses-permission>
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File file =new File(path,"MyDB.db");
        db=SQLiteDatabase.openOrCreateDatabase(file,null);
//        db.execSQL(Util.CREATE_TABLE);
    }

    void loadDatabase(){
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File file =new File(path,"MyDB.db");
        try {
            InputStream is=getAssets().open("MyDB.db");
            byte[] buffer=new byte[is.available()];
            is.read(buffer);
            is.close();

            FileOutputStream fos=new FileOutputStream(file);
            fos.write(buffer);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void findviews(){
        etId=(EditText)findViewById(R.id.editText);
        etName=(EditText)findViewById(R.id.editText2);
        etPhone=(EditText)findViewById(R.id.editText3);
        etAddress=(EditText)findViewById(R.id.editText4);
        btnPrevious=(Button)findViewById(R.id.button5);
        btnNext=(Button)findViewById(R.id.button6);
    }

    void setDatabase(){
        DBHelper dbHelper=new DBHelper(this,Util.DATABASE_NAME,null,Util.VERSION);
        db=dbHelper.getWritableDatabase();
    }

    public void onInsert(View v){
        ContentValues values=new ContentValues();
        values.put("Id",etId.getText().toString());
        values.put("Name",etName.getText().toString());
        values.put("Phone",etPhone.getText().toString());
        values.put("Address",etAddress.getText().toString());
        db.insert(Util.TABLE_NAME, null, values);
        Toast.makeText(this,"Data inserts successfully!",Toast.LENGTH_SHORT).show();

    }
    public void onUpdate(View v){
        ContentValues values=new ContentValues();
        values.put("Id",etId.getText().toString());
        values.put("Name",etName.getText().toString());
        values.put("Phone",etPhone.getText().toString());
        values.put("Address",etAddress.getText().toString());
        db.update(Util.TABLE_NAME, values, "Id=?", new String[]{etId.getText().toString()});
        Toast.makeText(this,"Data updates successfully!",Toast.LENGTH_SHORT).show();

    }
    public void onDelete(View v){
        db.delete(Util.TABLE_NAME, "Id=?", new String[]{etId.getText().toString()});
        Toast.makeText(this,"Data deletes successfully!",Toast.LENGTH_SHORT).show();

    }
    public void onQuery(View v){
        cursor=db.query(Util.TABLE_NAME,null,null,null,null,null,"Id");
        if(cursor==null){
            return;
        }
        btnPrevious.setEnabled(true);
        btnNext.setEnabled(true);

    }
    public void onPrevious(View v){
        if(cursor.isFirst()){
            Toast.makeText(this,"This is first row!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cursor.moveToPrevious()){
            etId.setText(cursor.getString(cursor.getColumnIndex("Id")));
            etName.setText(cursor.getString(cursor.getColumnIndex("Name")));
            etPhone.setText(cursor.getString(cursor.getColumnIndex("Phone")));
            etAddress.setText(cursor.getString(cursor.getColumnIndex("Address")));
        }

    }
    public void onNext(View v){
        if(cursor.isLast()){
            Toast.makeText(this,"This is last row!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cursor.moveToNext()){
            etId.setText(cursor.getString(cursor.getColumnIndex("Id")));
            etName.setText(cursor.getString(cursor.getColumnIndex("Name")));
            etPhone.setText(cursor.getString(cursor.getColumnIndex("Phone")));
            etAddress.setText(cursor.getString(cursor.getColumnIndex("Address")));
        }

    }
}
