package trojan.XMLParse.Classes;

import java.util.ArrayList;

public class Question {

    private ArrayList<Answer> answer;
    private String questionName;
    private String questionText;
    private int questionTime;
    private int questionDifficulty;
    private String mode;

    public Question(ArrayList<Answer> answer, String questionName, String questionText, int questionTime, int testDifficulty, String mode) {
        this.answer = answer;
        this.questionName = questionName;
        this.questionText = questionText;
        this.questionTime = questionTime;
        this.questionDifficulty = testDifficulty;
        this.mode = mode;
    }

    public ArrayList<Answer> getAnswer() {
        return answer;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getMode() {
        return mode;
    }

    public int getQuestionTime() {
        return questionTime;
    }

    public int getQuestionDifficulty() {
        return questionDifficulty;
    }
}
