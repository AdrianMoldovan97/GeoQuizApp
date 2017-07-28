package com.example.android.geoquiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Adrian on 7/27/2017.
 */

public class QuizAdapter extends ArrayAdapter<Quiz> {

    public QuizAdapter(Activity context, ArrayList<Quiz> quizzes) {
        super(context, 0, quizzes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        Quiz currentQuiz = getItem(position);
        TextView questionTextView = (TextView) listItemView.findViewById(R.id.questionText);
        questionTextView.setText(currentQuiz.getmText());


        Button userAnswerButton = (Button) listItemView.findViewById(R.id.userAnswer);
        userAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "right answer MEDIUM", Toast.LENGTH_SHORT).show();
            }
        });

        return listItemView;
}

}
