package com.kstudio.quizapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    String Name;
    ArrayList<Quezcus> Questions;
    Long time;

    public Test() {
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Quezcus> getQuestions() {
        return Questions;
    }

    public void setQuestions(ArrayList<Quezcus> questions) {
        Questions = questions;
    }
}