package com.example.android.survey;

/**
 * Created by MrReRe on 4/2/17.
 */

public class Option {
    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    int value;
    String text;

    public Option(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
