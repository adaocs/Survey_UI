package com.example.android.survey;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String QUESTION = "question";
    final String OPTION = "option";
    final String DESCRIPTION = "description";

    private Button choice1, choice2, choice3, choice4;
    private ArrayList<Question> questions;
    private TextView questionView;
    private int score;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("questions.xml");
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


        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);
        questionView = (TextView) findViewById(R.id.question);
        score = 0;
        index = 0;
        updateQuestions(index);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 0;
                index++;
                if (index < questions.size())
                    updateQuestions(index);
                else showResults();
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 1;
                index++;
                if (index < questions.size())
                    updateQuestions(index);
                else showResults();

            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 2;
                index++;
                if (index < questions.size())
                    updateQuestions(index);
                else showResults();

            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 3;
                index++;
                if (index < questions.size())
                    updateQuestions(index);
                else showResults();

            }
        });

    }

    public void updateQuestions(int num) {
        Question question = questions.get(num);
        questionView.setText("Choose the option that fits you most");
        choice1.setText(question.getOptions().get(0).getText());
        choice2.setText(question.getOptions().get(1).getText());
        choice3.setText(question.getOptions().get(2).getText());
        choice4.setText(question.getOptions().get(3).getText());

    }

    public void showResults() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}


