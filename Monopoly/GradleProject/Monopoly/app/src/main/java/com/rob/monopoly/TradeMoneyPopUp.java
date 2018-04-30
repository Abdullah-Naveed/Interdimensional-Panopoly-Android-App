package com.rob.monopoly;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

public class TradeMoneyPopUp {


    public void tradeMoneyPopup()
    {
        AlertDialog tradeDialog;
        final boolean secondPopup=false;

        CharSequence[] items = new CharSequence[GameState.getInstance().getCurrentPlayer().getNumProperties()];
        ArrayList<Property> properties=GameState.getInstance().getCurrentPlayer().getProperties();
        ArrayList<Property> selectedProperties=new ArrayList<>();
        int i=0;
        for(Property property:properties)
        {
            items[i]=property.getID();
            i++;
        }

        //ask user how much he would like to buy a property for
        AlertDialog.Builder decideMoneyPopup = new AlertDialog.Builder(GameState.getInstance().getContext());
        decideMoneyPopup.setTitle("How much do you want to buy a property for?");

        // Set an EditText view to get user input
        final EditText input = new EditText(GameState.getInstance().getContext());
        decideMoneyPopup.setView(input);

        input.setFilters(new InputFilter[] {
                // Maximum 2 characters.
                new InputFilter.LengthFilter(3),
                // Digits only.
                DigitsKeyListener.getInstance(),  // Not strictly needed, IMHO.
        });

        decideMoneyPopup.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                // Do something with value!
                choosePlayerPopUp(value);
            }
        });

        decideMoneyPopup.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });



        tradeDialog = decideMoneyPopup.create();//AlertDialog dialog; create like this outside onClick
        tradeDialog.show();

    }

    private void choosePlayerPopUp(String price) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder2.setTitle("Choose the player you would like to sell your property to with");
        CharSequence[] items2 = new CharSequence[GameState.getInstance().getNumPlayers()-1];
        int j=0;
        for(Player player:GameState.getInstance().getPlayers())
        {
            if(GameState.getInstance().getCurrentPlayer()!=player)
            {
                items2[j]=player.getID();
                j++;
            }

        }
        try
        {
            builder2.setSingleChoiceItems(items2, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    for(Player p : GameState.getInstance().getPlayers()){
                        if(p.getID()==items2[which]){
                            choseOtherPlayerProperties(p, price);
                            dialog.cancel();
                        }
                    }
                }
            });
            AlertDialog tradeDialog2=builder2.create();
            tradeDialog2.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private void choseOtherPlayerProperties(Player player, String price) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder2.setTitle("Choose The Other Properties To Trade With");
        CharSequence[] items2 = new CharSequence[player.getNumProperties()];
        ArrayList<Property> properties=player.getProperties();
        ArrayList<Property> selectedProperties=new ArrayList<>();
        final ArrayList seletedItems=new ArrayList();
        int i=0;
        for(Property property:properties)
        {
            items2[i]=property.getID();
            i++;
        }
        try
        {
            builder2.setMultiChoiceItems(items2, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        // indexSelected contains the index of item (of which checkbox checked)
                        @Override
                        public void onClick(DialogInterface dialog, int indexSelected,
                                            boolean isChecked) {
                            if (isChecked) {
                                // If the user checked the item, add it to the selected items
                                // write your code when user checked the checkbox
                                seletedItems.add(indexSelected);
                            } else if (seletedItems.contains(indexSelected)) {
                                // Else, if the item is already in the array, remove it
                                // write your code when user Uchecked the checkbox
                                seletedItems.remove(Integer.valueOf(indexSelected));
                            }
                        }
                    })
                    // Set the action buttons
                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //  Your code when user clicked on OK
                            //  You can write the code  to save the selected item here
                            for(Object i:seletedItems)
                            {
                                selectedProperties.add(properties.get((int)i));
                            }

//                            new dialog to agree
                            agreementBetweenPlayers(player, price ,selectedProperties);



                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //  Your code when user clicked on Cancel
                        }
                    });
            AlertDialog tradeDialog2=builder2.create();
            tradeDialog2.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private void agreementBetweenPlayers(Player player, String price, ArrayList<Property> properties) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder2.setTitle(player.getID() + ", Do you agree to this trade? ");

        builder2.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //function to do the trading between the players
                tradeProperties(player, price, properties);
                dialogInterface.dismiss();

            }
        });

        builder2.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog tradeDialog2=builder2.create();
        tradeDialog2.show();


    }

    private void tradeProperties(Player player, String price, ArrayList<Property> properties) {

        for(Property property : properties){
            player.removeFromProperties(property); //remove property from players property list
            player.deposit(Integer.parseInt(price)); //deposit to players account
            GameState.getInstance().getCurrentPlayer().withdraw(Integer.parseInt(price));

            GameState.getInstance().setPropertyOwner(property.getID(),GameState.getInstance().getCurrentPlayer());
        }

        TastyToast.makeText(GameState.getInstance().getContext(), "Trade Successful", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

    }


}
