package com.example.android.geoquiz;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static android.R.attr.checked;

public class MediumQuestions extends AppCompatActivity {
    int score = 0;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUiComponents();
    }

    private void createUiComponents() {
        addQuestions();
        setContentView(R.layout.activity_medium_questions);

        final ScrollView mainView = (ScrollView) findViewById(R.id.mainLayoutMedium);
        mainView.setBackgroundResource(R.color.colorBackground);
        LinearLayout mainLinearLayout = new LinearLayout(this);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < questions.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setId(i);

            TextView questionText = new TextView(this);
            setStyleTextView(questionText);
            questionText.setText(questions.get(i).getmText());
            linearLayout.addView(questionText);

            for (int j = 0; j < 3; j++) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(questions.get(i).getmAnswers()[j]);
                linearLayout.addView(checkBox);
                checkBox.setId(i + j);
            }
            layouts.add(linearLayout);
        }
        for (int k = 0; k < layouts.size(); k++) {
            mainLinearLayout.addView(layouts.get(k));
        }

        Button submitButton = new Button(this);
        setStyleBottom(submitButton);
        submitButton.setText(getStringFromResources(R.string.submit));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeTotalScore();
            }
        });

        Button restartButton = new Button(this);
        setStyleBottom(restartButton);
        restartButton.setText(getStringFromResources(R.string.restart));
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartScore();
                uncolorAnswers();
                mainView.pageScroll(View.FOCUS_UP);
            }
        });

        mainLinearLayout.addView(submitButton);
        mainLinearLayout.addView(restartButton);
        mainView.addView(mainLinearLayout);
    }

    private void setStyleTextView(TextView textView) {
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        textView.setTypeface(null, Typeface.BOLD_ITALIC);
        textView.setPadding(5, 20, 5, 20);
    }

    private void setStyleBottom(Button button) {
        button.setBackgroundResource(R.color.colorButton);
        button.setPadding(6, 6, 6, 6);
    }

    public void setCheckBoxesUnclickable() {
        for (LinearLayout linearLayout : layouts) {
            for (int i = 1; i < 4; i++) {
                linearLayout.getChildAt(i).setEnabled(false);
            }
        }
    }

    public void setCheckBoxesClickable() {
        for (LinearLayout linearLayout : layouts) {
            for (int i = 1; i < 4; i++) {
                linearLayout.getChildAt(i).setEnabled(true);
                CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                checkBox.setChecked(false);
            }
        }
    }

    public void restartScore() {
        setCheckBoxesClickable();
        Toast.makeText(this, getStringFromResources(R.string.start_quiz), Toast.LENGTH_SHORT).show();
    }

    private void addQuestions() {
        ArrayList<String> correctAnswer1 = new ArrayList<>();
        correctAnswer1.add(getStringFromResources(R.string.Q1_medium_answer1));
        correctAnswer1.add(getStringFromResources(R.string.Q1_medium_answer3));
        ArrayList<String> correctAnswer2 = new ArrayList<>();
        correctAnswer2.add(getStringFromResources(R.string.Q2_medium_answer1));
        correctAnswer2.add(getStringFromResources(R.string.Q2_medium_answer2));
        ArrayList<String> correctAnswer3 = new ArrayList<>();
        correctAnswer3.add(getStringFromResources(R.string.Q3_medium_answer2));
        correctAnswer3.add(getStringFromResources(R.string.Q3_medium_answer3));
        ArrayList<String> correctAnswer4 = new ArrayList<>();
        correctAnswer4.add(getStringFromResources(R.string.Q4_medium_answer3));
        ArrayList<String> correctAnswer5 = new ArrayList<>();
        correctAnswer5.add(getStringFromResources(R.string.Q5_medium_answer2));
        questions.add(new Question(getStringFromResources(R.string.text_Q1_medium), getArrayStringFromResources(R.array.Q1_medium), correctAnswer1, 4));
        questions.add(new Question(getStringFromResources(R.string.text_Q2_medium), getArrayStringFromResources(R.array.Q2_medium), correctAnswer2, 5));
        questions.add(new Question(getStringFromResources(R.string.text_Q3_medium), getArrayStringFromResources(R.array.Q3_medium), correctAnswer3, 5));
        questions.add(new Question(getStringFromResources(R.string.text_Q4_medium), getArrayStringFromResources(R.array.Q4_medium), correctAnswer4, 4));
        questions.add(new Question(getStringFromResources(R.string.text_Q5_medium), getArrayStringFromResources(R.array.Q5_medium), correctAnswer5, 5));
    }

    public boolean areAllChecked() {

        return true;
    }

    private void colorAnswers() {
        for (LinearLayout linearLayout : layouts) {
            int questionId = linearLayout.getId();
            for (int i = 1; i < 4; i++) {
                CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                if (checkBox.isChecked() && questions.get(questionId).getmCorrectAnswers().contains(checkBox.getText())) {
                    checkBox.setBackgroundResource(R.color.colorRightAnswer);
                } else if (checkBox.isChecked() && !questions.get(questionId).getmCorrectAnswers().contains(checkBox.getText())) {
                    checkBox.setBackgroundResource(R.color.colorWrongAnswer);
                } else if (!checkBox.isChecked() && questions.get(questionId).getmCorrectAnswers().contains(checkBox.getText())) {
                    checkBox.setBackgroundResource(R.color.colorAnotherAnswer);
                }
            }
        }
    }

    private void uncolorAnswers() {
        for (LinearLayout linearLayout : layouts) {
            for (int i = 1; i < 4; i++) {
                CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                checkBox.setBackgroundResource(R.color.colorBackground);
            }
        }
    }

    private String getStringFromResources(int resourceId) {
        return getResources().getString(resourceId);
    }

    private String[] getArrayStringFromResources(int resourceId) {
        String[] arrayStringFromResource = new String[3];
        arrayStringFromResource[0] = getResources().getStringArray(resourceId)[0];
        arrayStringFromResource[1] = getResources().getStringArray(resourceId)[1];
        arrayStringFromResource[2] = getResources().getStringArray(resourceId)[2];
        return arrayStringFromResource;
    }

    public void computeTotalScore() {
        score = 0;
        if (areAllChecked() == false) {
            Toast.makeText(this, getStringFromResources(R.string.check_questions), Toast.LENGTH_SHORT).show();
            return;
        }
        for (LinearLayout linearLayout : layouts) {
            int scoreForQuestion = addScoreQuestion(linearLayout);
            score += scoreForQuestion;
        }
        Toast.makeText(this, getStringFromResources(R.string.total)+ score, Toast.LENGTH_SHORT).show();
        setCheckBoxesUnclickable();
        colorAnswers();
    }

    public boolean areCheckBoxesCorrect(LinearLayout linearLayout, ArrayList<String> answers) {
        int arrayLength = answers.size();
        int nrOfCorrectAnswers = 0;
        for (int i = 1; i < linearLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
            if (checkBox.isChecked() && answers.contains(checkBox.getText())) {
                nrOfCorrectAnswers++;
            }
        }
        if (arrayLength == nrOfCorrectAnswers) {
            return true;
        }
        return false;
    }

    public int addScoreQuestion(LinearLayout linearlayout) {
        int questionId = linearlayout.getId();
        if (areCheckBoxesCorrect(linearlayout, questions.get(questionId).getmCorrectAnswers())) {
            return questions.get(questionId).getmDifficulty();
        }
        return 0;
    }
}

