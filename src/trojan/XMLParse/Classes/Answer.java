package trojan.XMLParse.Classes;

public class Answer {

     private String answerName;
     private String answerText;
     private int answerRating;


    public Answer(String answerName, String answerText, int answerRating){
        this.answerName = answerName;
        this.answerText = answerText;
        this.answerRating = answerRating;
    }

     public String getAnswerName() {
         return answerName;
     }

     public  String getAnswerText() {
         return answerText;
     }

     public int getAnswerRating() {
         return answerRating;
     }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setAnswerRating(int answerRating) {
        this.answerRating = answerRating;
    }
}
