package com.hui.class_assets;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
    }

    void findviews(){
        image=(ImageView)findViewById(R.id.imageView);
        tvShow=(TextView)findViewById(R.id.textView);
    }

    public void onLoadImage(View v){
        //使用Drawable
//        AssetManager assetMgr=getAssets();  //Context
//        try {
//            InputStream is=assetMgr.open("Koala.jpg");
//            Drawable drawable=Drawable.createFromStream(is,"");
//            image.setImageDrawable(drawable);
//            is.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //使用Bitmap
        try {
            InputStream is=getAssets().open("Koala.jpg");
            Bitmap bmp= BitmapFactory.decodeStream(is);
            image.setImageBitmap(bmp);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLoadText(View v){
        try {
            InputStream is=getAssets().open("text.txt");
            byte[] buffer=new byte[is.available()];
            is.read(buffer);
            String text=new String(buffer);

            tvShow.setText(text);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
