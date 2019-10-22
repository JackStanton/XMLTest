package trojan.XMLParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import trojan.XMLParse.Classes.Answer;
import trojan.XMLParse.Classes.Question;
import trojan.XMLParse.Classes.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public  class Parse {

    public static Test test;

    public static void parse(String filename) {
        try {

            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList testList = doc.getElementsByTagName("test");
            Element testNode = (Element) testList.item(0);

            String tName = testNode.getAttribute("name");
            String tTheme = testNode.getAttribute("theme");
            int tCountQuestions = 0;
//            int tCountQuestions = Integer.parseInt(testNode.getAttribute("countQuestions"));
            int tUseQuestions = Integer.parseInt(testNode.getAttribute("useQuestions"));
            int tTime = 0;
//            int tTime = Integer.parseInt(testNode.getAttribute("time"));
            int tBalls = 0;
//            int tBalls = Integer.parseInt(testNode.getAttribute("balls"));

            NodeList nList = doc.getElementsByTagName("question");
            ArrayList<Question> questionArray = new ArrayList<>();
            tCountQuestions = nList.getLength();
            for (int i = 0; i < nList.getLength(); i++) {
                Element nNode = (Element) nList.item(i);
                String qName = nNode.getAttribute("name");
                String qText = nNode.getElementsByTagName("questionText").item(0).getTextContent();
                int qTime = Integer.parseInt(nNode.getAttribute("time"));
                tTime = tTime + qTime;
                int qDif = Integer.parseInt(nNode.getAttribute("difficulty"));
                String qMode = nNode.getAttribute("mode");
                ArrayList<Answer> answerArray = new ArrayList<>();
                for (int j = 0; j < nNode.getElementsByTagName("answer").getLength(); j++) {
                    Element answerElem = (Element)nNode.getElementsByTagName("answer").item(j);
                    String aName = answerElem.getAttribute("name");
                    String aText = nNode.getElementsByTagName("answer").item(j).getTextContent();
                    int aRating = Integer.parseInt(answerElem.getAttribute("rating"));
                    if (aRating > 0) tBalls = tBalls + aRating;
                    Answer answer = new Answer(aName,aText,aRating);
                    answerArray.add(answer);
                }
                Question question = new Question(answerArray,qName,qText,qTime,qDif,qMode);
                questionArray.add(question);
            }
            test = new Test(questionArray,tName,tTheme,tCountQuestions,tUseQuestions,tTime,tBalls);

        } catch (Exception ignored) {
        }

    }
}
