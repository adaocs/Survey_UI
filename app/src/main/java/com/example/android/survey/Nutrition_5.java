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

public class Nutrition_5 extends Fragment implements View.OnClickListener {
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;
    private TextView questionView, questionStatus;
    private EditText breakfastInput, lunchInput, dinnerInput, snacksInput, drinksInput;
    private Button done;
    private int index;
    private String breakfast, lunch, dinner, snacks, drinks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_seven, containter, false);

        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        questions = (ArrayList<Question>) bundle.getSerializable("questions");
        answers = (ArrayList<Answer>) bundle.getSerializable("answers");

        questionView = (TextView) view.findViewById(R.id.description);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);

        breakfastInput= (EditText) view.findViewById(R.id.breakfastInput);
        lunchInput= (EditText) view.findViewById(R.id.lunchInput);
        dinnerInput= (EditText) view.findViewById(R.id.dinnerInput);
        snacksInput= (EditText) view.findViewById(R.id.snacksInput);
        drinksInput= (EditText) view.findViewById(R.id.drinksInput);

        done = (Button) view.findViewById(R.id.done);
        done.setOnClickListener(this);

        questionView.setText(questions.get(index).getDescription());
        questionStatus.setText("Question "+ (index+1) + " out of 13");

        return view;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Nutrition_6to7 fragment = new Nutrition_6to7();

        Question question = questions.get(index);
        breakfast = breakfastInput.getText().toString();
        lunch   = lunchInput.getText().toString();
        dinner = dinnerInput.getText().toString();
        snacks = snacksInput.getText().toString();
        drinks = drinksInput.getText().toString();

        Option option = new Option(0, "Breakfast: "+breakfast+ "\n" + "Lunch: " + lunch + "\n" +
                "Dinner: " + dinner + "\n" + "Snacks: " + snacks + "\n"+ "Drinks: " + drinks);
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
