package com.rob.monopoly;

import com.rob.monopoly.Interfaces.Improvable;
import com.rob.monopoly.Interfaces.Mortgageable;
import com.rob.monopoly.Interfaces.Ownable;
import com.rob.monopoly.Interfaces.Rentable;

import java.util.ArrayList;

import static com.rob.monopoly.MainActivity.properties;

public class Property implements Improvable,Rentable,Mortgageable,Ownable {

    private String ID;
    private int numHouses=0;
    private double housePriceModifier;
    private int baseRentPrice=0;
    private Player player=null;
    private boolean isMortgaged=false;
    private boolean isOwned=false;
    private int buyPrice;
    private String colourGroup;
    private int location;
    private String compoundViewID;

    public Property(String ID,String colourGroup,int buyPrice,double housePriceModifier,int baseRentPrice,String compoundViewID,int location)
    {
        this.ID=ID;
        this.colourGroup=colourGroup;
        this.buyPrice=buyPrice;
        this.housePriceModifier=housePriceModifier;
        this.baseRentPrice=baseRentPrice;
        this.compoundViewID=compoundViewID;
        this.location=location;
        properties.add(this);
    }

    @Override
    public boolean buildHouse() {
        if(ownsColourGroup())
        {
            numHouses++;
            return true;
        }
        return false;
    }

    @Override
    public boolean demolishHouse() {
        if(ownsColourGroup())
        {
            numHouses--;
            return true;
        }
        return false;
    }


    @Override
    public int getHousePrice() {
        if(numHouses==0)
        {
            return baseRentPrice;
        }
        return (int)(numHouses*housePriceModifier+baseRentPrice);
    }


    @Override
    public int getNumHouses() {
        return numHouses;
    }

    @Override
    public String getColourGroup() {
        return colourGroup;
    }

    @Override
    public boolean ownsColourGroup() {

        ArrayList<Property> properties=player.getProperties();
        int i=0;
        for (Property property : properties) {
            if(property.colourGroup==colourGroup)
            {
                i++;
            }
        }

        if(colourGroup=="yellow"&&i==2||colourGroup=="orange"&&i==2)
        {
            return true;
        }
        else if(colourGroup=="brown"&&i==3||colourGroup=="grey"&&i==3||colourGroup=="green"&&i==3||
                colourGroup=="red"&&i==3||colourGroup=="pink"&&i==3||colourGroup=="blue"&&i==3)
        {
            return true;
        }


        return false;
    }


    @Override
    public int getMortgageAmount() {
        return baseRentPrice/2;
    }


    @Override
    public void mortgageProperty() {
        player.deposit(buyPrice/2);
        isMortgaged=true;
        isOwned=false;
        player=null;
    }


    @Override
    public void payRent(Player player) {
        player.withdraw(getRentalAmount());
    }

    @Override
    public int getRentalAmount(){
        return getHousePrice();
    }

    @Override
    public boolean isOwned() {
        return isOwned;
    }

    @Override
    public boolean isMortgaged() {
        return isMortgaged;
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public void setOwner(Player player){
        this.player=player;
    }

    @Override
    public void buyProperty(Player player) {
        this.player=player;
        this.player.addToProperties(this);
        this.player.withdraw(buyPrice);
        isOwned=true;
        isMortgaged=false;

    }

    @Override
    public int buyPrice() {
        return buyPrice;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getCompoundViewID() {
        return compoundViewID;
    }

    public void setCompoundViewID(String compoundViewID) {
        this.compoundViewID = compoundViewID;
    }

//    public static void popup(View view) {
//        Toast.makeText(GameState.getInstance().getContext(),"TEST",Toast.LENGTH_LONG).show();



//        String ownerStr=null;
//        if(currentProperty.getOwner()==null&&GameState.getInstance().getCurrentPlayer().getPlayerLocation()==)/* and you are currently on that tile,check property location against current player location*/
//        {
//            ownerStr="No Owner";
//            //call buy popup
//        }else if(/* and you are currently on that tile,check property location against current player location*/)
//        {
//            Player player=currentProperty.getOwner();
//            //if current owner is you, call build mortgage popup else pay rent pop up
//        }
//        else
//        {
//            //call ok popup
//        }



    }



//    public void PopupCustomizedLayout(View view)
//    {
//        SweetAlertDialog pDialog=new SweetAlertDialog(this);
//        ListView listView=new ListView(this);
//
//
//        //need to add new param to property that holds the id of the compound view. compare id against it
//
//        View parent=(View)view.getParent();
//        String viewName=resourceName(parent.getId());
//
//        Property currentProperty=new Property("red1","red",100,50,1.2,2);;
//        for(Property prop:properties)
//        {
//            if(viewName==prop.getID())
//            {
//                currentProperty=prop;
//            }
//        }
//        String ownerStr=null;
//        if(currentProperty.getOwner()==null/* and you are currently on that tile,check property location against current player location*/)
//        {
//            ownerStr="No Owner";
//            //call buy popup
//        }else if(/* and you are currently on that tile,check property location against current player location*/)
//        {
//            Player player=currentProperty.getOwner();
//            //if current owner is you, call build mortgage popup else pay rent pop up
//        }
//        else
//        {
//            //call ok popup
//        }
//        String[] values = new String[] {
//                "Property Name: "+currentProperty.getID(),
//                "Buy Price: "+currentProperty.buyPrice(),
//                "Rent Price: "+currentProperty.getRentalAmount(),
//                "Owner: "+ownerStr,
//                "Colour Group: "+currentProperty.getColourGroup()
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
//        listView.setAdapter(adapter);
//
//
//        pDialog.setCustomView(listView);
//        pDialog.show();
//
//    }
//
//
//




