package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //0: Yellow, 1: Red, 2: Empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPosition = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int activePlayer = 0;
    boolean gameActive = true;
    public void dropIn (View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1500).setDuration(200);

            for (int[] winningPosition : winningPosition){

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    //Someone has won
                    String winner;
                    if (activePlayer == 1){

                        winner = "Yellow";
                    } else {

                        winner = "Red";
                    }

                    Button playAgainbtn = findViewById(R.id.againplaybtn);
                    TextView winnerTextview = findViewById(R.id.textviewagainid);
                    String winnerName = winner + getString(R.string.has_won);
                    winnerTextview.setText(winnerName);

                    playAgainbtn.setVisibility(View.VISIBLE);
                    winnerTextview.setVisibility(View.VISIBLE);
                }
            }
        }

        if (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2 &&
        gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2){

            Button playAgainbtn = findViewById(R.id.againplaybtn);
            TextView winnerTextview = findViewById(R.id.textviewagainid);

            winnerTextview.setText( R.string.match_drawn);

            playAgainbtn.setVisibility(View.VISIBLE);
            winnerTextview.setVisibility(View.VISIBLE);
        }
    }

    public void PlayAgain (View view){

        Button playAgainbtn = findViewById(R.id.againplaybtn);
        TextView winnerTextview = findViewById(R.id.textviewagainid);

        playAgainbtn.setVisibility(View.INVISIBLE);
        winnerTextview.setVisibility(View.INVISIBLE);

        GridLayout myGridLayout = findViewById(R.id.gridlauoutid);

        for (int i = 0; i<myGridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) myGridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
