package com.example.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Adrian on 7/27/2017.
 */

public class EasyQuestions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_questions);
        ArrayList<Quiz> questions = new ArrayList<Quiz>();
        questions.add(new Quiz("care e cel mai inalt munte", "himalaia", 2));
        questions.add(new Quiz("care e cel mai inalt munte", "himalaia", 2));
        questions.add(new Quiz("care e cel mai inalt munte", "himalaia", 2));
        questions.add(new Quiz("care e cel mai inalt munte", "himalaia", 2));
        questions.add(new Quiz("care e cel mai inalt munte", "himalaia", 2));

        QuizAdapter quizAdapter= new QuizAdapter(this,questions);
        ListView listView = (ListView) findViewById(R.id.listEasy);
        listView.setAdapter(quizAdapter);
    }
}
