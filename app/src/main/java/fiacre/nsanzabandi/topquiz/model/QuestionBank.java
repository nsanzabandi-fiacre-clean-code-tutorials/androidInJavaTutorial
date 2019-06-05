package fiacre.nsanzabandi.topquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents all the questions in our app.
 * @author fnsanzabandi
 */
public class QuestionBank {

    private List<Question> questions;
    private int nextQuestionIndex;

    public QuestionBank () {
        super();
    }

    public QuestionBank(List<Question> questions, int nextQuestionIndex) {
        this.questions = questions;
        this.nextQuestionIndex = nextQuestionIndex;
    }

    public QuestionBank(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(questions);
        this.nextQuestionIndex = 0;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNextQuestionIndex() {
        return nextQuestionIndex;
    }

    public void setNextQuestionIndex(int nextQuestionIndex) {
        this.nextQuestionIndex = nextQuestionIndex;
    }

    /**
     * Retrieves the next question from the question list.
     * @return Question
     * @author fnsanzabandi
     */
    public Question getQuestion() {
        if (nextQuestionIndex == questions.size()) {
            nextQuestionIndex = 0;
        }

        return questions.get(nextQuestionIndex++);
    }

    @Override
    public String toString() {
        return "QuestionBank{" +
                "questions=" + questions +
                ", nextQuestionIndex=" + nextQuestionIndex +
                '}';
    }
}
