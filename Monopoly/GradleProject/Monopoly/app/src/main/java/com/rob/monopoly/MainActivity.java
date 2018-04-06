package com.rob.monopoly;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static ArrayList<Property> properties=new ArrayList<Property>();
    static ArrayList<Player> players=new ArrayList<Player>();
    ViewGroup viewGroup=null;
//    public static final Random RANDOM = new Random();
//    private ImageView imageView1, imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initialSetting();
        viewGroup=(ViewGroup) findViewById(R.id.tablelayout);

        //add players
        players.add(new Player(viewGroup,"Rob"));
//
//        imageView1 = (ImageView) findViewById(R.id.dice1);
//        imageView2 = (ImageView) findViewById(R.id.dice2);
//        diceShow();

    }

    public void initialSetting(){
        //red
        CompoundView compoundView1 = (CompoundView)findViewById(R.id.red1);
        compoundView1.changeImage(1);
        compoundView1.setText("TEST");
        new Property("test","red",100,100,100);
        CompoundView compoundView2 = (CompoundView)findViewById(R.id.red2);
        compoundView2.changeImage(1);
        CompoundView compoundView3 = (CompoundView)findViewById(R.id.red3);
        compoundView3.changeImage(1);

        //yellow
        CompoundView compoundView4 = (CompoundView)findViewById(R.id.yellow1);
        compoundView4.changeImage(2);
        CompoundView compoundView5 = (CompoundView)findViewById(R.id.yellow2);
        compoundView5.changeImage(2);

        //pink
        CompoundView compoundView6 = (CompoundView)findViewById(R.id.pink1);
        compoundView6.changeImage(3);
        CompoundView compoundView7 = (CompoundView)findViewById(R.id.pink2);
        compoundView7.changeImage(3);
        CompoundView compoundView8 = (CompoundView)findViewById(R.id.pink3);
        compoundView8.changeImage(3);

        //green
        CompoundView compoundView9 = (CompoundView)findViewById(R.id.green1);
        compoundView9.changeImage(4);
        CompoundView compoundView10 = (CompoundView)findViewById(R.id.green2);
        compoundView10.changeImage(4);
        CompoundView compoundView11 = (CompoundView)findViewById(R.id.green3);
        compoundView11.changeImage(4);

        //blue
        CompoundView compoundView12 = (CompoundView)findViewById(R.id.blue1);
        compoundView12.changeImage(5);
        CompoundView compoundView13 = (CompoundView)findViewById(R.id.blue2);
        compoundView13.changeImage(5);
        CompoundView compoundView14 = (CompoundView)findViewById(R.id.blue3);
        compoundView14.changeImage(5);

        //grey
        CompoundView compoundView15 = (CompoundView)findViewById(R.id.grey1);
        compoundView15.changeImage(6);
        CompoundView compoundView16 = (CompoundView)findViewById(R.id.grey2);
        compoundView16.changeImage(6);
        CompoundView compoundView17 = (CompoundView)findViewById(R.id.grey3);
        compoundView17.changeImage(6);

        //orange
        CompoundView compoundView18 = (CompoundView)findViewById(R.id.orange1);
        compoundView18.changeImage(7);
        CompoundView compoundView19 = (CompoundView)findViewById(R.id.orange2);
        compoundView19.changeImage(7);

        //brown
        CompoundView compoundView20 = (CompoundView)findViewById(R.id.brown1);
        compoundView20.changeImage(8);
        CompoundView compoundView21 = (CompoundView)findViewById(R.id.brown2);
        compoundView21.changeImage(8);
        CompoundView compoundView22 = (CompoundView)findViewById(R.id.brown3);
        compoundView22.changeImage(8);

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

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.roll) {
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
//            alertOneButton();

            Random RANDOM=new Random();
            int i=RANDOM.nextInt(6) + 1;
            for(int j=0;j<i;j++)
            {
                players.get(0).move();
            }


        } else if (id == R.id.nav_gallery) {
            ViewGroup viewGroup=(ViewGroup) findViewById(R.id.tablelayout);
            Player rob=new Player(viewGroup,"ROB");
            rob.move();

        } else if (id == R.id.nav_slideshow) {
            ViewGroup viewGroup=(ViewGroup) findViewById(R.id.tablelayout);
            Player rob=new Player(viewGroup,"ROB");
            rob.move();

        } else if (id == R.id.nav_manage) {
            ViewGroup viewGroup=(ViewGroup) findViewById(R.id.tablelayout);
            Player rob=new Player(viewGroup,"ROB");
            rob.move();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*
    * AlertDialog with one action button.
    */
    public void alertOneButton() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("One Button")
                .setMessage("Thanks for visiting The Code of a Ninja - codeofaninja.com")
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


//    public void test(View v)
//    {
//        Log.v("fuck","this");
//        Toast.makeText(MainActivity.this,"worked",Toast.LENGTH_SHORT).show();
////        Intent intent=new Intent(this,Main2Activity.class);
////        startActivity(intent);
//    }


    public void PopupCustomizedLayout(View view)
    {
//        TextView textView=findViewById(R.id.customview_textview_title);
//        Property property=new Property("rob","red",100,100,100);
//        property.PopupCustomizedLayout(view);

//        arraylist of all properties
//        intial setting of noc list name and adding property to arraylist
//        get textview name check if equals name in properties
//        then using that property change the values for sweetalert


        SweetAlertDialog pDialog=new SweetAlertDialog(this);
        //pDialog.setTitleText("test");
        ListView listView=new ListView(this);
        Player owner=properties.get(0).getOwner();
        String ownerStr=null;
        if(owner==null){ownerStr="No Owner";}else{ownerStr=owner.getID();}
        String[] values = new String[] {
                "Property Name: "+properties.get(0).getID(),
                "Buy Price: "+properties.get(0).buyPrice(),
                "Rent Price: "+properties.get(0).getRentalAmount(),
                "Owner: "+ownerStr,
                "Colour Group: "+properties.get(0).getColourGroup()
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);


        pDialog.setCustomView(listView);
        pDialog.show();

    }



//    public void diceShow(){
//        final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
//        final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
//        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                int value = randomDiceValue();
//                int res = getResources().getIdentifier("dice_" + value, "drawable", "com.abdullahnaveed.diceanimation");
//
//                if (animation == anim1) {
//                    imageView1.setImageResource(res);
//                } else if (animation == anim2) {
//                    imageView2.setImageResource(res);
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        };
//
//        anim1.setAnimationListener(animationListener);
//        anim2.setAnimationListener(animationListener);
//
//        imageView1.startAnimation(anim1);
//        imageView2.startAnimation(anim2);
//    }
//
//    public static int randomDiceValue() {
//        return RANDOM.nextInt(6) + 1;
//    }




}

