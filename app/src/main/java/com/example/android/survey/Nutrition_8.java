package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MrReRe on 4/16/17.
 */

public class Nutrition_8 extends Fragment implements View.OnClickListener {

    private EditText input;
    private ArrayList<Question> questions;

    private Button done;
    private String weight;
    private ArrayList<Answer> answerList;
    private Option option;
    private Answer answer;
    private Question question;
    private int index;
    private TextView questionView, questionStatus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_six, containter, false);

        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        answerList = (ArrayList<Answer>) bundle.getSerializable("answers");
        questions = (ArrayList<Question>) bundle.getSerializable("questions");


        questionView = (TextView) view.findViewById(R.id.description);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);

        question = questions.get(index);
        questionView.setText(question.getDescription());
        questionStatus.setText("Question "+ (index+1) + " out of 13");


        input = (EditText) view.findViewById(R.id.input);
        done = (Button) view.findViewById(R.id.done);
        done.setText("Next question");
        done.setOnClickListener(this);
        return view;}

    @Override
    public void onClick(View v) {
        weight = input.getText().toString();
        option = new Option(0, weight);
        answer = new Answer(question, option);
        answerList.add(answer);
        index++;

        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Nutrition_9 fragment = new Nutrition_9();

        bundle.putInt("index", index);
        bundle.putSerializable("answers", (Serializable) answerList);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }
}
