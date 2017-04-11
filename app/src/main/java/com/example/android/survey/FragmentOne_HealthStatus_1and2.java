package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MrReRe on 4/10/17.
 */

public class FragmentOne_HealthStatus_1and2 extends Fragment implements View.OnClickListener {

    private ArrayList<Question> questions;
    final String QUESTION = "question";
    final String OPTION = "option";
    final String DESCRIPTION = "description";

    TextView questionView;
    Button choice1, choice2, choice3, choice4, choice5;
    int index,score;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fragment_one,containter, false);

        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("questions2.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            // Automatically identify the encoding based on xml 1.0 standard.
            xmlPullParser.setInput(inputStream, null);

            int eventType = xmlPullParser.getEventType();
            Question question = null;
            questions = new ArrayList<>();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals(QUESTION)) {
                            // Create a new questions object.
                            question = new Question();
                        } else if (xmlPullParser.getName().equals(DESCRIPTION)) {
                            // Inside a description.
                            // The next text element is the description of question.
                            question.setDescription(xmlPullParser.nextText());
                        } else if (xmlPullParser.getName().equals(OPTION)) {
                            // Inside a description.
                            // Add the option to the current question.
                            question.addOption(new Option(
                                    Integer.parseInt(xmlPullParser.getAttributeValue(0)),
                                    xmlPullParser.nextText()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xmlPullParser.getName().equals(QUESTION)) {
                            // Added the question to the list.
                            questions.add(question);
                        } else if (xmlPullParser.getName().equals(DESCRIPTION)) {
                        } else if (xmlPullParser.getName().equals(OPTION)) {
                        }
                        break;
                    case XmlPullParser.TEXT:
                        // The actual text inside the tags.
                        break;
                }
                eventType = xmlPullParser.next();
            }

            for (Question q : questions) {
                Log.d("Question", q.getDescription());
                for (Option o : question.getOptions()) {
                    Log.d("Option", "Name=" + o.getText() + " Value=" + o.getValue());
                }
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(questions.toString());
        questionView = (TextView) view.findViewById(R.id.question);
        choice1 = (Button) view.findViewById(R.id.choice1);
        choice2 = (Button) view.findViewById(R.id.choice2);
        choice3 = (Button) view.findViewById(R.id.choice3);
        choice4 = (Button) view.findViewById(R.id.choice4);
        choice5 = (Button) view.findViewById(R.id.choice5);

        index = 0;
        score = 0;
        updateQuestions(index);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);
        choice5.setOnClickListener(this);




        return view;
    }

    public void updateQuestions(int num) {
        Question question = questions.get(num);
        questionView.setText(question.getDescription());
        choice1.setText(question.getOptions().get(0).getText());
        choice2.setText(question.getOptions().get(1).getText());
        choice3.setText(question.getOptions().get(2).getText());
        choice4.setText(question.getOptions().get(3).getText());
        choice5.setText(question.getOptions().get(4).getText());

    }

    public void onClick(View v){

        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTwo_HealthStatus_3to12 fragmentTwo = new FragmentTwo_HealthStatus_3to12();
        fragmentTwo.setArguments(bundle);

        switch (v.getId()){

            case R.id.choice1:
                score += 1;
                index++;
                if(index == 2){
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();

                }
                else if(index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.choice2:
                score += 2;
                index++;
                if(index == 2){
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();
                }
                else if(index < questions.size()) {
                    updateQuestions(index);
                }

                break;

            case R.id.choice3:
                score += 3;
                index++;
                if(index == 2){
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();
                }
                else if(index < questions.size()) {
                    updateQuestions(index);
                }
                break;


            case R.id.choice4:
                score += 4;
                index++;
                if(index == 2){
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();
                }
                else if(index < questions.size()) {
                    updateQuestions(index);
                }

                break;

            case R.id.choice5:
                score += 5;
                index++;
                if(index == 2){
                    bundle.putInt("score", score);
                    bundle.putInt("index", index);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();
                }
                else if(index < questions.size()) {
                    updateQuestions(index);
                }

                break;


        }

    }


}
