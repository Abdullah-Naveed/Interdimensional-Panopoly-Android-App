package com.rob.monopoly;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import java.util.Random;

/**
 * Created by Robert on 23/03/2018.
 */

public class UserMover extends AppCompatActivity{

    private Context context;

    public UserMover(Context context) {
        this.context = context;
    }


    public int move(ViewGroup viewGroup, int index, int User)
    {
        wipe(User,viewGroup);
        if(index>=20&&index<=34)
        {
            int newLocation=0;
            TableRow subView=(TableRow)viewGroup.getChildAt(0);
            index=index%20;
            View view=subView.getVirtualChildAt(index);
            if(index==7)
            {
                Random random=new Random();
                int i=random.nextInt(3)+1;
                switch(i)
                {
                    case 1: subView=(TableRow)viewGroup.getChildAt(6);
                        view=subView.getVirtualChildAt(Math.abs(7));
                        newLocation=7;break;
                    case 2: subView=(TableRow)viewGroup.getChildAt(4);
                        view=subView.getVirtualChildAt(Math.abs(0));
                        newLocation=27;break;
                    case 3: subView=(TableRow)viewGroup.getChildAt(2);
                        view=subView.getVirtualChildAt(Math.abs(14));
                        newLocation=36;break;
                }
                playSound();
            }


            setVisibility(User, view);
            return newLocation;
        }
        else if(index==19||index==35)
        {
            int newLocation=0;
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(1);
            if(index==19){view=subView.getVirtualChildAt(0);}
            if(index==35)
            {

            }
            setVisibility(User, view);

        }
        else if(index==18||index==36)
        {
            int newLocation=0;
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(2);
            if(index==18){view=subView.getVirtualChildAt(0);}
            if(index==36)
            {
                Random random=new Random();
                int i=random.nextInt(3)+1;
                switch(i)
                {
                    case 1: subView=(TableRow)viewGroup.getChildAt(6);
                        view=subView.getVirtualChildAt(Math.abs(7));
                        newLocation=7;break;
                    case 2: subView=(TableRow)viewGroup.getChildAt(4);
                        view=subView.getVirtualChildAt(Math.abs(0));
                        newLocation=27;break;
                    case 3: subView=(TableRow)viewGroup.getChildAt(0);
                        view=subView.getVirtualChildAt(Math.abs(7));
                        newLocation=36;break;
                }
                playSound();
            }
            setVisibility(User, view);
            return newLocation;
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
            int newLocation=0;
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(4);
            if(index==16)
            {
                Random random=new Random();
                int i=random.nextInt(3)+1;
                switch(i)
                {
                    case 1: subView=(TableRow)viewGroup.getChildAt(6);
                        view=subView.getVirtualChildAt(Math.abs(7));
                        newLocation=7;break;
                    case 2: subView=(TableRow)viewGroup.getChildAt(0);
                        view=subView.getVirtualChildAt(Math.abs(7));
                        newLocation=27;break;
                    case 3: subView=(TableRow)viewGroup.getChildAt(2);
                        view=subView.getVirtualChildAt(Math.abs(14));
                        newLocation=36;break;
                }
                playSound();
            }
            if(index==38){view=subView.getVirtualChildAt(14);}
            setVisibility(User, view);
            return newLocation;

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
            int newLocation=0;
            View view=null;
            TableRow subView=(TableRow)viewGroup.getChildAt(6);
            if(index==7)
            {

                Random random=new Random();
                int i=random.nextInt(3)+1;
                switch(i)
                {
                    case 1: subView=(TableRow)viewGroup.getChildAt(4);
                            view=subView.getVirtualChildAt(Math.abs(0));
                            newLocation=16;break;
                    case 2: subView=(TableRow)viewGroup.getChildAt(0);
                            view=subView.getVirtualChildAt(Math.abs(7));
                            newLocation=27;break;
                    case 3: subView=(TableRow)viewGroup.getChildAt(2);
                            view=subView.getVirtualChildAt(Math.abs(14));
                            newLocation=36;break;
                }
                playSound();
            }
            else
            {
                view=subView.getVirtualChildAt(Math.abs(index-14));
            }


            setVisibility(User, view);
            return newLocation;
        }
        return 0;
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


    void playSound()
    {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.teleport_sound);
        mp.start();
    }

}
