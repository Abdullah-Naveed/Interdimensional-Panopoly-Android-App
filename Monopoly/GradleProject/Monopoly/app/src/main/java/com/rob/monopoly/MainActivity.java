package com.rob.monopoly;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rob.monopoly.NOCList.twitterbotics.KnowledgeBaseModule;
import com.sdsmdg.tastytoast.TastyToast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static ArrayList<Property> properties=new ArrayList<Property>();
    static ArrayList<Player> players=new ArrayList<Player>(0);
    ViewGroup viewGroup=null;
    GameState instance=null;

    public int diceRoll = 0;
    public int diceRoll1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        instance = GameState.getInstance();
        viewGroup = (ViewGroup) findViewById(R.id.tablelayout);
        instance.setViewGroup(viewGroup);
        instance.setContext(this);
        instance.setActivity(this);
        instance.setKnowledgeBaseModule(this);
        initialSetting();
        //gamestate add players, current player and numPlayers

        //add players
        Bundle bundle = getIntent().getExtras();
        int numPlayers = (int) bundle.get("NumPlayers");
//        diceShow(new View(this));
        initialPlayers(numPlayers);
        Random random=new Random();
        instance.setCurrentPlayer(instance.getPlayerWithInt(random.nextInt(numPlayers-1)));

    }
    public void initialPlayers(int numPlayers)
    {
        KnowledgeBaseModule NOC = GameState.getInstance().getKnowledgeBaseModule();
        Vector<String> allPlayers = NOC.getAllFrames();
        String[] playerColours = {"Blue","Red","Green","Pink"};

        for(int i=0;i<numPlayers;i++)
        {
            String player = NOC.selectRandomlyFrom(allPlayers);
            instance.addPlayers((new Player(this,viewGroup,player+" - Player "+i+ " - " + playerColours[i],i)));
            instance.setCurrentPlayer(instance.getPlayerWithInt(i));
            instance.getPlayerWithInt(i).move(0);
        }
    }


    public void initialSetting(){
        //red
        KnowledgeBaseModule NOC=GameState.getInstance().getKnowledgeBaseModule();
        HashMap<String,ArrayList<String>> colourLocations = new LinkedHashMap<String,ArrayList<String>>(NOC.getLocations(this));

        ArrayList<String> groupGenres = new ArrayList<>();

        for(Map.Entry<String, ArrayList<String>> entry: colourLocations.entrySet()){
            groupGenres.add(entry.getKey());
        }

        CompoundView compoundView1 = findViewById(R.id.red1);
        compoundView1.changeImage(1);
        compoundView1.setText(colourLocations.get(groupGenres.get(0)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(0)).toArray()[0].toString(),"red",425,100,85,"red1",21));
        CompoundView compoundView2 = findViewById(R.id.red2);
        compoundView2.changeImage(1);
        compoundView2.setText(colourLocations.get(groupGenres.get(0)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(0)).toArray()[1].toString(),"red",400,95,80,"red2",22));
        CompoundView compoundView3 = findViewById(R.id.red3);
        compoundView3.changeImage(1);
        compoundView3.setText(colourLocations.get(groupGenres.get(0)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(0)).toArray()[2].toString(),"red",375,95,80,"red3",24));

        //yellow
        CompoundView compoundView4 = findViewById(R.id.yellow1);
        compoundView4.changeImage(2);
        compoundView4.setText(colourLocations.get(groupGenres.get(1)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(1)).toArray()[0].toString(),"yellow",475,120,105,"yellow1",26));
        CompoundView compoundView5 = findViewById(R.id.yellow2);
        compoundView5.changeImage(2);
        compoundView5.setText(colourLocations.get(groupGenres.get(1)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(1)).toArray()[1].toString(),"yellow",450,125,100,"yellow2",28));

        //pink
        CompoundView compoundView6 = findViewById(R.id.pink1);
        compoundView6.changeImage(3);
        compoundView6.setText(colourLocations.get(groupGenres.get(2)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(2)).toArray()[0].toString(),"pink",550,140,125,"pink1",30));
        CompoundView compoundView7 = findViewById(R.id.pink2);
        compoundView7.changeImage(3);
        compoundView7.setText(colourLocations.get(groupGenres.get(2)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(2)).toArray()[1].toString(),"pink",525,135,120,"pink2",32));
        CompoundView compoundView8 = findViewById(R.id.pink3);
        compoundView8.changeImage(3);
        compoundView8.setText(colourLocations.get(groupGenres.get(2)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(2)).toArray()[2].toString(),"pink",500,135,120,"pink3",33));

        //green
        CompoundView compoundView9 = findViewById(R.id.green1);
        compoundView9.changeImage(4);
        compoundView9.setText(colourLocations.get(groupGenres.get(3)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(3)).toArray()[0].toString(),"green",350,85,70,"green1",17));
        CompoundView compoundView10 = findViewById(R.id.green2);
        compoundView10.changeImage(4);
        compoundView10.setText(colourLocations.get(groupGenres.get(3)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(3)).toArray()[1].toString(),"green",325,80,65,"green2",18));
        CompoundView compoundView11 = findViewById(R.id.green3);
        compoundView11.changeImage(4);
        compoundView11.setText(colourLocations.get(groupGenres.get(3)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(3)).toArray()[2].toString(),"green",300,80,65,"green3",19));

        //blue
        CompoundView compoundView12 = findViewById(R.id.blue1);
        compoundView12.changeImage(5);
        compoundView12.setText(colourLocations.get(groupGenres.get(4)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(4)).toArray()[0].toString(),"blue",625,170,150,"blue1",37));
        CompoundView compoundView13 = findViewById(R.id.blue2);
        compoundView13.changeImage(5);
        compoundView13.setText(colourLocations.get(groupGenres.get(4)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(4)).toArray()[1].toString(),"blue",600,160,140,"blue2",38));
        CompoundView compoundView14 = findViewById(R.id.blue3);
        compoundView14.changeImage(5);
        compoundView14.setText(colourLocations.get(groupGenres.get(4)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(4)).toArray()[2].toString(),"blue",575,160,140,"blue3",39));

        //grey
        CompoundView compoundView15 = findViewById(R.id.grey1);
        compoundView15.changeImage(6);
        compoundView15.setText(colourLocations.get(groupGenres.get(5)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(5)).toArray()[0].toString(),"grey",275,70,55,"grey1",10));
        CompoundView compoundView16 = findViewById(R.id.grey2);
        compoundView16.changeImage(6);
        compoundView16.setText(colourLocations.get(groupGenres.get(5)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(5)).toArray()[1].toString(),"grey",250,65,50,"grey2",12));
        CompoundView compoundView17 = findViewById(R.id.grey3);
        compoundView17.changeImage(6);
        compoundView17.setText(colourLocations.get(groupGenres.get(5)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(5)).toArray()[2].toString(),"grey",225,65,50,"grey3",13));

        //orange
        CompoundView compoundView18 = findViewById(R.id.orange1);
        compoundView18.changeImage(7);
        compoundView18.setText(colourLocations.get(groupGenres.get(6)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(6)).toArray()[0].toString(),"orange",200,45,35,"orange1",6));
        CompoundView compoundView19 = findViewById(R.id.orange2);
        compoundView19.changeImage(7);
        compoundView19.setText(colourLocations.get(groupGenres.get(6)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(6)).toArray()[1].toString(),"orange",175,40,30,"orange2",8));

        //brown
        CompoundView compoundView20 = findViewById(R.id.brown1);
        compoundView20.changeImage(8);
        compoundView20.setText(colourLocations.get(groupGenres.get(7)).toArray()[0].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(7)).toArray()[0].toString(),"brown",150,35,25,"brown1",1));
        CompoundView compoundView21 = findViewById(R.id.brown2);
        compoundView21.changeImage(8);
        compoundView21.setText(colourLocations.get(groupGenres.get(7)).toArray()[1].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(7)).toArray()[1].toString(),"brown",125,30,20,"brown2",2));
        CompoundView compoundView22 = findViewById(R.id.brown3);
        compoundView22.changeImage(8);
        compoundView22.setText(colourLocations.get(groupGenres.get(7)).toArray()[2].toString());
        GameState.getInstance().addProperties(new Property(colourLocations.get(groupGenres.get(7)).toArray()[2].toString(),"brown",100,30,20,"brown3",4));

        //chance card
        CompoundView compoundView23 = (CompoundView)findViewById(R.id.card1);
        compoundView23.changeImage(9);
        CompoundView compoundView24 = (CompoundView)findViewById(R.id.card2);
        compoundView24.changeImage(9);
        CompoundView compoundView25 = (CompoundView)findViewById(R.id.card3);
        compoundView25.changeImage(9);
        CompoundView compoundView26 = (CompoundView)findViewById(R.id.card4);
        compoundView26.changeImage(9);

        //tax
        CompoundView compoundView35 = (CompoundView)findViewById(R.id.tax1);
        compoundView35.changeImage(10);
        CompoundView compoundView36 = (CompoundView)findViewById(R.id.tax2);
        compoundView36.changeImage(10);

        //game
        CompoundView compoundView31 = (CompoundView)findViewById(R.id.game1);
        compoundView31.changeImage(11);
        CompoundView compoundView32 = (CompoundView)findViewById(R.id.game2);
        compoundView32.changeImage(11);
        CompoundView compoundView33 = (CompoundView)findViewById(R.id.game3);
        compoundView33.changeImage(11);
        CompoundView compoundView34 = (CompoundView)findViewById(R.id.game4);
        compoundView34.changeImage(11);

        //teleport
        CompoundView compoundView27 = (CompoundView)findViewById(R.id.teleport1);
        compoundView27.changeImage(12);
        CompoundView compoundView28 = (CompoundView)findViewById(R.id.teleport2);
        compoundView28.changeImage(12);
        CompoundView compoundView29 = (CompoundView)findViewById(R.id.teleport3);
        compoundView29.changeImage(12);
        CompoundView compoundView30 = (CompoundView)findViewById(R.id.teleport4);
        compoundView30.changeImage(12);

        //corner
        CompoundViewCorners compoundViewCorners1=findViewById(R.id.square1);
        compoundViewCorners1.changeImage(1);
        CompoundViewCorners compoundViewCorners2=findViewById(R.id.square2);
        compoundViewCorners2.changeImage(2);
        CompoundViewCorners compoundViewCorners3=findViewById(R.id.square3);
        compoundViewCorners3.changeImage(3);
        CompoundViewCorners compoundViewCorners4=findViewById(R.id.square4);
        compoundViewCorners4.changeImage(4);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.end_turn) {
            if (GameState.getInstance().getCurrentPlayer().isHasRolled()) {
                GameState.getInstance().getCurrentPlayer().setHasRolled(false);
                if (GameState.getInstance().getCurrentPlayer().getBalance() <= 0) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
                    pDialog.setTitleText("You Cant't End Turn With A Negative Balance");
                    pDialog.show();
                } else {
                    GameState.getInstance().changeToNextPlayer();
                    TastyToast.makeText(getApplicationContext(), "Changed To Next Player", TastyToast.LENGTH_LONG, TastyToast.INFO);
                }
            } else {
                TastyToast.makeText(GameState.getInstance().getContext(), "You Must Roll You Damn Fool!!!", TastyToast.LENGTH_LONG, TastyToast.CONFUSING).show();
            }

        }else if (id == R.id.properties) {
            SweetAlertDialog pDialog=new SweetAlertDialog(GameState.getInstance().getContext(),SweetAlertDialog.NORMAL_TYPE);
            List list=GameState.getInstance().getCurrentPlayer().getProperties();
            Object[] array=list.toArray(new Property[list.size()]);
            Property[] properties=new Property[array.length];
            String[] stringsProp=new String[array.length];
            for(int i=0;i<array.length;i++)
            {
                properties[i]=(Property)array[i];
                stringsProp[i]=properties[i].getID();
            }
            ListView listView = new ListView(GameState.getInstance().getContext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringsProp);
            listView.setAdapter(adapter);
            pDialog.setCustomView(listView);
            pDialog.show();
            pDialog.findViewById(R.id.confirm_button).setVisibility(View.GONE);

        } else if (id == R.id.trade_properties) {

            tradePopup();

        } else if (id == R.id.trade_money_properties) {

            tradeMoneyPopup();

        } else if (id == R.id.bankrupt) {

//            if(GameState.getInstance().getCurrentPlayer().getBalance()<0){
            if (GameState.getInstance().getNumPlayers() >= 2) {
                SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
                pDialog.setCancelButton("I've had a change of heart", sweetAlertDialog -> {
                    pDialog.cancel();
                });

                pDialog.setConfirmButton("Yes take my soul", sweetAlertDialog -> {

                    UserMover userMover = new UserMover(GameState.getInstance().getContext());
                    userMover.wipe(GameState.getInstance().getCurrentPlayer().getPlayerNum(), viewGroup);
                    GameState.getInstance().removePlayer(GameState.getInstance().getCurrentPlayer());
                    TastyToast.makeText(getApplicationContext(), "Changed To Next Player", TastyToast.LENGTH_LONG, TastyToast.INFO);
                    pDialog.cancel();

                    if (GameState.getInstance().getNumPlayers() == 1) {
                        SweetAlertDialog Dialog = new SweetAlertDialog(GameState.getInstance().getContext());
                        Dialog.setTitleText("CONGRATS U WON IT ONLY TOOK US 3 MONTHS OF DEPRESSION TO MAKE THIS FUN GAME FOR YOU");
                        Dialog.show();

                        Dialog.setConfirmButton("Im Sorry?", sweetAlertDialog2 -> {

                            this.finish();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                            int pid = android.os.Process.myPid();
                            android.os.Process.killProcess(pid);

                        });
                    }

                });

                pDialog.setTitle("Are u sure u wanna LOSE hahahahaha");
                pDialog.show();

            } else {

            }
//            else{
//                GameState.getInstance().changeToNextPlayer();
//                Log.i("Player",GameState.getInstance().getCurrentPlayer().getID());
//            }
        }else if(id == R.id.refresh){
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View hView =  navigationView.getHeaderView(0);
            TextView nav_balance = (TextView)hView.findViewById(R.id.balance);
            String bal=Integer.toString(GameState.getInstance().getCurrentPlayer().getBalance());
            nav_balance.setText("Balance: "+bal);
        } else if (id == R.id.roll_1){
            GameState.getInstance().getCurrentPlayer().move(5);
            checkRent(GameState.getInstance().getCurrentPlayer().getPlayerLocation());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkRent(int playerLocation) {
        Property currentProperty=null;
        for(Property property:GameState.getInstance().getProperties())
        {
            if(property.getLocation()==playerLocation)
            {
                currentProperty=property;
            }
        }
        if(currentProperty!=null)
        {
            if(GameState.getInstance().getCurrentPlayer()!=currentProperty.getOwner()&&currentProperty.getOwner()!=null)
            {
                currentProperty.getOwner().deposit(currentProperty.getRentalAmount());
                currentProperty.payRent(GameState.getInstance().getCurrentPlayer());
            TastyToast.makeText(GameState.getInstance().getContext(),"You Have Paid Rent!",TastyToast.LENGTH_LONG,TastyToast.WARNING).show();

            }
        }

    }


    public void alertMovedButton(int moved) {

        new AlertDialog.Builder(MainActivity.this)
                .setMessage("You moved "+moved+" places")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();
    }



    public void alertPersistentDialog(){

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("The Code of a Ninja")
                .setMessage("This is a persistent AlertDialog")
                .setPositiveButton("Show Toast", null) // null to override the onClick
                .setNegativeButton("Dismiss", null)
                .setCancelable(false)
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Toast.makeText(MainActivity.this,"Not Dismissed",Toast.LENGTH_SHORT).show();

                    }
                });


                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // dismiss once everything is ok
                        alertDialog.dismiss();
                    }
                });
            }
        });

        // don't forget to show it
        alertDialog.show();

    }

    public void diceShow(View view) {
        final Animation anim1 = AnimationUtils.loadAnimation(GameState.getInstance().getContext(), R.anim.shake);
        final Animation anim2 = AnimationUtils.loadAnimation(GameState.getInstance().getContext(), R.anim.shake);
        ImageView imageView1 = findViewById(R.id.dice);
        ImageView imageView2 = findViewById(R.id.dice2);

        if (!GameState.getInstance().getCurrentPlayer().isHasRolled()) {

            if (!GameState.getInstance().getCurrentPlayer().isInJail()) {

                Random RANDOM = new Random();
                diceRoll = RANDOM.nextInt(6) + 1;
                diceRoll1 = RANDOM.nextInt(6) + 1;

                int i = diceRoll + diceRoll1;

                TastyToast.makeText(GameState.getInstance().getContext(),"You Have Moved " + i + " Spaces.",TastyToast.LENGTH_LONG,TastyToast.SUCCESS).show();


                try {
                    final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (animation == anim1) {
                                int res = getResources().getIdentifier("dice_" + diceRoll, "drawable", "com.rob.monopoly");
                                imageView1.setImageResource(res);
                            } else if (animation == anim2) {
                                int res = getResources().getIdentifier("dice_" + diceRoll1, "drawable", "com.rob.monopoly");
                                imageView2.setImageResource(res);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    };

                    anim1.setAnimationListener(animationListener);
                    anim2.setAnimationListener(animationListener);

                    imageView1.startAnimation(anim1);
                    imageView2.startAnimation(anim2);

                    }catch(NullPointerException e)
                    {

                    }

                    GameState.getInstance().getCurrentPlayer().move(i);
                    System.out.println(GameState.getInstance().getCurrentPlayer().getPlayerLocation());
                    checkRent(GameState.getInstance().getCurrentPlayer().getPlayerLocation());

                } else{

                    Random RANDOM = new Random();
                    diceRoll = RANDOM.nextInt(6) + 1;
                    diceRoll1 = RANDOM.nextInt(6) + 1;
                    int i = diceRoll + diceRoll1;

                    try {
                        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (animation == anim1) {
                                    int res = getResources().getIdentifier("dice_" + diceRoll, "drawable", "com.rob.monopoly");
                                    imageView1.setImageResource(res);
                                } else if (animation == anim2) {
                                    int res = getResources().getIdentifier("dice_" + diceRoll1, "drawable", "com.rob.monopoly");
                                    imageView2.setImageResource(res);
                                }
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        };

                        anim1.setAnimationListener(animationListener);
                        anim2.setAnimationListener(animationListener);

                        imageView1.startAnimation(anim1);
                        imageView2.startAnimation(anim2);

                    }catch(NullPointerException e)
                    {

                    }


                    if (diceRoll == diceRoll1) {

                        GameState.getInstance().getCurrentPlayer().move(i);
                        System.out.println(GameState.getInstance().getCurrentPlayer().getPlayerLocation());
                        checkRent(GameState.getInstance().getCurrentPlayer().getPlayerLocation());
                        GameState.getInstance().getCurrentPlayer().setInJail(false);

                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
                        pDialog.setTitleText("You didn't roll a double, pay 50 euro if you want to get out of Jail");
                        pDialog.show();

                        pDialog.setCancelButton("No I don't want pay", sweetAlertDialog -> {
                            pDialog.cancel();
                        });

                        pDialog.setConfirmButton("Yes here you go", sweetAlertDialog -> {

                            GameState.getInstance().getCurrentPlayer().withdraw(50);
                            GameState.getInstance().getCurrentPlayer().setInJail(false);
                            TastyToast.makeText(getApplicationContext(), "Thank You.You are free to leave on your next turn", TastyToast.LENGTH_LONG, TastyToast.INFO);

                            pDialog.cancel();
                        });

                    }

                }

                GameState.getInstance().getCurrentPlayer().setHasRolled(true);
            } else {
                TastyToast.makeText(getApplicationContext(), "Sorry BRAH you have already rolled unlucky", TastyToast.LENGTH_LONG, TastyToast.INFO);
            }


        }

    public void PopupCustomizedLayout(View view) {

        View parent = (View) view.getParent();
        String viewName = GameState.getInstance().getContext().getResources().getResourceEntryName(parent.getId());

        Property currentProperty = null;

        for (Property prop : GameState.getInstance().getProperties()) {
            if (viewName.equals(prop.getCompoundViewID())) {
                currentProperty = prop;
//                System.out.println(currentProperty.getLocation());
            }
        }

        if(currentProperty==null) { return; }

        if (currentProperty.getOwner() == null && GameState.getInstance().getCurrentPlayer().getPlayerLocation() == currentProperty.getLocation()) {
//            //call buy popup
            buyPopUp(GameState.getInstance().getContext(), currentProperty);
        } else if (currentProperty.getOwner() == GameState.getInstance().getCurrentPlayer()) {
            buildMortgagePopUp(GameState.getInstance().getContext(), currentProperty);
        } else
        {
//            //call ok popup
            okPopUp(GameState.getInstance().getCurrentPlayer(),currentProperty);
        }
    }


    public void buyPopUp(Context context,Property property)
    {
        SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
        ListView listView = new ListView(GameState.getInstance().getContext());

        String[] values = new String[] {
                "Property Name: "+property.getID(),
                "Buy Price: "+property.buyPrice(),
                "Rent Price: "+property.getRentalAmount(),
                "Number of Houses: "+property.getNumHouses(),
                "Owner: No Owner"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        pDialog.setCustomView(listView);

        pDialog.setConfirmText("Buy");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                if(GameState.getInstance().getCurrentPlayer().getBalance()>=property.buyPrice())
                {
                    property.setOwner(GameState.getInstance().getCurrentPlayer());
                    GameState.getInstance().getCurrentPlayer().withdraw(property.buyPrice());
                    GameState.getInstance().getCurrentPlayer().addToProperties(property);
                }
                pDialog.cancel();
            }
        });
        pDialog.show();
    }


    public void buildMortgagePopUp(Context context,Property property)
    {
        SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
        ListView listView = new ListView(GameState.getInstance().getContext());

        String[] values = new String[] {
                "Property Name: "+property.getID(),
                "Buy Price: "+property.buyPrice(),
                "Rent Price: "+property.getRentalAmount(),
                "Number of Houses: "+property.getNumHouses(),
                "Owner: "+property.getOwner()
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        pDialog.setCustomView(listView);
        pDialog.setConfirmText("Build");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                if(GameState.getInstance().getCurrentPlayer().getBalance()>=property.getHousePrice()&&GameState.getInstance().getCurrentPlayer().isGroupOwner(property))
                {
                    GameState.getInstance().getCurrentPlayer().withdraw(property.getHousePrice());
                    property.buildHouse();
                }
                pDialog.cancel();
            }
        });

        pDialog.setCancelText("Mortgage");
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {

                for(int i=0;i<property.getNumHouses();i++)
                {
                    GameState.getInstance().getCurrentPlayer().deposit(property.getHousePrice()/2);
                    property.demolishHouse();
                }
                property.mortgageProperty();
                GameState.getInstance().getCurrentPlayer().deposit(property.getMortgageAmount());
                GameState.getInstance().getCurrentPlayer().removeFromProperties(property);

                pDialog.cancel();
            }
        });
        pDialog.show();
    }


    public void okPopUp(Context context,Property property)
    {
        SweetAlertDialog pDialog = new SweetAlertDialog(GameState.getInstance().getContext());
        ListView listView = new ListView(GameState.getInstance().getContext());
        String owner=null;
        if(property.getOwner()==null)
        {
            owner="No Owner";
        }
        else
        {
            owner=property.getOwner().getID();
        }
        String[] values = new String[] {
                "Property Name: "+property.getID(),
                "Buy Price: "+property.buyPrice(),
                "Rent Price: "+property.getRentalAmount(),
                "Number of Houses: "+property.getNumHouses(),
                "Owner: "+owner
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);


        pDialog.setCustomView(listView);
        pDialog.show();
    }

    public void tradePopup()
    {
        TradePopUp tradePopUp=new TradePopUp();
        tradePopUp.tradePopup();
    }

    public void tradeMoneyPopup()
    {
        TradeMoneyPopUp tradePopUp=new TradeMoneyPopUp();
        tradePopUp.tradeMoneyPopup();
    }

    public static void miniGame(Activity activity)
    {
        Intent startGame = new Intent(GameState.getInstance().getContext(), GuessTheFictionalWorld.class);
        activity.startActivity(startGame);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}

