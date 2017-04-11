package com.example.android.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text_Score, text_Result;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);

        text_Score = (TextView) findViewById(R.id.score);
        text_Result = (TextView) findViewById(R.id.result);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);

        text_Score.setText("Levels of Depression: " + score + " / 63" );


        if(score >= 0 && score <= 10){
            text_Result.setText("Result: These ups and downs are considered normal");

        }

        if(score >= 11 && score <= 16){
            text_Result.setText("Result: Mild mood disturbance");

        }

        if(score >= 17 && score <= 20){
            text_Result.setText("Result: Borderline clinical depression");

        }

        if(score >= 21 && score <= 30){
            text_Result.setText("Result: Moderate depression");

        }

        if(score >= 31 && score <= 40){
            text_Result.setText("Result: Severe depression");

        }
        if(score > 40){
            text_Result.setText("Result: Extreme depression");
        }
    }


    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
