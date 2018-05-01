package com.rob.monopoly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.rob.monopoly.NOCList.twitterbotics.KnowledgeBaseModule;
import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class GuessTheFictionalWorld extends AppCompatActivity {

    String aFictionalWorld = "";
    String bFictionalWorld = "";
    String cFictionalWorld = "";
    String dFictionalWorld = "";
    String aCharacter = "";
    ArrayList<String> answers = new ArrayList<String>();
    String answer="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_fictional_world);
        Random rand = new Random();
        KnowledgeBaseModule NOC = GameState.getInstance().getKnowledgeBaseModule();
        Vector<String> fictionalWorlds = NOC.getFictionalWorlds(GameState.getInstance().getContext());

        answers.add(aFictionalWorld = NOC.selectRandomlyFrom(fictionalWorlds));
        answers.add(bFictionalWorld = NOC.selectRandomlyFrom(fictionalWorlds));
        answers.add(cFictionalWorld = NOC.selectRandomlyFrom(fictionalWorlds));
        answers.add(dFictionalWorld = NOC.selectRandomlyFrom(fictionalWorlds));
        Collections.shuffle(answers);

        Vector<String> character;
        int j=0;
        do {
            character = NOC.getAllKeysWithFieldValue("Fictional World", answers.get(j));
            answer=answers.get(j);
            j++;
        }
        while(character.size()==0);
        if(character==null)

        Collections.shuffle(answers);
        TextView nocTextView = findViewById(R.id.nocTextView);

        int i=rand.nextInt(character.size())+1;
        aCharacter = character.elementAt(i-1);
        nocTextView.setText("What Fictional World Is " + aCharacter + " From?");

        Button buttonA = findViewById(R.id.button0);
        buttonA.setText(answers.get(0).toString());
        Button buttonB = findViewById(R.id.button1);
        buttonB.setText(answers.get(1).toString());
        Button buttonC = findViewById(R.id.button2);
        buttonC.setText(answers.get(2).toString());
        Button buttonD = findViewById(R.id.button3);
        buttonD.setText(answers.get(3).toString());

    }

    public void chooseAnswer(View view){

        switch(view.getId()){

            case R.id.button0:

                if(answers.get(0).equals(answer)){
                    GameState.getInstance().getCurrentPlayer().deposit(50);
                    TastyToast.makeText(this, "Fairplay, you're not as dumb as you look!! Here is 50 euro",TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                }else{
                    GameState.getInstance().getCurrentPlayer().withdraw(50);
                    TastyToast.makeText(this, "YOU HAM!!! Give Me 50 euro",TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }

                break;
            case R.id.button1:

                if(answers.get(1).equals(answer)){
                    GameState.getInstance().getCurrentPlayer().deposit(50);
                    TastyToast.makeText(this, "Fairplay, you're not as dumb as you look!! Here is 50 euro",TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                }else{
                    GameState.getInstance().getCurrentPlayer().withdraw(50);
                    TastyToast.makeText(this, "YOU HAM!!! Give Me 50 euro",TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }

                break;
            case R.id.button2:

                if(answers.get(2).equals(answer)){
                    GameState.getInstance().getCurrentPlayer().deposit(50);
                    TastyToast.makeText(this, "Fairplay, you're not as dumb as you look!! Here is 50 euro",TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                }else{
                    GameState.getInstance().getCurrentPlayer().withdraw(50);
                    TastyToast.makeText(this, "YOU HAM!!! Give Me 50 euro",TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }

                break;
            case R.id.button3:

                if(answers.get(3).equals(answer)){
                    GameState.getInstance().getCurrentPlayer().deposit(50);
                    TastyToast.makeText(this, "Fairplay, you're not as dumb as you look!! Here is 50 euro",TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                }else{
                    GameState.getInstance().getCurrentPlayer().withdraw(50);
                    TastyToast.makeText(this, "YOU HAM!!! Give Me 50 euro",TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }

                break;

        }
        finish();

    }

    @Override
    public void onBackPressed()
    {
        
    }

}
