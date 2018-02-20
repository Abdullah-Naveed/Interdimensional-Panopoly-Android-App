package com.rob.hardcodedmonopoly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastDamnCustomView lastDamnCustomView=findViewById(R.id.topleft);
        lastDamnCustomView.setText("Please");


    }
}
