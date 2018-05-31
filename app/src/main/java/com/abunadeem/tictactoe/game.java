package com.abunadeem.tictactoe;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class game extends AppCompatActivity {
    public int winner = -1;
    TextView scoresP2, scoresP1;
    boolean autoplay;
    boolean hardmode;
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
    private Switch easyModeSwitcher, hardModeSwitcher;

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
        easyModeSwitcher = findViewById(R.id.switch1);
        hardModeSwitcher = findViewById(R.id.switch2);
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
       //make sure if we have any autoplay
        autoplay = easyModeSwitcher.isChecked();
        hardmode = hardModeSwitcher.isChecked();
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
            //
            if (hardmode && played.size() < 8 && winner == -1) HardMode();

            if (autoplay && played.size() < 8 && winner == -1) AutoPlay();


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

        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner = 1;
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner = 1;
        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner = 1;
        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner = 1;
        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner = 1;
        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner = 1;
        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1;
        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner = 1;


        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner = 2;
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner = 2;
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner = 2;
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner = 2;
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner = 2;
        if (player2.contains(9) && player2.contains(6) && player2.contains(3))
            winner = 2;
        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner = 2;
        if (player2.contains(7) && player2.contains(5) && player2.contains(3))
            winner = 2;


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


   /*     int[][] win= {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};


        for (int[] aWin : win) {

            for (int j : aWin) {
                if (player1.contains(aWin[j])) {
                        Toast.makeText(this, "p1", Toast.LENGTH_SHORT).show();
                }
                if (player2.contains(aWin[j])) {
                    if (player2.contains(aWin[1]) && player2.contains(aWin[2]) && player2.contains(aWin[0]))
                        Toast.makeText(this, "p2", Toast.LENGTH_SHORT).show();
                }
            }

        }
*/

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

    }


    public void HardMode() {
        int[][] choises = {{1, 2}, {2, 3}, {3, 1}, {4, 5}, {5, 6}, {6, 4}, {7, 8}, {8, 9}, {9, 7},
                {1, 4}, {1, 7}, {4, 7}, {2, 5}, {2, 8}, {5, 8}, {3, 6}, {3, 9}, {6, 9},
                {1, 5}, {1, 9}, {5, 9}, {7, 5}, {7, 3}, {5, 3}};
        int[] solveId = {3, 1, 2, 6, 4, 5, 9, 7, 8, 7, 4, 1, 8, 5, 2, 9, 6, 3, 9, 5, 1, 3, 5, 7};
        Button[] solveButton = {b3, b1, b2, b6, b4, b5, b9, b7, b8, b7, b4, b1, b8, b5
                , b2, b9, b6, b3, b9, b5, b1, b3, b5, b7};
        easyModeSwitcher.setChecked(false);
        int i;

        if (player1.contains(5)) {
            Button button = b3;
            int number = 3;
            for (i = 0; i < choises.length; i++) {

                if (player2.contains(choises[i][0]) && player2.contains(choises[i][1]) ||
                        player1.contains(choises[i][0]) && player1.contains(choises[i][1])) {
                    number = solveId[i];
                    button = solveButton[i];
                }

            }

            if (!played.contains(number)) playgame(number, button);
            else AutoPlay();


        } else {
            Button button = b5;
            int number = 5;
            for (i = 0; i < choises.length; i++) {

                if (player2.contains(choises[i][0]) && player2.contains(choises[i][1]) ||
                        player1.contains(choises[i][0]) && player1.contains(choises[i][1])) {
                    number = solveId[i];
                    button = solveButton[i];
                }

            }

            if (!played.contains(number)) playgame(number, button);
            else AutoPlay();
        }

    }
}

