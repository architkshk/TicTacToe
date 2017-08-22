package example.archtkshk.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	int gamePlayer=0;
    boolean gameIsActive =  true;
	int[][] winningPositions={ {0,1,2} , {3,4,5} , {6,7,8} , {0,4,8} , {2,4,6} , {0,3,6} , {1,4,7} , {2,5,8} };
  	int[] gameState={2,2,2,2,2,2,2,2,2};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(
				R.layout.activity_main);
	}



	public void dropIn(View view) {
	    ImageView v = (ImageView) view;


        int k=Integer.parseInt
                (v.getTag().toString());
        if (gameState[k] == 2 && gameIsActive)  {

            v.setTranslationY(-1000f);

            gameState[k]=gamePlayer;

            if (gamePlayer == 0) {

                v.setImageResource(R.drawable.yellow);
                gamePlayer = 1;

            } else {

                v.setImageResource(R.drawable.red);
                gamePlayer = 0;

            }

            v.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions)
            {
                TextView msg = (TextView) findViewById(R.id.winnerMessage);

                if( gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2)
                {

                    if( gameState[winningPosition[0]]==0)
                    {
                        msg.setText("Yellow has won!");
                    }

                    else if( gameState[winningPosition[0]]==1)
                    {
                        msg.setText("Red has won!");
                    }

                    LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(View.VISIBLE);
                    gameIsActive = false;


                } else {

                    boolean gameIsOver =true;

                    for(int counterState : gameState)
                    {

                        if(counterState == 2)
                            gameIsOver = false;

                    }

                    if(gameIsOver)
                    {
                        msg.setText("Its a Draw!!" );
                        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgain.setVisibility(View.VISIBLE);
                        gameIsActive = false;

                    }

                }
            }

        }

    }

    public void playAgain(View view) {

	    gamePlayer=0;
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);

        playAgain.setVisibility(View.INVISIBLE);

        for (int i =0 ; i<gameState.length;i++)
        {
            gameState[i]=2;

        }

        GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i < g.getChildCount(); i++)
        {
            ((ImageView) g.getChildAt(i)).setImageResource(0);
        }
        gameIsActive = true;


	}

}
