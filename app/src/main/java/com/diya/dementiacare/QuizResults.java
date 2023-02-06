package com.diya.dementiacare;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizResults extends AppCompatActivity {

    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_results);

        tv = (TextView)findViewById(R.id.tvres);
        tv2 = (TextView)findViewById(R.id.tvres2);
        tv3 = (TextView)findViewById(R.id.tvres3);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        Button exit_tv = (Button) findViewById(R.id.exit_tv);


        StringBuffer sb = new StringBuffer();
        sb.append("Correct Answers: " + QuizGameActivity.correct + "\n");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Incorrect Answers: " + QuizGameActivity.wrong + "\n");
        StringBuffer sb3 = new StringBuffer();
        sb3.append("Final Score: " + QuizGameActivity.correct + "\n");
        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);

        QuizGameActivity.correct=0;
        QuizGameActivity.wrong=0;

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), QuizGameActivity.class);
                startActivity(in);
                finish();
            }
        });
        exit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();

            }});

    }
    public void goHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}