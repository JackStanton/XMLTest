package trojan.XMLParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OutXML {

    public static void write(String filename) throws TransformerException, ParserConfigurationException, FileNotFoundException {

        // здесь создаем документ
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = factory.newDocumentBuilder();
        Document doc = build.newDocument();

        Element rootElement = doc.createElement("test");

        rootElement.setAttribute("name", Parse.test.getTestName());
        rootElement.setAttribute("theme", Parse.test.getTestTheme());
        rootElement.setAttribute("countQuestions", String.valueOf(Parse.test.getCountOfQuestions()));
        rootElement.setAttribute("useQuestions", String.valueOf(Parse.test.getCountOfUsedQuestions()));
        rootElement.setAttribute("time", String.valueOf(Parse.test.getTestTime()));
        rootElement.setAttribute("balls", String.valueOf(Parse.test.getTestBalls()));
        doc.appendChild(rootElement);

        for (int i = 0; i < Parse.test.getQuestion().size(); i++) {
            Element question = doc.createElement("question");
            question.setAttribute("name", Parse.test.getQuestion().get(i).getQuestionName());
            question.setAttribute("time", String.valueOf(Parse.test.getQuestion().get(i).getQuestionTime()));
            question.setAttribute("difficulty", String.valueOf(Parse.test.getQuestion().get(i).getQuestionDifficulty()));
            question.setAttribute("mode", Parse.test.getQuestion().get(i).getMode());
            rootElement.appendChild(question);
            Element questionText = doc.createElement("questionText");
            String value = Parse.test.getQuestion().get(i).getQuestionText();
            Text textNode = doc.createTextNode(value);
            questionText.appendChild(textNode);
            question.appendChild(questionText);
            for (int j = 0; j < Parse.test.getQuestion().get(i).getAnswer().size(); j++) {
                Element answer = doc.createElement("answer");
                answer.setAttribute("name", Parse.test.getQuestion().get(i).getAnswer().get(j).getAnswerName());
                answer.setAttribute("rating", String.valueOf((int)Parse.test.getQuestion().get(i).getAnswer().get(j).getAnswerRating()));
                Text answerText = doc.createTextNode(Parse.test.getQuestion().get(i).getAnswer().get(j).getAnswerText());
                answer.appendChild(answerText);
                question.appendChild(answer);
            }
        }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(filename)));
    }

}
