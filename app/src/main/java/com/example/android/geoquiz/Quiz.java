package com.example.android.geoquiz;

/**
 * Created by Adrian on 7/27/2017.
 */

public class Quiz {
    private String mText;
    private String mCorrectAnswer;
    private int mDifficulty;
    private int mScore;




    public Quiz(String text, String CorrectAnswer, int difficulty) {
        mText = text;
        mCorrectAnswer = CorrectAnswer;
        mDifficulty = difficulty;
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
}
