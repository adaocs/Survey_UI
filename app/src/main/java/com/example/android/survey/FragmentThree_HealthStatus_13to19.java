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

public class FragmentThree_HealthStatus_13to19 extends Fragment implements View.OnClickListener {

    private ArrayList<Question> questions;
    private TextView descriptionView, activityView;
    private Button choice1, choice2;
    private int index, score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);

        Bundle bundle = getArguments();

        questions = (ArrayList<Question>) bundle.getSerializable("questions");
        index = bundle.getInt("index");
        score = bundle.getInt("score");
        descriptionView = (TextView) view.findViewById(R.id.description);
        activityView = (TextView) view.findViewById(R.id.activity);
        choice1 = (Button) view.findViewById(R.id.choice1);
        choice2 = (Button) view.findViewById(R.id.choice2);

        updateQuestions(index);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentFour_HealthStatus_20to23 fragmentFour = new FragmentFour_HealthStatus_20to23();
        fragmentFour.setArguments(bundle);

        switch (v.getId()) {

            case R.id.choice1:
                score += 1;
                index++;
                if (index == 19) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFour);
                    fragmentTransaction.commit();

                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice2:
                score += 2;
                index++;
                if (index == 19) {
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentFour);
                    fragmentTransaction.commit();
                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;


        }

    }

    public void updateQuestions(int num) {
        Question question = questions.get(num);
        activityView.setText(question.getDescription());
        if (num < 16)
            descriptionView.setText("During the past 4 weeks, have you had any of the following problems with your work or other regular daily activities as a result of your physical health?");
        else
            descriptionView.setText("During the past 4 weeks, have you had any of the following problems with your work or other regular daily activities as a result of any emotional problems" +
                    " such as feeling depressed or anxious?");

        choice1.setText(question.getOptions().get(0).getText());
        choice2.setText(question.getOptions().get(1).getText());

    }


}
