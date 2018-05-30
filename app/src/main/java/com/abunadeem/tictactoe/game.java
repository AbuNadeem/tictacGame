package com.abunadeem.tictactoe;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class game extends AppCompatActivity {
    TextView scoresP2,scoresP1;
            boolean autoplay;boolean immode;
    private Switch sw,sm;
    int scorep1=0;
    int scorep2=0;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
       scoresP1= findViewById(R.id.score1);
        scoresP2  = findViewById(R.id.score2);
  b1= findViewById(R.id.b1);
         b2= findViewById(R.id.b2);
         b3= findViewById(R.id.b3);
         b4= findViewById(R.id.b4);
         b5= findViewById(R.id.b5);
         b6= findViewById(R.id.b6);
         b7= findViewById(R.id.b7);
         b8= findViewById(R.id.b8);
         b9= findViewById(R.id.b9);
         sw=findViewById(R.id.switch1);
        sm=findViewById(R.id.switch2);
       }


    public void bgo(View view) {

        int sbut = 0;
        int butId = view.getId();

        switch (butId) {
            case R.id.b1: sbut = 1;break;
            case R.id.b3: sbut = 3;break;
            case R.id.b2: sbut = 2;break;
            case R.id.b4: sbut = 4;break;
            case R.id.b5: sbut = 5;break;
            case R.id.b6: sbut = 6;break;
            case R.id.b7: sbut = 7;break;
            case R.id.b8: sbut = 8;break;
            case R.id.b9: sbut = 9;break;
        }
        playgame(sbut, (Button) view);
    }

    //current player
    int cplayer = 1;
    //list of buttons which selected by first player
    ArrayList<Integer> player1 = new ArrayList<>();
    //list of buttons which selected by second player
    ArrayList<Integer> player2 = new ArrayList<>();
    //list of all selected buttons
    ArrayList<Integer> played = new ArrayList<>();


    private void playgame(int sbut, Button selectedbut) {
        autoplay=sw.isChecked();
        immode = sm.isChecked();
        if(cplayer==1) {
            selectedbut.setText("X");
            selectedbut.setTextSize(40);
            selectedbut.setBackgroundResource(R.color.gold);
            selectedbut.setClickable(false);
            selectedbut.setTextColor(Color.WHITE);
            player1.add(sbut);
            played.add(sbut);
            checkwinner();
            cplayer=2;
            if(immode && played.size()<8 && winner==-1) impMode();

            if(autoplay && played.size()<8 && winner==-1) AutoPlay();







        } else{
            selectedbut.setText("O");
            selectedbut.setTextSize(40);
            selectedbut.setBackgroundColor(Color.rgb(100,150,220));
            selectedbut.setTextColor(Color.WHITE);
            selectedbut.setClickable(false);
            player2.add(sbut);
            played.add(sbut);
            checkwinner();
            cplayer=1;
        }
    }


    public int winner=-1;
    private void checkwinner() {

        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner=1;
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner=1;
        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner=1;
        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner=1;
        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner=1;
        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner=1;
        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner=1;
        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner=1;



        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner=2;
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner=2;
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner=2;
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner=2;
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner=2;
        if (player2.contains(9) && player2.contains(6) && player2.contains(3))
            winner=2;
        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner=2;
        if (player2.contains(7) && player2.contains(5) && player2.contains(3))
            winner=2;


        if(winner!=-1)
            if(winner==1){

                scorep1++;
                AlertDialog.Builder me = new AlertDialog.Builder(this, 5);
                me.setTitle(getResources().getString(R.string.cong));
                me.setMessage(getResources().getString(R.string.cong1));
                me.setCancelable(true);
                me.setIcon(R.drawable.ic_action_emo_wink);

                me.show();
                scoresP1.setText(String.valueOf(scorep1));
                stop();
            } else{

               scorep2++;
               AlertDialog.Builder me = new AlertDialog.Builder(this, 5);
                me.setTitle(getResources().getString(R.string.sorry));
                me.setMessage(getResources().getString(R.string.sorry1));
                me.setCancelable(true);
                me.setIcon(R.drawable.ic_action_name);

                me.show();
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
            cplayer = 1;

        } else {

            int t;

            do t = (int) (Math.random() * 8 + 1);
            while (played.contains(t));


            btn[t - 1].setText("O");
            btn[t - 1].setTextSize(40);
            btn[t - 1].setBackgroundColor(Color.rgb(100, 150, 220));
            btn[t - 1].setTextColor(Color.WHITE);
            btn[t - 1].setClickable(false);
            player2.add(t);
            played.add(t);
            checkwinner();
            cplayer = 1;
        }

    }

    public void reset(View view) {
        Button[] btn = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for(Button c : btn){

            c.setClickable(true);
            c.setText("");
            c.setBackgroundResource(R.color.bg);
        }
      player1.clear();
        player2.clear();
        played.clear();
        cplayer=1;
        winner=-1;
    }
    public void stop(){
        Button[] btn = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for(Button c : btn){

            c.setClickable(false);
        }

    }

    public void clear(View view) {
         scorep1=0;
         scorep2=0;
        scoresP1.setText(String.valueOf(scorep1));
        scoresP2.setText(String.valueOf(scorep2));

    }


    public void impMode(){
        int[][] win ={{1,2},{2,3},{3,1},{4,5},{5,6},{6,4},{7,8},{8,9},{9,7},
                      {1,4},{1,7},{4,7},{2,5},{2,8},{5,8},{3,6},{3,9},{6,9},
                      {1,5},{1,9},{5,9},{7,5},{7,3},{5,3}};
        int[] winId={3,1,2,6,4,5,9,7,8,7,4,1,8,5,2,9,6,3,9,5,1,3,5,7};
        Button[] winButton={b3,b1,b2,b6,b4,b5,b9,b7,b8,b7,b4,b1,b8,b5
                ,b2,b9,b6,b3,b9,b5,b1,b3,b5,b7};
        sw.setChecked(false);
        int i;

        if (player1.contains(5)){
            Button b =b3;int id = 3;
            for ( i = 0; i< win.length; i++){

                if (player1.contains(win[i][0]) && player1.contains(win[i][1]) || player2.contains(win[i][0]) && player2.contains(win[i][1]))
                {
                    id= winId[i];
                    b=winButton[i];
                 /*   winButton[i].setText("O");
                    winButton[i].setTextSize(40);
                    winButton[i].setBackgroundColor(Color.rgb(100, 150, 220));
                    winButton[i].setTextColor(Color.WHITE);
                    winButton[i].setClickable(false);
                    player2.add(winId[i]);
                    played.add(winId[i]);
                    checkwinner();
                    cplayer = 1; */

                }

            }
           if(!played.contains(id)) playgame(id,b);
              else AutoPlay();

            }else {
            Button b =b5;int id = 5;

            for ( i = 0; i< win.length; i++){

                if (player1.contains(win[i][0]) && player1.contains(win[i][1]) || player2.contains(win[i][0]) && player2.contains(win[i][1]))
                {
                   id= winId[i];
                   b=winButton[i];

                }

            }  if(!played.contains(id)) playgame(id,b);
            else AutoPlay();
                }

                }
}
