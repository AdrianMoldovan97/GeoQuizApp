package com.example.android.geoquiz;

import java.util.ArrayList;

public class Question {
    private String mText;
    private String[] mAnswers = new String[3];
    private String mCorrectAnswer;
    private ArrayList<String> mCorrectAnswers;
    private String selectedAnswer;
    private int mDifficulty;

    public Question(String text,  String correctAnswer, int difficulty) {
        mText = text;
        mCorrectAnswer = correctAnswer;
        mDifficulty = difficulty;
    }

    public Question(String text, String[] answers , String correctAnswer, int difficulty) {
        mText = text;
        mCorrectAnswer = correctAnswer;
        mDifficulty = difficulty;
        mAnswers = answers;
    }

    public  Question (String text, String[] answers , ArrayList<String> correctAnswers, int difficulty) {
        mText = text;
        mCorrectAnswers = correctAnswers;
        mDifficulty = difficulty;
        mAnswers = answers;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public ArrayList<String> getmCorrectAnswers() {
        return mCorrectAnswers;
    }

    public int getmDifficulty() {
        return mDifficulty;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String getmText() {
        return mText;
    }

    public String[] getmAnswers() {
        return mAnswers;
    }
}
