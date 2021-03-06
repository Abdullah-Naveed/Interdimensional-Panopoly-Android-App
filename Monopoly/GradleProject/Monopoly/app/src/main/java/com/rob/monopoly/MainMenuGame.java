package com.rob.monopoly;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainMenuGame extends AppCompatActivity {
    private TextView textView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu_game);

        textView = findViewById(R.id.textView);
        textView.setText("2 Players");
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress +=2;
                GameState.getInstance().setNumPlayers(progress);
                textView.setText("");
                textView.setText("" + progress  + " Players");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void startGame(View view){
        Intent startGame = new Intent(MainMenuGame.this,MainActivity.class);
        startGame.putExtra("NumPlayers",Integer.parseInt(textView.getText().toString().substring(0,1)));
        startActivity(startGame);
    }


}
