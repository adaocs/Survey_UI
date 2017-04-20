package com.example.android.survey;


import java.io.Serializable;

/**
 * Created by MrReRe on 4/16/17.
 */

public class Answer implements Serializable {
    private Option option;
    private Question question;
    private long time;

    public Answer(Question question, Option option, Long time){
        this.question = question;
        this.option = option;
        this.time = time;
    }
    public void setQuestion(Question question){
        this.question = question;
    }
    public Question getQuestion(){
        return this.question;
    }
    public Option getAnswer(){
        return this.option;
    }
    public void setAnswer(){
        this.option = option;
    }

    public long getTime(){return this.time;}
    public void setTime(long time){this.time = time;}
}
