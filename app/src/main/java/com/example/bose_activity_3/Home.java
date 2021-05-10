package com.example.bose_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener{

    TextView txt_p1s, txt_p2s,roundshow,drawcounter,playerturn;
    Button bt_reset;
    Button[] button = new Button[9];
    int p1scorec,p2scorec,roundcount,round,draw;
    boolean ap;

    int [] gs = {2,2,2,2,2,2,2,2,2};
    int [][] ws = {{0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                      {0,4,8},{2,4,6}
                  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playerturn = (TextView)findViewById(R.id.PLAYERT) ;
        txt_p1s = (TextView)findViewById(R.id.t_p1s);
        txt_p2s = (TextView)findViewById(R.id.t_p2s);
        roundshow = (TextView)findViewById(R.id.rshow);
        drawcounter = (TextView)findViewById(R.id.drawc);
        bt_reset = (Button) findViewById(R.id.b_reset);
        for(int i=0; i < button.length; i++){
            String buttonID = "b_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            button[i] = (Button) findViewById(resourceID);
            button[i].setOnClickListener(this);

        }

        roundcount = 0;
        p1scorec = 0;
        p2scorec = 0;
        round = 1;
        draw = 0;
        ap = true;
    }

    @Override
    public void onClick(View view) {
        //Log.i("test","button is clicked");
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(ap){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#1b42e0"));
            playerturn.setText("PLAYER 2");
            gs[gameStatePointer] = 0;
        }else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#eb0000"));
            playerturn.setText("PLAYER 1");
            gs[gameStatePointer] = 1;
        }
        roundcount++;

        if(checkWinner()){
            if(ap){
                Toast.makeText(this, "Player 1 Won", Toast.LENGTH_SHORT).show();
                p1scorec++;
                round++;
                updatePlayerScore();
                updateround();
                playAgain();
            }else{
                Toast.makeText(this, "Player 2 Won", Toast.LENGTH_SHORT).show();
                p2scorec++;
                round++;
                updatePlayerScore();
                updateround();
                playAgain();
            }
        }else if(roundcount == 9){
            round++;
            draw++;
            updateround();
            updatedraw();
            playAgain();
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();

        }else{
            ap = !ap;
        }

       bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                p1scorec = 0;
                p2scorec = 0;
                draw = 0;
                round = 1;
                playerturn.setText("PLAYER 1");
                updatePlayerScore();
                updatedraw();
                updateround();
            }
        });
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningState:ws){
            if(gs[winningState[0]]==gs[winningState[1]] &&
                    gs[winningState[1]]==gs[winningState[2]] &&
                   gs[winningState[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        txt_p1s.setText(Integer.toString(p1scorec));
        txt_p2s.setText(Integer.toString(p2scorec));
    }

    public void playAgain(){
        roundcount = 0;
        ap = true;

        for(int i = 0; i < button.length; i++){
            gs[i] = 2;
            button[i].setText("");
        }
    }

    public void updateround(){
     roundshow.setText(Integer.toString(round));
    }
    public void updatedraw(){
        drawcounter.setText(Integer.toString(draw));
    }
}