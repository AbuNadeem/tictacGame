package com.abunadeem.tictactoe;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
public class game extends AppCompatActivity {
    public int winner = -1;
    TextView scoresP2, scoresP1;
    boolean easyMode,mediumMode,hardmode,imposMode;
    int scorep1 = 0;
    int scorep2 = 0;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    //current player
    int currentPlayer = 1;
    //list of buttons which selected by first player
    ArrayList<Integer> player1 = new ArrayList<>();
    //list of buttons which selected by second player
    ArrayList<Integer> player2 = new ArrayList<>();
    //list of all selected buttons
    ArrayList<Integer> played = new ArrayList<>();

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        scoresP1 = findViewById(R.id.score1);
        scoresP2 = findViewById(R.id.score2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.levels, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.offlinepvp:
                {easyMode=false;mediumMode=false;
                hardmode=false;imposMode=false;}
                return true;
            case R.id.Easy:
                {easyMode=true;mediumMode=false;
                hardmode=false;imposMode=false;}
                return true;
            case R.id.med:
                {easyMode=false;mediumMode=true;
                hardmode=false;imposMode=false;}
                return true;
            case R.id.hard:
                {easyMode=false;mediumMode=false;
                hardmode=true;imposMode=false;}
                return true;
            case R.id.impo:
                {easyMode=false;mediumMode=false;
                hardmode=true;imposMode=true;}
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void start(View view) {

        int number = 0;
        int butId = view.getId();
// give every button a uniqe number
        switch (butId) {
            case R.id.b1: number = 1;break;
            case R.id.b3: number = 3;break;
            case R.id.b2: number = 2;break;
            case R.id.b4: number = 4;break;
            case R.id.b5: number = 5;break;
            case R.id.b6: number = 6;break;
            case R.id.b7: number = 7;break;
            case R.id.b8: number = 8;break;
            case R.id.b9: number = 9;break;
        }
        //lunch the game for the selected button
        playgame(number, (Button) view);
    }

    private void playgame(int number, Button selectedButton) {

        if (currentPlayer == 1) {
            //change the properties for the selected button
            selectedButton.setText("X");
            selectedButton.setTextSize(40);
            selectedButton.setBackgroundResource(R.color.gold);
            selectedButton.setClickable(false);
            selectedButton.setTextColor(Color.WHITE);
         //add the button to player 1 list and as a played button
            player1.add(number);
            played.add(number);

            checkwinner();
           //now player 2 return
            currentPlayer = 2;
            if (easyMode && played.size() < 8 && winner == -1) AutoPlay();
            if (mediumMode && played.size() < 8 && winner == -1) mediumMode();
            if (hardmode && played.size() < 8 && winner == -1) HardMode();


        } else {
            //same steps for player1
            selectedButton.setText("O");
            selectedButton.setTextSize(40);
            selectedButton.setBackgroundColor(Color.rgb(100, 150, 220));
            selectedButton.setTextColor(Color.WHITE);
            selectedButton.setClickable(false);
            player2.add(number);
            played.add(number);
            checkwinner();
            currentPlayer = 1;
        }
    }

    private void checkwinner() {
        int[][] win= {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
        for (int[] aWin : win) {
            if (player1.contains(aWin[0]) && player1.contains(aWin[1]) && player1.contains(aWin[2]))
                winner = 1;
            if (player2.contains(aWin[0]) && player2.contains(aWin[1]) && player2.contains(aWin[2]))
                winner = 2;

        }

            if (winner != -1)
            if (winner == 1) {

                scorep1++;
                AlertDialog.Builder alert = new AlertDialog.Builder(this, 5);
                alert.setTitle(getResources().getString(R.string.cong));
                alert.setMessage(getResources().getString(R.string.cong1));
                alert.setCancelable(true);
                alert.setIcon(R.drawable.ic_action_emo_wink);

                alert.show();
                scoresP1.setText(String.valueOf(scorep1));
                stop();
            } else {

                scorep2++;
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(this, 5);
                alert.setTitle(getResources().getString(R.string.sorry));
                alert.setMessage(getResources().getString(R.string.sorry1));
                alert.setCancelable(true);
                alert.setIcon(R.drawable.ic_action_name);

                alert.show();
                scoresP2.setText(String.valueOf(scorep2));
                stop();

            }


    }



    public void AutoPlay() {
        Button[] btn = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        if (!played.contains(5)) {

            b5.setText("O");
            b5.setTextSize(40);
            b5.setBackgroundColor(Color.rgb(100, 150, 220));
            b5.setTextColor(Color.WHITE);
            b5.setClickable(false);
            player2.add(5);
            played.add(5);
            checkwinner();
            currentPlayer = 1;

        } else {

            int random;
            do random = (int) (Math.random() * 8 + 1);
            while (played.contains(random));


            btn[random - 1].setText("O");
            btn[random - 1].setTextSize(40);
            btn[random - 1].setBackgroundColor(Color.rgb(100, 150, 220));
            btn[random - 1].setTextColor(Color.WHITE);
            btn[random - 1].setClickable(false);
            player2.add(random);
            played.add(random);
            checkwinner();
            currentPlayer = 1;
        }

    }

    public void reset(View view) {
        Button[] btn = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for (Button c : btn) {

            c.setClickable(true);
            c.setText("");
            c.setBackgroundResource(R.color.bg);
        }

        player1.clear();
        player2.clear();
        played.clear();
        currentPlayer = 1;
        winner = -1;
    }

    public void stop() {
        Button[] btn = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for (Button c : btn) {

            c.setClickable(false);
        }

    }

    public void clear(View view) {
        scorep1 = 0;
        scorep2 = 0;
        scoresP1.setText(String.valueOf(scorep1));
        scoresP2.setText(String.valueOf(scorep2));
        reset(view);

    }


    public void HardMode() {

        int[][] choises = {{1, 2}, {2, 3}, {3, 1}, {4, 5}, {5, 6}, {6, 4}, {7, 8}, {8, 9}, {9, 7},
                {1, 4}, {1, 7}, {4, 7}, {2, 5}, {2, 8}, {5, 8}, {3, 6}, {3, 9}, {6, 9},
                {1, 5}, {1, 9}, {5, 9}, {7, 5}, {7, 3}, {5, 3}};
        int[] solveId = {3, 1, 2, 6, 4, 5, 9, 7, 8, 7, 4, 1, 8, 5, 2, 9, 6, 3, 9, 5, 1, 3, 5, 7};
        Button[] solveButton = {b3, b1, b2, b6, b4, b5, b9, b7, b8, b7, b4, b1, b8, b5
                , b2, b9, b6, b3, b9, b5, b1, b3, b5, b7}; int i;

// FIRST STEP : select first button
            Button button = b3;
            int number = 3;
            if(!player1.contains(5)){
                button=b5;
                number=5;
                 }

//////// IMPOSSIPLE LEVEL PART /////////////////////////
        int[][] choises1 = {{2, 6}, {6, 8}, {8, 4}, {4, 2},{5,7}};
        int[] solveit = {3, 9, 7, 1,9};
        Button[] solve2button = {b3, b9, b7, b1,b9};

        if(imposMode) {

            for (i = 0; i < choises1.length; i++) {

                if (player1.contains(choises1[i][0]) && player1.contains(choises1[i][1])
                        && !played.contains(solveit[i])) {
                    number = solveit[i];
                    button = solve2button[i];
                }
            }
        }
   //////////////THE END OF IMPOSSIBLE PART   /////////////////

            for (i = 0; i < choises.length; i++) {

                if (player1.contains(choises[i][0]) && player1.contains(choises[i][1])
                        && !played.contains(solveId[i])) {
                    number = solveId[i];
                    button = solveButton[i];
                }

            }
            for (i = 0; i < choises.length; i++) {

                if (player2.contains(choises[i][0]) && player2.contains(choises[i][1])
                        && !played.contains(solveId[i])) {
                    number = solveId[i];
                    button = solveButton[i];
                }

            }


            if (!played.contains(number)) playgame(number, button);
            else AutoPlay();
        }

////
public void mediumMode(){
    int[][] choises = {{1, 2}, {2, 3}, {3, 1}, {4, 5}, {5, 6}, {6, 4}, {7, 8}, {8, 9}, {9, 7},
            {1, 4}, {1, 7}, {4, 7}, {2, 5}, {2, 8}, {5, 8}, {3, 6}, {3, 9}, {6, 9},
            {1, 5}, {1, 9}, {5, 9}, {7, 5}, {7, 3}, {5, 3}};
    int[] solveId = {3, 1, 2, 6, 4, 5, 9, 7, 8, 7, 4, 1, 8, 5, 2, 9, 6, 3, 9, 5, 1, 3, 5, 7};
    Button[] solveButton = {b3, b1, b2, b6, b4, b5, b9, b7, b8, b7, b4, b1, b8, b5
            , b2, b9, b6, b3, b9, b5, b1, b3, b5, b7}; int i;
    Button button = b3;
    int number = 3;
    if(!player1.contains(5)){
        button=b5;
        number=5;
    }

    for (i = 0; i < choises.length; i++) {

        if (player1.contains(choises[i][0]) && player1.contains(choises[i][1])
                || player2.contains(choises[i][0]) && player2.contains(choises[i][1]))
        {
            number = solveId[i];
            button = solveButton[i];
        }


    }  if (!played.contains(number)) playgame(number, button);
    else AutoPlay();
}


    }


