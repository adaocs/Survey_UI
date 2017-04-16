package com.example.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import java.util.ArrayList;

public class Result_ThirdActivity extends Activity {

    private ArrayList<Answer> answers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__third);

        Intent intent = getIntent();
        if(intent != null){
           Bundle bundle = intent.getExtras();
            answers = (ArrayList<Answer>) bundle.getSerializable("answers");
            for(Answer answer: answers){
                System.out.println(answer.getQuestion().getDescription());
                System.out.println(answer.getAnswer().getText());
            }
        }
    }

}
