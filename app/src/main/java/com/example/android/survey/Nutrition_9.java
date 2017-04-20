package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MrReRe on 4/16/17.
 */


public class Nutrition_9 extends Fragment implements View.OnClickListener {

    private Button yes, no;
    private ArrayList<Answer> answers;
    private ArrayList<Question> questions;
    private int index;
    private TextView questionView, questionStatus;
    private Question question;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_five, containter, false);


        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        answers = (ArrayList<Answer>) bundle.getSerializable("answers");
        questions = (ArrayList<Question>) bundle.getSerializable("questions");


        questionView = (TextView) view.findViewById(R.id.question);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);
        yes = (Button) view.findViewById(R.id.male);
        no = (Button) view.findViewById(R.id.female);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);


        question = questions.get(index);
        questionView.setText(question.getDescription());
        yes.setText(question.getOptions().get(0).getText());
        no.setText(question.getOptions().get(1).getText());
        questionStatus.setText("Question "+ (index+1) + " out of 13");

        return view;
    }
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Nutrition_10 fragment = new Nutrition_10();
        Nutrition_11to13 fragmentTwo = new Nutrition_11to13();
        Option option;
        Answer answer;
        Long time;
        switch (v.getId()) {

            case R.id.male:
                time = System.currentTimeMillis();
                option = question.getOptions().get(0);
                answer = new Answer(question, option, time);
                answers.add(answer);
                index++;
                bundle.putInt("index", index);
                bundle.putSerializable("answers", (Serializable) answers);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;

            case R.id.female:
                time = System.currentTimeMillis();
                option = question.getOptions().get(1);
                answer = new Answer(question, option, time);
                answers.add(answer);
                index = index + 2;
                bundle.putInt("index", index);
                bundle.putSerializable("answers", (Serializable) answers);
                fragmentTwo.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                fragmentTransaction.commit();
                break;
        }






    }
}
