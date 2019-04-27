package com.anshaysingh.additiongame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    Button startButton;
    TextView questionTextView;
    TextView scoreTextView;
    TextView finalScoreTextView;
    TextView timerTextView;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int correctScore = 0;
    int incorrectScore = 0;
    boolean gameOver = false;

    public void playAgain(View view) {
        gameOver = false;
        correctScore = 0;
        incorrectScore = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        finalScoreTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }
            public void onFinish() {
                finalScoreTextView.setText("Score: "+Integer.toString(correctScore)+" / "+Integer.toString(correctScore+incorrectScore));
                playAgainButton.setVisibility(View.VISIBLE);
                gameOver = true;
            }
        }.start();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        question();
        playAgain(findViewById(R.id.playAgainButton));


    }
    public void question() {
        if(!gameOver) {
            Random rand = new Random();
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);
            int c = a + b;
            questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
            locationOfCorrectAnswer = rand.nextInt(4);
            answers.clear();
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers.add(c);
                } else {
                    int incorrectAnswer = rand.nextInt(41);
                    while (incorrectAnswer == c) {
                        incorrectAnswer = rand.nextInt(41);
                    }
                    answers.add(incorrectAnswer);
                }
            }
            option1.setText(Integer.toString(answers.get(0)));
            option2.setText(Integer.toString(answers.get(1)));
            option3.setText(Integer.toString(answers.get(2)));
            option4.setText(Integer.toString(answers.get(3)));
        }
       }

    public void chosenAnswer(View view) {
        if(!gameOver) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                correctScore++;
                finalScoreTextView.setText("Correct!");
            } else {
                incorrectScore++;
                finalScoreTextView.setText("Incorrect!");
            }
            scoreTextView.setText(Integer.toString(correctScore) + " / " + Integer.toString(incorrectScore + correctScore));
            question();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutTwo);
        startButton = (Button) findViewById(R.id.startButton);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        finalScoreTextView = (TextView) findViewById(R.id.finalScoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
    }
}
