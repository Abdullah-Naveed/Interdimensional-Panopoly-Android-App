package com.rob.monopoly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GuessTheCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_character);

        int maxLettersInCharacterName = 6;
        LinearLayout m_ll = findViewById(R.id.llMain);
        for(int i=0;i<maxLettersInCharacterName;i++)
        {
            TextView text = new TextView(this);
            text.setText("__"+'\t');
            text.setTextSize(25);
            m_ll.addView(text);
        }


    }

}
