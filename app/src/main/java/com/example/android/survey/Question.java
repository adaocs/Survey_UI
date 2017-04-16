package com.example.android.survey;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MrReRe on 4/2/17.
 */

// Class which represents a question.
public class Question implements Serializable {

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    String description;

    public ArrayList<Option> getOptions() {
        return options;
    }

    ArrayList<Option> options;

    public void addOption(Option op) {
        options.add(op);
    }

    public Question() {
        options = new ArrayList<>();
    }

}
