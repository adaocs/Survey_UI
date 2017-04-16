package com.example.android.survey;

import java.io.Serializable;

/**
 * Created by MrReRe on 4/2/17.
 */

public class Option implements Serializable {
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
