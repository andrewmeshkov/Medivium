package com.kstudio.quizapp.model;

public class Statistics {

    private int correct;
    private int wrong;
    private int skip;
    private int score;
    private String date;
    private String category;

    public Statistics() {
    }

    public Statistics(int correct, int wrong, int skip, int score, String date, String category) {
        this.correct = correct;
        this.wrong = wrong;
        this.skip = skip;
        this.score = score;
        this.date = date;
        this.category = category;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
