package com.rob.monopoly;

import android.view.ViewGroup;

public class Player extends UserMover{

    private int PlayerNum=1;
    private int PlayerLocation=0;
    private ViewGroup viewGroup=null;

    public Player(ViewGroup viewGroup)
    {
        this.viewGroup=viewGroup;
    }

    public void move()
    {
        PlayerLocation++;
        PlayerLocation=PlayerLocation%40;
        move(viewGroup,PlayerLocation,1);
    }

}
