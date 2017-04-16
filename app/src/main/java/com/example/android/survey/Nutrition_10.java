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

public class Nutrition_10 extends Fragment implements View.OnClickListener {

    private Button done;
    private ArrayList<Answer> answers;
    private ArrayList<Question> questions;
    private int index;
    private TextView questionView, questionStatus;
    private String weight, day;
    private EditText weightInput, dayInput;
    private Question question;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_eight, containter, false);

        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        answers = (ArrayList<Answer>) bundle.getSerializable("answers");
        questions = (ArrayList<Question>) bundle.getSerializable("questions");


        questionView = (TextView) view.findViewById(R.id.question);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);
        done = (Button) view.findViewById(R.id.done);

        weightInput = (EditText) view.findViewById(R.id.weightInput);
        dayInput = (EditText) view.findViewById(R.id.dayInput);

        done.setOnClickListener(this);


        question = questions.get(index);
        questionView.setText(question.getDescription());
        done.setText("Next Question");
        questionStatus.setText("Question "+ (index+1) + " out of 13");

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Nutrition_11to13 fragment = new Nutrition_11to13();

        Question question = questions.get(index);
        weight = weightInput.getText().toString();
        day   = dayInput.getText().toString();

        Option option = new Option(0, "Lost by: "+ weight + " lbs" + "\n" + "In: " + day + " days");
        Answer answer = new Answer(question, option);
        answers.add(answer);
        index++;
        bundle.putInt("index", index);
        bundle.putSerializable("answers", (Serializable) answers);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
