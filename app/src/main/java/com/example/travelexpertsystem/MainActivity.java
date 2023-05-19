package com.example.travelexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup preferenceRadioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button nextButton;
    private Button restartButton;

    private int currentQuestionIndex = 0;
    private String[] questions = {
            "What is your preferred type of travel destination?",
            "What is your budget range?",
            "What activities are you interested in?"
    };

    private String[] answers = {
            "",
            "",
            ""
    };
    private String[][] options = {
            {"Beach", "City", "Nature"},
            {"Low", "Medium", "High"},
            {"Hiking", "Sightseeing", "Water sports"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionTextView = findViewById(R.id.questionTextView);
        preferenceRadioGroup = findViewById(R.id.preferenceRadioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        nextButton = findViewById(R.id.nextButton);
        restartButton = findViewById(R.id.restartButton);

        updateQuestion();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = preferenceRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(radioButtonId);
                answers[currentQuestionIndex] = selectedRadioButton.getText().toString();

                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    updateQuestion();
                } else {
                    showResult();

                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = 0;
                for (int i = 0; i < answers.length; i++) {
                    answers[i] = "";
                }
                preferenceRadioGroup.clearCheck();
                updateQuestion();
                restartButton.setVisibility(View.GONE);
            }
        });
    }

    private void updateQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);

        radioButton1.setText(options[currentQuestionIndex][0]);
        radioButton2.setText(options[currentQuestionIndex][1]);
        radioButton3.setText(options[currentQuestionIndex][2]);

        preferenceRadioGroup.clearCheck();
    }

    private void showResult() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Travel Recommendation:\n\n");

        resultBuilder.append("Destination: ");
        if (answers[0].equals("Beach")) {
            resultBuilder.append("Algiers");
        } else if (answers[0].equals("City")) {
            resultBuilder.append("Oran");
        } else if (answers[0].equals("Nature")) {
            resultBuilder.append("Tassili n'Ajjer National Park");
        }
        resultBuilder.append("\n");

        resultBuilder.append("Accommodation: ");
        if (answers[1].equals("Low")) {
            resultBuilder.append("Budget hotel");
        } else if (answers[1].equals("Medium")) {
            resultBuilder.append("3-star hotel");
        } else if (answers[1].equals("High")) {
            resultBuilder.append("Luxury resort");
        }
        resultBuilder.append("\n");



        resultBuilder.append("Activities:  ");
        if (answers[2].equals("Hiking")) {
            resultBuilder.append("Hiking in the mountains");
        } else if (answers[2].equals("Sightseeing")) {
            resultBuilder.append("City sightseeing tours");
        } else if (answers[2].equals("Water sports")) {
            resultBuilder.append("Snorkeling and scuba diving");
        }
        resultBuilder.append("\n");

        questionTextView.setText(resultBuilder.toString());

        nextButton.setEnabled(false);
        restartButton.setVisibility(View.VISIBLE);
    }

}