package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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

public class Nutrition_11to13 extends Fragment implements View.OnClickListener {

    private Button yes, no;
    private ArrayList<Answer> answers;
    private ArrayList<Question> questions;
    private int index;
    private TextView questionView, questionStatus;

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


        updateQuestions(index);

        return view;
    }

    public void updateQuestions(int num) {
        Question question = questions.get(num);
        questionView.setText(question.getDescription());
        yes.setText(question.getOptions().get(0).getText());
        no.setText(question.getOptions().get(1).getText());
        questionStatus.setText("Question "+ (index+1) + " out of 13");


    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        Question question = questions.get(index);
        Option option;
        Answer answer;
        switch (v.getId()) {

            case R.id.male:
                option = question.getOptions().get(0);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;

                if(index ==13) {
                    bundle.putSerializable("answers", (Serializable) answers);
                    Intent intent = new Intent(getActivity(), Result_ThirdActivity.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }

                break;

            case R.id.female:
                option = question.getOptions().get(1);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if(index == 13) {
                    bundle.putSerializable("answers", (Serializable) answers);
                    Intent intent = new Intent(getActivity(), Result_ThirdActivity.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);

                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;
        }
    }

}
