package fiacre.nsanzabandi.topquiz.model;

import java.util.List;

/**
 * Represent a question
 * @author fnsanzabandi
 */
public class Question {

    private String question;
    private List<String> choices;
    private int answerIndex;


    public Question(String question, List<String> choices, int answerIndex) {
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", choices=" + choices +
                ", answerIndex=" + answerIndex +
                '}';
    }
}