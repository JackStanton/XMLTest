package trojan.XMLParse.Classes;

import java.util.ArrayList;

public class Test {

    private ArrayList<Question> question;
    private String testName;
    private String testTheme;
    private int countOfQuestions;
    private int countOfUsedQuestions;
    private int testTime;
    private int testBalls;

    public  Test(ArrayList<Question> question, String testName, String testTheme, int countOfQuestions, int countOfUsedQuestions, int testTime, int testBalls) {
        this.question = question;
        this.testName = testName;
        this.testTheme = testTheme;
        this.countOfQuestions = countOfQuestions;
        this.countOfUsedQuestions = countOfUsedQuestions;
        this.testTime = testTime;
        this.testBalls = testBalls;
    }

    public ArrayList<Question> getQuestion() {
        return question;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestTheme() {
        return testTheme;
    }

    public int getCountOfQuestions() {
        return countOfQuestions;
    }

    public int getCountOfUsedQuestions() {
        return countOfUsedQuestions;
    }

    public int getTestTime() {
        return testTime;
    }

    public int getTestBalls() {
        return testBalls;
    }
}
