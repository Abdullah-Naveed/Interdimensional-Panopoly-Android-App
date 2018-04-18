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
    
    public void christmas(){
    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*20);
    	// It's christmas so you buy a present for all the players
    }
    
    public void christmas2(){
    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().deposit(i*20);
    	// It's christmas you get a present from all of the players
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
    
    public void coffee(){
    	GameState.getInstance().getCurrentPlayer().withdraw(25);
    	// hero picked you up in his vehicle. yous go for a coffee which cost you 25
    }
    
    public void fan(){
    	GameState.getInstance().getCurrentPlayer().withdraw(15);
    	// You see your favourite hero on the streets, he charges you 15 for a picture
    }
    
    public void underPaid(){
    	int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*15);
    	
    	// Villain rats you out for underpaying for your properties. you must pay an extra 15 for every property owned
    
    }
    
    public void taxFraud(){
    	int i = GameState.getInstance().getCurrentPlayer().getBalance();
    	i = i/15;
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(i);
    	
    	// you're caught for not paying the right amount of tax.
    	
    }
    
//    public void droppingMoney(){
//    	GameState.getInstance().getNextPlayer().withdraw(50);
//    	GameState.getInstance().getCurrentPlayer().deposit(50);
//    	
//    	// the player infront of you dropped 50 euro. you and HERO race for it but you get it first.
//    }
    
    
   public void taxMan(){
	   int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   i = i/10;
	   GameState.getInstance().getCurrentPlayer().withdraw(i);
	   
	  // Villain comes to your house disguised as a taxman and scammed you.
   }
   
   public void interest(){
	   int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   i = i/16;
	   GameState.getInstance().getCurrentPlayer().deposit(i);
	  
	   // you gained interest on your savings from the bank
   }
   
   public void moneyBack(){
	   GameState.getInstance().getCurrentPlayer().deposit(50);
	   // Hero gave you a little present for being a loyal fan
   }
   
   public void jail(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
	   // You hate villain because he is NEGATIVE, NEGATIVE, NEGATIVE. you get sent to jail for assaulting him.
	   
   }
   
   public void jail2(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
	   // you are caught stealing Villains vehicle. you get caught and sent to jail
   }
   
   public void game(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(5);
	   
	   // it's been a long weekend so you and HERO go to play a game
   }
   
   public void game2(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(29);
	   
	   // it's been a long weekend so you and Villain go to play a game
   }
   
   public void freeParking(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(20);
	   
	   // You manage to find free parking before you meet up with HERO.
   }
   
   public void teleporting(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(16);
	   
	   // HERO bring you in his vehicle to a secret location. You's find a teleporting device and decide to try it out
   }
   
   public void houseParty(){
	   int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
	   GameState.getInstance().getCurrentPlayer().withdraw(i*25);
	   
	   // You host a house party in every property you own that week inviting people such as HERO,HERO,HERO,HERO. Some damages were made to all properties.
   }
   
   public void negativeSocialMedia(){
	   Random randomNumber = new Random();
       int i = randomNumber.nextInt(15)+1;

       GameState.getInstance().getCurrentPlayer().move(i);
	   // you receive a lot of backlash for saying that HERO is NEGATIVE, NEGATIVE and NEGATIVE online and are chased on the streets until you find yourself somewhere safe.
   }
   
   public void wallet(){
	   GameState.getInstance().getCurrentPlayer().deposit(130);
	   // you see a villain doing his favourite activity. You manage to rob his wallet whilst he wasn't looking
   }
   
   public void smashedPhone(){
	   GameState.getInstance().getCurrentPlayer().withdraw(150);
	   // Villain smashes your new phone with this weapon of choice. You have to pay 150 to repair it
   }
   
   public void goToInsurance(){
	   GameState.getInstance().getCurrentPlayer().setPlayerLocation(11);
	   
	   // Villain spread rumours you're a tax fraud so you head to the tax office.
   }
   
  public void pawnShop(){
	  GameState.getInstance().getCurrentPlayer().withdraw(80);
	  
	  // you go to a pawn shop and end up seeing HEROS CLOTHING. you end up buying it.
  }

   
    // Possible ideas for chance card //

    // - steal someones property.
    // - Destroy someones house(if they have one).
    // - Steal money from another player
    // - throw dice and pay 10x what you thrown,
}
