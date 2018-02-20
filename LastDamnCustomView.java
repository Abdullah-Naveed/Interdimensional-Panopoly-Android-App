package com.rob.hardcodedmonopoly;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Robert on 18/02/2018.
 */

public class LastDamnCustomView extends LinearLayout {

    public LastDamnCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.lastdamncustomview, this, true);

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


}
