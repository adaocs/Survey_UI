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

/**
 * Created by MrReRe on 4/11/17.
 */

public class FragmentSeven_Gender extends Fragment implements View.OnClickListener{

    private Button male, female;
    private boolean isMale, isFemale;
    private int score;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_five, container, false);
        Bundle bundle = getArguments();
        score = bundle.getInt("score");
        male = (Button) view.findViewById(R.id.male);
        female = (Button) view.findViewById(R.id.female);
        isMale = false;
        isFemale = false;
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentEight_Age fragmentEight = new FragmentEight_Age();
        fragmentEight.setArguments(bundle);

        switch (v.getId()) {

            case R.id.male:

                isMale = true;
                isFemale = false;
                bundle.putInt("score", score);
                bundle.putBoolean("male", isMale);
                bundle.putBoolean("female", isFemale);
                fragmentTransaction.replace(R.id.fragment_container, fragmentEight);
                fragmentTransaction.commit();


                break;

            case R.id.female:

                isMale = false;
                isFemale = true;
                bundle.putInt("score", score);
                bundle.putBoolean("male", isMale);
                bundle.putBoolean("female", isFemale);
                fragmentTransaction.replace(R.id.fragment_container, fragmentEight);
                fragmentTransaction.commit();

                break;
        }
    }
}
