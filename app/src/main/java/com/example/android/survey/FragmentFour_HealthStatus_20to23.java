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
 * Created by MrReRe on 4/10/17.
 */

public class FragmentFour_HealthStatus_20to23 extends Fragment implements View.OnClickListener {
    private ArrayList<Question> questions;
    private TextView questionView, questionStatus;
    private Button choice1, choice2, choice3, choice4, choice5;
    private int index, score;
    private ArrayList<Answer> answers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        Bundle bundle = getArguments();

        questions = (ArrayList<Question>) bundle.getSerializable("questions");
        answers = (ArrayList<Answer>) bundle.getSerializable("answers");

        index = bundle.getInt("index");
        score = bundle.getInt("score");
        questionView = (TextView) view.findViewById(R.id.question);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);

        choice1 = (Button) view.findViewById(R.id.choice1);
        choice2 = (Button) view.findViewById(R.id.choice2);
        choice3 = (Button) view.findViewById(R.id.choice3);
        choice4 = (Button) view.findViewById(R.id.choice4);
        choice5 = (Button) view.findViewById(R.id.choice5);

        updateQuestions(index);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);
        choice5.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentFive_HealthStatus_24to32 fragmentFive = new FragmentFive_HealthStatus_24to32();
        fragmentFive.setArguments(bundle);
        Question question = questions.get(index);
        Option option;
        Answer answer;
        switch (v.getId()) {

            case R.id.choice1:
                score += 1;
                option = question.getOptions().get(0);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if (index == 23) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFive);
                    fragmentTransaction.commit();

                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice2:
                score += 2;
                option = question.getOptions().get(1);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if (index == 23) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFive);
                    fragmentTransaction.commit();
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice3:
                score += 3;
                option = question.getOptions().get(2);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if (index == 23) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFive);
                    fragmentTransaction.commit();
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice4:
                score += 4;
                option = question.getOptions().get(3);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if (index == 23) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFive);
                    fragmentTransaction.commit();
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice5:
                score += 5;
                option = question.getOptions().get(4);
                answer = new Answer(question, option);
                answers.add(answer);
                index++;
                if (index == 23) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFive);
                    fragmentTransaction.commit();
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;


        }

    }

    public void updateQuestions(int num) {
        Question question = questions.get(num);
        questionView.setText(question.getDescription());
        choice1.setText(question.getOptions().get(0).getText());
        choice2.setText(question.getOptions().get(1).getText());
        choice3.setText(question.getOptions().get(2).getText());
        choice4.setText(question.getOptions().get(3).getText());
        choice5.setText(question.getOptions().get(4).getText());
        questionStatus.setText("Question "+ (index+1) + " out of 36");



    }
}
