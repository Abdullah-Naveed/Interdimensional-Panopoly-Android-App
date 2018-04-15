package com.rob.monopoly;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GameState {

        private static GameState instance;



        private Player currentPlayer;
        private int player;
        private int numPlayers;
        private int[] boardPlayers;
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
            return numPlayers;
        }

        public void setNumPlayers(int numPlayers) {
            this.numPlayers = numPlayers;
        }

        public int[] getBoardPlayers() {
            return boardPlayers;
        }

        public void setBoardPlayers(int[] boardPlayers) {
            this.boardPlayers = boardPlayers;
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
