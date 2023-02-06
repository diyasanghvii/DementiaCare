package com.diya.dementiacare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class QuizActivity extends AppCompatActivity {
    Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button = (Button) findViewById(R.id.new_game_tv);
        Button exit_tv = (Button) findViewById(R.id.exit_tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }



        });
        exit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();

            }});
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, QuizGameActivity.class);
        startActivity(intent);
        finish();
    }
        public void goHome(){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
}