package com.rob.monopoly;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;

/**
 * Created by Robert on 23/03/2018.
 */

public class UserMover extends AppCompatActivity{



    public void move(ViewGroup viewGroup, int index, int User)
    {
        wipe(User,viewGroup);
        if(index>=20&&index<=34)
        {
            TableRow subView=(TableRow)viewGroup.getChildAt(0);
            View view=subView.getVirtualChildAt(index%20);
            setVisibility(User, view);
        }
        else if(index==19||index==35)
        {
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(1);
            if(index==19){view=subView.getVirtualChildAt(0);}
            if(index==35){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
        }
        else if(index==18||index==36)
        {
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(2);
            if(index==18){view=subView.getVirtualChildAt(0);}
            if(index==36){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
        }
        else if(index==17||index==37)
        {
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(3);
            if(index==17){view=subView.getVirtualChildAt(0);}
            if(index==37){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
        }
        else if(index==16||index==38)
        {
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(4);
            if(index==16){view=subView.getVirtualChildAt(0);}
            if(index==38){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
        }
        else if(index==15||index==39)
        {
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(5);
            if(index==15){view=subView.getVirtualChildAt(0);}
            if(index==39){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
        }
        else if(index<=14&&index>=0)
        {
            TableRow subView=(TableRow)viewGroup.getChildAt(6);
            View view=subView.getVirtualChildAt(Math.abs(index-14));

            setVisibility(User, view);
        }

    }

    public void setVisibility(int User, View view)
    {
        switch(User)
        {
            case 1:View colour1=view.findViewById(R.id.colored_bar1);try{colour1.setVisibility(View.VISIBLE);}catch(Exception e){};break;
            case 2:View colour2=view.findViewById(R.id.colored_bar2);try{colour2.setVisibility(View.VISIBLE);}catch(Exception e){};break;
            case 3:View colour3=view.findViewById(R.id.colored_bar3);try{colour3.setVisibility(View.VISIBLE);}catch(Exception e){};break;
            case 4:View colour4=view.findViewById(R.id.colored_bar4);try{colour4.setVisibility(View.VISIBLE);}catch(Exception e){};break;
        }
    }

    public void wipe(int User, ViewGroup viewGroup)
    {
        for(int i=0;i<viewGroup.getChildCount();i++)
        {
            TableRow subView=(TableRow)viewGroup.getChildAt(i);
            for(int j=0;j<subView.getVirtualChildCount();j++)
            {
                View view=subView.getVirtualChildAt(j);
                View colour1=view.findViewById(R.id.colored_bar1);try{colour1.setVisibility(View.INVISIBLE);}catch(Exception e){};
            }
        }
    }


}
