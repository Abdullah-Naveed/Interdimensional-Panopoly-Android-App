package com.rob.monopoly;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

public class GameState {

        private static GameState instance;



        private Player currentPlayer;
        private int player;
        private int numPlayers;
        private ArrayList<Property> properties=new ArrayList<Property>();
        private ArrayList<Player> players=new ArrayList<Player>();
        private ArrayList<Card> cards=new ArrayList<Card>();
        private ViewGroup viewGroup;
        private Context context;

        private GameState(){}

        //static block initialization for exception handling
        static{
            try{
                instance = new GameState();
            }catch(Exception e){
                throw new RuntimeException("Exception occured in creating singleton instance");
            }
        }

        public static GameState getInstance(){
            return instance;
        }

        public Player getPlayerWithInt(int i) { return this.players.get(i); }

        public Player getCurrentPlayer() {
            return currentPlayer;
        }

        public void setCurrentPlayer(Player currentPlayer) {
            this.currentPlayer = currentPlayer;
        }

        public int getPlayer() {
            return player;
        }

        public void setPlayer(int player) {
            this.player = player;
        }

        public int getNumPlayers() {
            return players.size();
        }

        public void setNumPlayers(int numPlayers) {
            this.numPlayers = numPlayers;
        }

        public void changeToNextPlayer(){
            currentPlayer = getNextPlayer(currentPlayer);
        }

        public void removePlayer(Player currentPlayer) {
            this.players.get(players.indexOf(currentPlayer)).setPlayerWasRemoved(true);
            this.players.remove(currentPlayer);
        }

        public Player getNextPlayer(Player currentPlayer) {
            int i=players.indexOf(currentPlayer);
            return this.players.get((i+1)%players.size());
        }

        public ArrayList<Property> getProperties() {
            return properties;
        }

        public void setProperties(ArrayList<Property> properties) {
            this.properties = properties;
        }

        public void addProperties(Property property) {
            this.properties.add(property);
        }

        public Property setPropertyOwner(String propertyID,Player player) {
            for(Property property:properties)
            {
                if(property.getID().equals(propertyID))
                {
                    property.setOwner(player);
                }
            }
            return null;
        }

        public ArrayList<Player> getPlayers() {
            return players;
        }

        public void setPlayers(ArrayList<Player> players) {
            this.players = players;
        }

        public void addPlayers(Player player){
            this.players.add(player);
        }

        public ViewGroup getViewGroup() {
            return viewGroup;
        }

        public void setViewGroup(ViewGroup viewGroup) {
            this.viewGroup = viewGroup;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public ArrayList<Card> getCards() { return cards; }

        public void setCards(ArrayList<Card> cards) { this.cards = cards; }

        public void addCards(Card card) { this.cards.add(card); }

}
