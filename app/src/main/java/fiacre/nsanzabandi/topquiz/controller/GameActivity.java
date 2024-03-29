package fiacre.nsanzabandi.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import fiacre.nsanzabandi.topquiz.R;
import fiacre.nsanzabandi.topquiz.model.Question;
import fiacre.nsanzabandi.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView questionTextView;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;

    private QuestionBank questionBank;
    private Question currentQuestion;
    private int score;
    private int numberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean enableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            numberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            score = 0;
            numberOfQuestions = 4;
        }

        enableTouchEvents = true;
        // Retrieve the widgets
        questionTextView = findViewById(R.id.activity_game_question_text);
        answerButton1 = findViewById(R.id.activity_game_answer1_btn);
        answerButton2 = findViewById(R.id.activity_game_answer2_btn);
        answerButton3 = findViewById(R.id.activity_game_answer3_btn);
        answerButton4 = findViewById(R.id.activity_game_answer4_btn);

        // Name buttons using the tag property
        answerButton1.setTag(0);
        answerButton2.setTag(1);
        answerButton3.setTag(2);
        answerButton4.setTag(3);

        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);

        currentQuestion = questionBank.getQuestion();
        this.displayQuestion(currentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_EXTRA_SCORE, score);
        outState.putInt(BUNDLE_STATE_QUESTION, numberOfQuestions);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == currentQuestion.getAnswerIndex()) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        enableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (--numberOfQuestions == 0) {
                    endGame();
                } else {
                    currentQuestion = questionBank.getQuestion();
                    displayQuestion(currentQuestion);
                }
            }
        }, 2000);
        enableTouchEvents = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return enableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well done!");
        builder.setMessage("Your score is: " + score);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * Generate the questionBank to use in our application.
     * @author fnsanzabandi
     */
    private QuestionBank generateQuestions() {
        // TODO: Rename well all the functions
        Question question1 = new Question("Question1", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 1);
        Question question2 = new Question("Question2", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 2);
        Question question3 = new Question("Question3", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 3);
        Question question4 = new Question("Question4", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 0);
        Question question5 = new Question("Question5", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 1);
        Question question6 = new Question("Question6", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 2);
        Question question7 = new Question("Question7", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 3);
        Question question8 = new Question("Question8", Arrays.asList("choice1", "choice2", "choice3", "choice4"), 0);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8));
    }

    /**
     * Displays the question passed in parameter.
     * @param question
     * @author fnsanzabandi
     */
    private void displayQuestion(Question question) {
        questionTextView.setText(question.getQuestion());
        answerButton1.setText(question.getChoices().get(0));
        answerButton2.setText(question.getChoices().get(1));
        answerButton3.setText(question.getChoices().get(2));
        answerButton4.setText(question.getChoices().get(3));
    }
}
