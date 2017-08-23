package com.example.android.geoquiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HardQuestions extends AppCompatActivity {
    int score = 0;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();
    ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUiComponents();
    }

    private void createUiComponents() {
        addQuestions();
        setContentView(R.layout.activity_hard_questions);

        final ScrollView mainView = (ScrollView) findViewById(R.id.mainLayoutHard);
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

            EditText editText = new EditText(this);
            editText.setId(i);
            editText.setHint("write answer here");
            editTexts.add(editText);
            linearLayout.addView(editText);
            layouts.add(linearLayout);
        }
        for (int k = 0; k < layouts.size(); k++) {
            mainLinearLayout.addView(layouts.get(k));
        }
        Button submitButton = new Button(this);
        setStyleBottom(submitButton);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeTotalScore();
            }
        });
        mainLinearLayout.addView(submitButton);

        Button restartButton = new Button(this);
        setStyleBottom(restartButton);
        restartButton.setText("Restart");
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartScore();
                uncolrAnswers();
                mainView.pageScroll(View.FOCUS_UP);
            }
        });
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

    private void addQuestions() {
        questions.add(new Question(getStringFromResources(R.string.text_Q1_hard), getStringFromResources(R.string.Q1_hard_right_answer), 7));
        questions.add(new Question(getStringFromResources(R.string.text_Q2_hard), getStringFromResources(R.string.Q2_hard_right_answer), 9));
        questions.add(new Question(getStringFromResources(R.string.text_Q3_hard), getStringFromResources(R.string.Q3_hard_right_answer), 6));
        questions.add(new Question(getStringFromResources(R.string.text_Q4_hard), getStringFromResources(R.string.Q4_hard_right_answer), 8));
        questions.add(new Question(getStringFromResources(R.string.text_Q5_hard), getStringFromResources(R.string.Q5_hard_right_answer), 10));
    }

    public void restartScore() {
        setRadioButtonsClickable();
        Toast.makeText(this, "Start the quiz !", Toast.LENGTH_SHORT).show();
    }

    private String getStringFromResources(int resourceId) {
        return getResources().getString(resourceId);
    }

    private void colorAnswers() {
        for (EditText editText : editTexts) {
            if (addScoreQuestion(editText) == 0) {
                editText.setBackgroundResource(R.color.colorWrongAnswer);
            } else {
                editText.setBackgroundResource(R.color.colorRightAnswer);
            }
        }
    }

    private void uncolrAnswers() {
        for (EditText editText : editTexts) {
            editText.setBackgroundResource(R.color.colorBackground);
        }
    }

    private void setRadioButtonsClickable() {
        for (EditText editText : editTexts) {
            editText.setEnabled(true);
            editText.setText("");
            editText.setHint(getStringFromResources(R.string.write_answer));
        }
    }

    private void enableEditText() {
        for (EditText editText : editTexts) {
            editText.setEnabled(false);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

    }

    public boolean areAllCompleted() {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().equals("")) {
                return false;
            }
        }
        return true;
    }

    private int addScoreQuestion(EditText editText) {
        int editTextIndex = editText.getId();
        String editTextString = editText.getText().toString();
        if (questions.get(editTextIndex).getmCorrectAnswer().equals(editTextString)) {
            int scoreQustion = questions.get(editTextIndex).getmDifficulty();
            return scoreQustion;
        }
        return 0;
    }

    public void computeTotalScore() {
        score = 0;
        if (areAllCompleted() == false) {
            Toast.makeText(this, getStringFromResources(R.string.complete_spaces), Toast.LENGTH_SHORT).show();
            return;
        }
        for (EditText editText : editTexts) {
            int scoreForQuestion = addScoreQuestion(editText);
            score += scoreForQuestion;
        }
        Toast.makeText(this, getStringFromResources(R.string.total)+ score, Toast.LENGTH_SHORT).show();
        enableEditText();
        colorAnswers();
    }
}

