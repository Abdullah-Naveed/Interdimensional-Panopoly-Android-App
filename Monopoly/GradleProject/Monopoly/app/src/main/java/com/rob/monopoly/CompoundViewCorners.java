package com.rob.monopoly;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CompoundViewCorners extends LinearLayout {

    Button button=(Button)findViewById(R.id.button);
    Context mainContext;
    public CompoundViewCorners(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainContext=context;
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.compound_view_corners, this, true);

        String title;
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
    }

    public void changeImage(int imageCode){
        ImageView imageView=findViewById(R.id.imageviewcorners);
        switch(imageCode){
            case 1: imageView.setImageResource(R.drawable.parking);
                break;
            case 2: imageView.setImageResource(R.drawable.jail);
                break;
            case 3: imageView.setImageResource(R.drawable.injail);
                break;
            case 4: imageView.setImageResource(R.drawable.go);
                break;
            default:break;

        }
    }

}
