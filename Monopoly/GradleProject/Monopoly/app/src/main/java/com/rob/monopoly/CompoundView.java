package com.rob.monopoly;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Robert on 18/02/2018.
 */

public class CompoundView extends LinearLayout {

    Button button=(Button)findViewById(R.id.button);
    Context mainContext;
    public CompoundView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainContext=context;
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.compound_view, this, true);

        String title;
        String subtitle;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            title = a.getString(R.styleable.CustomView_customViewTitle);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (title == null) {
            throw new RuntimeException("No title provided");
        }

        init(title);

    }

    // Setup views
    private void init(String title) {
        TextView titleView = (TextView) findViewById(R.id.customview_textview_title);
        titleView.setText(title);
    }



    public void setText(String str)
    {
        TextView titleView = (TextView) findViewById(R.id.customview_textview_title);
        titleView.setText(str);
    }


    public void changeImage(int imageCode){
        ImageView imageView=(ImageView)findViewById(R.id.customview_imageview_main);
        switch(imageCode){
            case 1: imageView.setImageResource(R.drawable.red);
                break;
            case 2: imageView.setImageResource(R.drawable.yellow);
                break;
            case 3: imageView.setImageResource(R.drawable.pink);
                break;
            case 4: imageView.setImageResource(R.drawable.green);
                break;
            case 5: imageView.setImageResource(R.drawable.blue);
                break;
            case 6: imageView.setImageResource(R.drawable.grey);
                break;
            case 7: imageView.setImageResource(R.drawable.orange);
                break;
            case 8: imageView.setImageResource(R.drawable.brown);
                break;
            case 9: imageView.setImageResource(R.drawable.chancecard);
                break;
            case 10: imageView.setImageResource(R.drawable.tax);
                break;
            case 11: imageView.setImageResource(R.drawable.game);
                break;
            case 12: imageView.setImageResource(R.drawable.teleport);
                break;
            default:break;

        }
    }



    public void turnOnColour(int id)
    {
        switch(id)
        {
            case 1: View view=findViewById(R.id.colored_bar1);
                    view.setVisibility(View.VISIBLE);
                    break;
            case 2: View view2=findViewById(R.id.colored_bar2);
                view2.setVisibility(View.VISIBLE);
                break;
            case 3: View view3=findViewById(R.id.colored_bar3);
                view3.setVisibility(View.VISIBLE);
                break;
            case 4: View view4=findViewById(R.id.colored_bar4);
                view4.setVisibility(View.VISIBLE);
                break;
        }
    }




}
