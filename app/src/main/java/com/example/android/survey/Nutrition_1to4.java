package com.example.android.survey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
 * Created by MrReRe on 4/16/17.
 */

public class Nutrition_1to4 extends Fragment implements OnClickListener {

    private Button yes, no;
    private ArrayList<Answer> answers;

    private ArrayList<Question> questions;
    final String QUESTION = "question";
    final String OPTION = "option";
    final String DESCRIPTION = "description";

    private int index;
    private TextView questionView, questionStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_five, containter, false);

        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("questions3_nutrition.xml");
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

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionView = (TextView) view.findViewById(R.id.question);
        questionStatus = (TextView) view.findViewById(R.id.questionStatus);
        yes = (Button) view.findViewById(R.id.male);
        no = (Button) view.findViewById(R.id.female);

        index = 0;
        updateQuestions(index);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        answers = new ArrayList<>();
        return view;
    }
    @Override
    public void onClick(View v) {


        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Nutrition_5 fragmentTwo = new Nutrition_5();
        fragmentTwo.setArguments(bundle);
        Question question = questions.get(index);
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
                if (index == 4) {
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                    fragmentTransaction.commit();

                } else if (index < questions.size()) {
                    updateQuestions(index);
                }
                break;

            case R.id.female:
                time = System.currentTimeMillis();
                option = question.getOptions().get(1);
                answer = new Answer(question, option, time);
                answers.add(answer);
                index++;
                if (index == 4) {
                    bundle.putInt("index", index);
                    bundle.putSerializable("answers", (Serializable) answers);
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
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
        yes.setText(question.getOptions().get(0).getText());
        no.setText(question.getOptions().get(1).getText());
        questionStatus.setText("Question "+ (index+1) + " out of 13");


    }
}
