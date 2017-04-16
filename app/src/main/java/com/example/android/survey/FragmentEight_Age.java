package com.example.android.survey;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by MrReRe on 4/11/17.
 */

public class FragmentEight_Age extends Fragment implements View.OnClickListener {
    private int score;
    private EditText input;
    private Button done;
    private String age;
    private ArrayList<Answer> answerList;
    private Question question;
    private Option option;
    private Answer answer;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_six, container, false);
        Bundle bundle = getArguments();
        score = bundle.getInt("score");
        answerList = (ArrayList<Answer>) bundle.getSerializable("answers");
        question = new Question();
        question.setDescription("How old are you?");



        input = (EditText) view.findViewById(R.id.input);
        done = (Button) view.findViewById(R.id.done);

        done.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){
        age = input.getText().toString();
        option = new Option(0, age);
        answer = new Answer(question, option);
        answerList.add(answer);
        for(Answer answer: answerList){
            System.out.println(answer.getQuestion().getDescription());
            System.out.println(answer.getAnswer().getText());
        }
        Intent intent = new Intent(getActivity(), ThirdActivity.class);
        getActivity().startActivity(intent);
    }
}
