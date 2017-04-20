package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MrReRe on 4/11/17.
 */

public class FragmentSeven_Gender extends Fragment implements View.OnClickListener{

    private Button male, female;
    private ArrayList<Answer> answers;

    private int score;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_five, container, false);

        Bundle bundle = getArguments();
        score = bundle.getInt("score");
        answers = (ArrayList<Answer>) bundle.getSerializable("answers");
        male = (Button) view.findViewById(R.id.male);
        female = (Button) view.findViewById(R.id.female);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Age fragmentEight = new Age();
        fragmentEight.setArguments(bundle);
        Question question = new Question();
        question.setDescription("Are you male or female?");
        Option option;
        Answer answer;
        Long time;
        switch (v.getId()) {

            case R.id.male:

                time = System.currentTimeMillis();
                option = new Option(0, "male");
                answer = new Answer(question, option, time);
                answers.add(answer);
                bundle.putInt("score", score);
                bundle.putSerializable("answers", (Serializable) answers);
                fragmentTransaction.replace(R.id.fragment_container, fragmentEight);
                fragmentTransaction.commit();


                break;

            case R.id.female:

                time = System.currentTimeMillis();
                option = new Option(1, "female");
                answer = new Answer(question, option, time);
                answers.add(answer);
                bundle.putInt("score", score);
                bundle.putSerializable("answers", (Serializable) answers);
                fragmentTransaction.replace(R.id.fragment_container, fragmentEight);
                fragmentTransaction.commit();

                break;
        }
    }
}
