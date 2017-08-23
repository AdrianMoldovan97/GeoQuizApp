package com.example.android.geoquiz;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EasyQuestions extends AppCompatActivity {
    int score = 0;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUiComponents();
    }

    private void createUiComponents() {
        addQuestions();
        setContentView(R.layout.activity_easy_questions);

        final ScrollView mainView = (ScrollView) findViewById(R.id.mainLayout);
        mainView.setBackgroundResource(R.color.colorBackground);
        final LinearLayout mainLinearLayout = new LinearLayout(this);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < questions.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView questionText = new TextView(this);
            setStyleTextView(questionText);
            questionText.setText(questions.get(i).getmText());
            linearLayout.addView(questionText);

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOrientation(LinearLayout.VERTICAL);
            radioGroup.setId(i);
            for (int j = 0; j < 3; j++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setId(j);
                radioButton.setText(questions.get(i).getmAnswers()[j]);
                radioGroup.addView(radioButton);
            }
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    int answerIndex = group.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) group.getChildAt(answerIndex);
                    questions.get(group.getId()).setSelectedAnswer(radioButton.getText().toString());
                }
            });
            radioGroups.add(radioGroup);
            linearLayout.addView(radioGroup);
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
                if (areAllChecked()) {
                    colorAnswers();
                    setRadioButtonsUnclickable();
                }
            }
        });
        mainLinearLayout.addView(submitButton);

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
        mainLinearLayout.addView(restartButton);
        mainView.addView(mainLinearLayout);
    }

    private void setStyleBottom(Button button) {
        button.setBackgroundResource(R.color.colorButton);
        button.setPadding(6, 6, 6, 6);
    }

    private void setStyleTextView(TextView textView) {
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        textView.setTypeface(null, Typeface.BOLD_ITALIC);
        textView.setPadding(5, 20, 5, 20);
    }

    private void addQuestions() {
        questions.add(new Question(getStringFromResources(R.string.text_Q1_easy), getArrayStringFromResources(R.array.Q1_easy), getStringFromResources(R.string.Q1_easy_answer2), 2));
        questions.add(new Question(getStringFromResources(R.string.text_Q2_easy), getArrayStringFromResources(R.array.Q2_easy), getStringFromResources(R.string.Q2_easy_answer2), 3));
        questions.add(new Question(getStringFromResources(R.string.text_Q3_easy), getArrayStringFromResources(R.array.Q3_easy), getStringFromResources(R.string.Q3_easy_answer3), 1));
        questions.add(new Question(getStringFromResources(R.string.text_Q4_easy), getArrayStringFromResources(R.array.Q4_easy), getStringFromResources(R.string.Q4_easy_answer1), 3));
        questions.add(new Question(getStringFromResources(R.string.text_Q5_easy), getArrayStringFromResources(R.array.Q5_easy), getStringFromResources(R.string.Q5_easy_answer2), 1));
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

    public boolean areAllChecked() {
        for (Question question : questions) {
            if (question.getSelectedAnswer() == null) {
                return false;
            }
        }
        return true;
    }

    public int addScoreQuestion(RadioGroup radioGroup) {
        int questionId = radioGroup.getId();
        if (questions.get(questionId).getmCorrectAnswer().equals(questions.get(questionId).getSelectedAnswer())) {
            return questions.get(questionId).getmDifficulty();
        }
        return 0;
    }

    private void setRadioButtonsUnclickable() {
        for (RadioGroup radioGroup : radioGroups) {
            ;
            for (int i = 0; i < 3; i++) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }
    }

    private void setRadioButtonsClickable() {
        for (RadioGroup radioGroup : radioGroups) {
            for (int i = 0; i < 3; i++) {
                radioGroup.getChildAt(i).setEnabled(true);
            }
        }
    }

    private boolean isCorrectButton(RadioButton radioButton) {
        RadioGroup radioGroup = (RadioGroup) radioButton.getParent();
        if (addScoreQuestion(radioGroup) == 0) {
            return false;
        }
        return true;
    }

    private void uncolorAnswers() {
        for (RadioGroup radioGroup : radioGroups) {
            for (int i = 0; i < 3; i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setBackgroundResource(R.color.colorBackground);
            }
        }
    }

    private void colorAnswers() {
        for (RadioGroup radioGroup : radioGroups) {
            for (int i = 0; i < 3; i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton.isChecked() && isCorrectButton(radioButton)) {
                    radioButton.setBackgroundResource(R.color.colorRightAnswer);
                } else if (radioButton.isChecked() && !isCorrectButton(radioButton)) {
                    radioButton.setBackgroundResource(R.color.colorWrongAnswer);
                }
            }
        }
    }

    public void computeTotalScore() {
        score = 0;
        if (areAllChecked() == false) {
            Toast.makeText(this, getStringFromResources(R.string.check_questions), Toast.LENGTH_SHORT).show();
            return;
        }
        for (Question question : questions) {
            if (question.getmCorrectAnswer().equals(question.getSelectedAnswer())) {
                score += question.getmDifficulty();
            }
        }
        Toast.makeText(this,getStringFromResources(R.string.total) + score, Toast.LENGTH_SHORT).show();
    }

    public void restartScore() {
        setRadioButtonsClickable();
        for (Question question : questions) {
            question.setSelectedAnswer(null);
        }
        Toast.makeText(this, getStringFromResources(R.string.start_app), Toast.LENGTH_SHORT).show();
    }
}





