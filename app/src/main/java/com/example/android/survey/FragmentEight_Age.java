package com.example.android.survey;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by MrReRe on 4/11/17.
 */

public class FragmentEight_Age extends Fragment implements View.OnClickListener {
    private int score;
    private EditText input;
    private Button done;
    private String age;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_six, container, false);
        Bundle bundle = getArguments();
        score = bundle.getInt("score");

        input = (EditText) view.findViewById(R.id.input);
        done = (Button) view.findViewById(R.id.done);

        done.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){
        super.onDestroy();
    }
}
