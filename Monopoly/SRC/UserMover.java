package com.rob.monopoly;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Robert on 23/03/2018.
 */

public class UserMover extends AppCompatActivity{

    public void move(int id, int numOfPlaces, int currentPosition)
    {

        int newPosition=numOfPlaces+currentPosition;
        switch(newPosition)
        {
            case 0:
                ImageView imageView=findViewById(R.id.square4);
                break;
            case 1:
                CompoundView compoundView=findViewById(R.id.brown3);
                compoundView.turnOnColour(id);
                break;
            case 2:
                CompoundView compoundView2=findViewById(R.id.brown2);
                compoundView2.turnOnColour(id);
                break;
            case 3:
                CompoundView compoundView3=findViewById(R.id.card4);
                compoundView3.turnOnColour(id);
                break;
            case 4:
                CompoundView compoundView4=findViewById(R.id.brown1);
                compoundView4.turnOnColour(id);
                break;
            case 5:
                CompoundView compoundView5=findViewById(R.id.game4);
                compoundView5.turnOnColour(id);
                break;
            case 6:
                CompoundView compoundView6=findViewById(R.id.orange2);
                compoundView6.turnOnColour(id);
                break;
            case 7:
                CompoundView compoundView7=findViewById(R.id.teleport4);
                compoundView7.turnOnColour(id);
                break;
            case 8:
                CompoundView compoundView8=findViewById(R.id.orange1);
                compoundView8.turnOnColour(id);
                break;
            case 9:
                CompoundView compoundView9=findViewById(R.id.game3);
                compoundView9.turnOnColour(id);
                break;
            case 10:
                CompoundView compoundView10=findViewById(R.id.grey3);
                compoundView10.turnOnColour(id);
                break;
            case 11:
                CompoundView compoundView11=findViewById(R.id.tax2);
                compoundView11.turnOnColour(id);
                break;
            case 12:
                CompoundView compoundView12=findViewById(R.id.grey2);
                compoundView12.turnOnColour(id);
                break;
            case 13:
                CompoundView compoundView13=findViewById(R.id.grey1);
                compoundView13.turnOnColour(id);
                break;
            case 14:
                ImageView ImageView14=findViewById(R.id.square3);

                break;
            case 15:
                CompoundView compoundView15=findViewById(R.id.blue3);
                compoundView15.turnOnColour(id);
                break;
            case 16:
                CompoundView compoundView16=findViewById(R.id.brown3);
                compoundView16.turnOnColour(id);
                break;
            case 17:
                CompoundView compoundView17=findViewById(R.id.brown3);
                compoundView17.turnOnColour(id);
                break;
            case 18:
                CompoundView compoundView18=findViewById(R.id.brown3);
                compoundView18.turnOnColour(id);
                break;
            case 19:
                CompoundView compoundView19=findViewById(R.id.brown3);
                compoundView19.turnOnColour(id);
                break;
            case 20:
                CompoundView compoundView20=findViewById(R.id.brown3);
                compoundView20.turnOnColour(id);
                break;
            case 21:
                CompoundView compoundView21=findViewById(R.id.brown3);
                compoundView21.turnOnColour(id);
                break;
            case 22:
                CompoundView compoundView22=findViewById(R.id.brown3);
                compoundView22.turnOnColour(id);
                break;
            case 23:
                CompoundView compoundView23=findViewById(R.id.brown3);
                compoundView23.turnOnColour(id);
                break;
            case 24:
                CompoundView compoundView24=findViewById(R.id.brown3);
                compoundView24.turnOnColour(id);
                break;
            case 25:
                CompoundView compoundView25=findViewById(R.id.brown3);
                compoundView25.turnOnColour(id);
                break;
            case 26:
                CompoundView compoundView26=findViewById(R.id.brown3);
                compoundView26.turnOnColour(id);
                break;
            case 27:
                CompoundView compoundView27=findViewById(R.id.brown3);
                compoundView27.turnOnColour(id);
                break;
            case 28:
                CompoundView compoundView28=findViewById(R.id.brown3);
                compoundView28.turnOnColour(id);
                break;
            case 29:
                CompoundView compoundView29=findViewById(R.id.brown3);
                compoundView29.turnOnColour(id);
                break;
            case 30:
                CompoundView compoundView30=findViewById(R.id.brown3);
                compoundView30.turnOnColour(id);
                break;
            case 31:
                CompoundView compoundView31=findViewById(R.id.brown3);
                compoundView31.turnOnColour(id);
                break;
            case 32:
                CompoundView compoundView32=findViewById(R.id.brown3);
                compoundView32.turnOnColour(id);
                break;
            case 33:
                CompoundView compoundView33=findViewById(R.id.brown3);
                compoundView33.turnOnColour(id);
                break;
            case 34:
                CompoundView compoundView34=findViewById(R.id.brown3);
                compoundView34.turnOnColour(id);
                break;
            case 35:
                CompoundView compoundView35=findViewById(R.id.brown3);
                compoundView35.turnOnColour(id);
                break;
            case 36:
                CompoundView compoundView36=findViewById(R.id.brown3);
                compoundView36.turnOnColour(id);
                break;
            case 37:
                CompoundView compoundView37=findViewById(R.id.brown3);
                compoundView37.turnOnColour(id);
                break;
            case 38:
                CompoundView compoundView38=findViewById(R.id.brown3);
                compoundView38.turnOnColour(id);
                break;

            case 39:
                CompoundView compoundView39=findViewById(R.id.brown3);
                compoundView39.turnOnColour(id);
                break;
            case 40:
                CompoundView compoundView40=findViewById(R.id.brown3);
                compoundView40.turnOnColour(id);
                break;
        }
    }
}
