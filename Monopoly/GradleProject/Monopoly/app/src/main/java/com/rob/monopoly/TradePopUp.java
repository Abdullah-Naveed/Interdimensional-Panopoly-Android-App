package com.rob.monopoly;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

public class TradePopUp {

    public void tradePopup()
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


        // arraylist to keep the selected items
        final ArrayList seletedItems=new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder.setTitle("Select What Properties You Wanna Trade");
        builder.setMultiChoiceItems(items, null,
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
                        choosePlayerPopUp(selectedProperties);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                });

        tradeDialog = builder.create();//AlertDialog dialog; create like this outside onClick
        tradeDialog.show();

    }

    private void choosePlayerPopUp(ArrayList<Property> selectedProperties) {
        for(Property property:selectedProperties)
        {
            System.out.println(property.getID());
        }
        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder2.setTitle("Choose the player you would like to trade with");
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
                            choseOtherPlayerProperties(p,selectedProperties);
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

    private void choseOtherPlayerProperties(Player player, ArrayList<Property> selectedP) {

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
                            agreementBetweenPlayers(player, selectedP,selectedProperties);



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

    private void agreementBetweenPlayers(Player player, ArrayList<Property> firstProperties, ArrayList<Property> secondProperties) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(GameState.getInstance().getContext());
        builder2.setTitle(player.getID() + ", Do you agree to this trade? ");

        builder2.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //function to do the trading between the players
                tradeProperties(player, firstProperties, secondProperties);
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

    private void tradeProperties(Player player, ArrayList<Property> firstProperties, ArrayList<Property> secondProperties) {

        for(Property property : firstProperties){
            GameState.getInstance().getCurrentPlayer().removeFromProperties(property);
            player.addToProperties(property);
            GameState.getInstance().setPropertyOwner(property.getID(),player);
        }

        for(Property property : secondProperties){
            player.removeFromProperties(property);
            GameState.getInstance().getCurrentPlayer().addToProperties(property);
            GameState.getInstance().setPropertyOwner(property.getID(),GameState.getInstance().getCurrentPlayer());
        }

        TastyToast.makeText(GameState.getInstance().getContext(), "Trade Successful", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

    }



}
