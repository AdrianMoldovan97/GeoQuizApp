package com.example.android.geoquiz;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.android.geoquiz.R.id.hardQuestionsButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUIComponents();

    }

    private void setUIComponents(){
        setContentView(R.layout.activity_main);

        Button easyQuestionsButton = (Button) findViewById(R.id.easyQuestionsButton);
        easyQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent easyQuestionsIntent = new Intent(MainActivity.this, EasyQuestions.class);
                startActivity(easyQuestionsIntent);
            }
        });

        Button mediumQuestionsButton = (Button) findViewById(R.id.mediumQuestionsButton);
        mediumQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mediumQuestionsIntent= new Intent(MainActivity.this, MediumQuestions.class);
                startActivity(mediumQuestionsIntent);
            }
        });

        Button hardQuestionsButton = (Button) findViewById(R.id.hardQuestionsButton);
        hardQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hardQuestionsIntent= new Intent(MainActivity.this, HardQuestions.class);
                startActivity(hardQuestionsIntent);
            }
        });
    }
}
