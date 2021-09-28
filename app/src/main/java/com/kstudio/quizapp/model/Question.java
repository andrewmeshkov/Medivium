package com.kstudio.quizapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Question implements Parcelable {
    private String question;
    private String answer;
    private String option1;
    private String option2;

    private String option3;
    private String option4;
    private String choice;
    private String st,

            IsImageQuestion,
            Image;

    public Question() {
    }

    public Question(String question, String answer, String option1, String option2, String option3, String option4, String choice,String st) {
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.choice = choice;
        this.st = st;
    }
    public String getIsImageQuestion() {
        return IsImageQuestion;
    }

    public void setIsImageQuestion(String isImageQuestion) {
        this.IsImageQuestion = isImageQuestion;
    }
    protected Question(Parcel in) {
        question = in.readString();
        answer = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        IsImageQuestion = in.readString();
        option4 = in.readString();
        choice = in.readString();
        Image = in.readString();
        st = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(choice);
        dest.writeString(st);
        dest.writeString(Image);
        dest.writeString(IsImageQuestion);
}

    public String getImage() {
        return  Image;
    }
}


