package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int player = 1;
    int currentPos=9;
    boolean gameActive = true;
    int turnCounter = 0;
    int[] gameState = {0,0,0,0,0,0,0,0,0};
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8}, //rows
                        {0,3,6},{1,4,7},{2,5,8}, //columns
                        {2,4,6},{0,4,8}}; //diagonals

    public void drop(View view){
        ImageView v = (ImageView) view; //gets the current image view which was clicked
        currentPos = Integer.parseInt(v.getTag().toString()); //gets current position

        if(gameState[currentPos]==0 && gameActive) {
            gameState[currentPos] = player; //updates game state
            v.setTranslationY(-1500);
            if (player == 1) {
                v.setImageResource(R.drawable.red);
                player = 2;
            } else if (player == 2) {
                v.setImageResource((R.drawable.yellow));
                player = 1;
            }
            v.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            turnCounter++;
            for (int[] winningPosition : winPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 0) {
                    if (player == 2) {
                        Toast.makeText(this, "RED WINS!", Toast.LENGTH_LONG).show();
                        //Log.i("info", "red wins!");
                    } else if (player == 1) {
                        //Log.i("info", "yellow wins!");
                        Toast.makeText(this, "YELLOW WINS!", Toast.LENGTH_LONG).show();
                    }
                    gameActive=false;
                }
            }
        }
        if(!gameActive || turnCounter>=9){
            TextView text = findViewById(R.id.textView);
            text.setVisibility(View.VISIBLE);
            Button restartButton = findViewById(R.id.button);
            restartButton.setVisibility(View.VISIBLE);
        }
    }

    public void resetGame(View view){
        Button restartButton = findViewById(R.id.button);
        restartButton.setVisibility(View.INVISIBLE);
        TextView text = findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);
        player = 1;
        currentPos = 9;
        gameActive = true;
        turnCounter = 0;
        Arrays.fill(gameState, 0);
        GridLayout grid = findViewById(R.id.gridLayout);
        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView child = (ImageView)grid.getChildAt(i);
            child.setImageDrawable(null);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}