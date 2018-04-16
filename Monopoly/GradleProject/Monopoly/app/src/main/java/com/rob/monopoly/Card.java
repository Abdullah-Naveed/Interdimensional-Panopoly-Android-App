package com.rob.monopoly;

import java.util.ArrayList;
import java.util.Random;

public class Card{

    private String message;
    private Player player=null;

    public Card(String message){
        this.message=message;
    }


    public void runAway(){

        Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;

        GameState.getInstance().getCurrentPlayer().move(i);
        // returns noc list such as 'you see a villain, so you run away from him and advance i spaces
    }

    public void hospital(){
    	GameState.getInstance().getCurrentPlayer().withdraw(150);

    // Returns noc list such as ' you get into a fight with villain, you break your leg pay 150 hospital fees.
    }

    public void birthday(){

    	GameState.getInstance().getCurrentPlayer().deposit(200);

    // e.g its your birthday, you get a surprise card from your favourite hero.
    }
    public void birthday2(){

       int i = GameState.getInstance().getNumPlayers();
        GameState.getInstance().getCurrentPlayer().deposit(i*50);

        // It's your birthday you get 50 euro from every player.
    }

    public void meeting(){

        Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;

        GameState.getInstance().getCurrentPlayer().setPlayerLocation(i);
        // e.g you accidentaly took heros clothing home. you arrange to meet up at location i to give it back.
    }

    public void armWrestle(){
    	GameState.getInstance().getCurrentPlayer().deposit(125);
        // e.g you came up against the NEGATIVE villain in an armwrestle competition. you won 125.
    }

    public void backToStart(){

    	GameState.getInstance().getCurrentPlayer().setPlayerLocation(0);
    	GameState.getInstance().getCurrentPlayer().deposit(200);

    // e.g Villin was able to sneak you back to the start so you gain another 200
    }

    public void robbery(){
    	GameState.getInstance().getCurrentPlayer().deposit(100);

        // you get pickpocketed by villain
    }

    public void carCrash(){
    	GameState.getInstance().getCurrentPlayer().withdraw(200);
        // You crash into villains vehicle, youve to pay 200
    }

    public void stealing(){
    	GameState.getInstance().getCurrentPlayer().deposit(125);
        // You steal villains favourite clothes. you sell them on ebay
    }

   public void bowling(){
	   GameState.getInstance().getCurrentPlayer().withdraw(40);
       // You go bowling with hero, hero and hero. you pay for everyone.
   }

   public void onlineShop(){
	   GameState.getInstance().getCurrentPlayer().withdraw(50);

       // You bought hero a new jacket similar to his favourite clothing.
   }

    public void propertyDamages(){
       int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
       GameState.getInstance().getCurrentPlayer().withdraw(i*25);

        // the NEGATIVE villain made damages to all of your properties. pay 25 for every property you own
    }


    // Possible ideas for chance card //

    // - steal someones property.
    // - Destroy someones house(if they have one).
    // - Steal money from another player
    // - throw dice and pay 10x what you thrown,
}
