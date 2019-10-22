package trojan.GUI;

import trojan.GUI.Extended.*;
import trojan.XMLParse.Classes.Answer;
import trojan.XMLParse.OutXML;
import trojan.XMLParse.Parse;
import trojan.XMLParse.Classes.Question;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class MainWindow extends JFrame{

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_POSITION_X = 300;
    private static final int WINDOW_POSITION_Y = 100;
    private static File fileName;
    static int fontSize = 17;

    private static String testName = " ";
    private static String testTheme = " ";
    private static  int countOfQuestions = 0;
    private static  int countOfUsedQuestions = 0;
    private static  int testTime = 0;
    private static  double testBalls = 0;


    private static String questionName;
    private static String questionText;
    private static String questionMode;
    private static int questionTime;
    private static int testDifficulty;

    private static String answerName;
    private static String answerText;
    private static double answerRating;

    private static ArrayList<Question> questionsArr;
    private static  ArrayList<Answer> answerArr;
    private static ButtonGroupEx[] group;
    private static ButtonModel[] trueValues;
    private static JCheckBoxEx[][] checkBoxArray;
    private static JCheckBoxEx[][] trueCheckBoxArray;
    private static JTextFieldEx[] textFields;
    private static String[] trueTextValues;

//    private EditPanel editPanel = new EditPanel();
//    private ArrayList<ButtonGroup> group = new ArrayList<>();
//    private ArrayList<ButtonModel> trueValues = new ArrayList<>();



    private static JPanel gridMain = new JPanel( new GridLayout(1,1));
    static JPanel grid = new JPanel( new GridLayout(1,2) );
    private static JPanel labelPanel = new JPanel(new GridLayout(countOfQuestions,1));

    private static JButton btnOpen = new JButton("Открыть");
    private static JButton btnStart = new JButton("Начать");
    private static JButton btnEnd = new JButton("Закончить");
    private static JButton btnOk = new JButton("Ок");
    private static JButton btnEdit = new JButton("Редактировать");
    private static JButton btnCancel = new JButton("Отмена");
    private static JButton btnSave = new JButton("Сохранить");


    private static JLabel testNameLabel = new JLabel();
    private static JLabel testThemeLabel = new JLabel();
    private static JLabel testCountQuestLabel = new JLabel();
    private static JLabel testCountUseQuestLabel = new JLabel();
    //        JLabel testTimeLabel = new JLabel();
    private static JLabel testBallsLabel = new JLabel();

    private static JLabel resBalls = new JLabel();
    private static JLabel resPer = new JLabel();


    private static JScrollPane scrollPane;
    private EditPanel editPanel;





    public MainWindow(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSITION_X,WINDOW_POSITION_Y);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Тест");


        btnStart.setEnabled(false);
        btnEdit.setEnabled(false);


        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                fileName = new File("");
                String message;
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(new JFileChooser());
                fileName = chooser.getSelectedFile();
                if (fileName != null){
                    String string = chooser.getSelectedFile().toString();
                    string = string.substring(string.lastIndexOf('.')+1,string.length());
                    if (string.equals("xml")){

                        generateTestLabels();
                        setTitle("Тест - "+testName);
                        setVisible(true);
                    } else{
                        message = "Выбранный файл не является XML!";
                        JOptionPane.showMessageDialog(MainWindow.this, message);
                    }
                } else{
                    message = "Файл не выбран!";
                    JOptionPane.showMessageDialog(MainWindow.this, message);
                }
            repaint();
            }

        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelPanel.removeAll();
                group = new ButtonGroupEx[countOfUsedQuestions];
                trueValues = new ButtonModel[countOfUsedQuestions];
                checkBoxArray = new JCheckBoxEx[countOfUsedQuestions][];
                textFields = new JTextFieldEx[countOfUsedQuestions];
                trueTextValues = new String[countOfUsedQuestions];
                for (int i = 0; i < countOfUsedQuestions; i++) {
                    answerArr = Parse.test.getQuestion().get(i).getAnswer();
                    Collections.shuffle(answerArr);
                    getQuestInfo(i);
                    JLabel questLabel = new JLabel(i+1+") "+questionText);
                    questLabel.setFont(new Font(questLabel.getFont().getName(), Font.PLAIN, fontSize));
                    labelPanel.add(questLabel);
//                    JTextArea questArea = new JTextArea();
//                    questArea.setEditable(false);
//                    questArea.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//                    questArea.setLineWrap(true);
//                    questArea.setText(i+1+") "+questionText);
//                    questArea.setFont(new Font(questArea.getFont().getName(), Font.PLAIN, fontSize));
//                    labelPanel.add(questArea);
                    ButtonGroupEx btngr = new ButtonGroupEx();
                    group[i] = btngr;

                    if (questionMode.equals("single")) {
                        for (int j = 0; j < Parse.test.getQuestion().get(i).getAnswer().size(); j++) {

                            getAnswerInfo(j);
                            JRadioButtonEx answerButton = new JRadioButtonEx();
                            answerButton.setText(answerText);
                            answerButton.setFont(new Font(answerButton.getFont().getName(), Font.PLAIN, fontSize));
//                            group.get(i).add(answerButton);

                            if (answerRating > 0){
                                group[i].setRating(answerRating);
                            }

                            group[i].add(answerButton);
                            labelPanel.add(answerButton);
//                            if (answerRating>0)trueValues.add(answerButton.getModel());
                            if (answerRating>0){
                                trueValues[i] = answerButton.getModel();
                            }
                        }
                    }
                    checkBoxArray[i] = new JCheckBoxEx[Parse.test.getQuestion().get(i).getAnswer().size()];
                    if (questionMode.equals("multiply")) {

                        for (int j = 0; j < Parse.test.getQuestion().get(i).getAnswer().size(); j++) {
                            getAnswerInfo(j);
                            JCheckBoxEx answerBox = new JCheckBoxEx();
                            answerBox.setText(answerText);
                            answerBox.setRating(answerRating);
                            answerBox.setFont(new Font(answerBox.getFont().getName(), Font.PLAIN, fontSize));
                            checkBoxArray[i][j] = answerBox;
                            labelPanel.add(answerBox);
                        }
                    }
                    if (questionMode.equals("insert")) {
                        for (int j = 0; j < Parse.test.getQuestion().get(i).getAnswer().size(); j++) {
                            getAnswerInfo(j);
                            JTextFieldEx answerField = new JTextFieldEx();
                            answerField.setFont(new Font(answerField.getFont().getName(), Font.PLAIN, fontSize));
                            answerField.setRating(answerRating);
                            textFields[j] = answerField;
                            labelPanel.add(answerField);
                            if(answerRating > 0){
                                trueTextValues[j] = answerText;
                            }
                        }
                    }

                }
                scrollPane = new JScrollPane(labelPanel);
                scrollPane.getVerticalScrollBar().setValue(1);
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                add(scrollPane,BorderLayout.CENTER);
                grid.removeAll();
                grid.add(btnEnd);
                grid.repaint();
                setVisible(true);

            }
        });

        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(scrollPane);
                repaint();
                double balls = 0;
                for (int i = 0; i < group.length; i++) {
                    if((group[i].getSelection() == trueValues[i]) && (group[i].getSelection() != null )&&( trueValues[i] != null)){
                        balls = balls + group[i].getRating();
                    }
                }


                for (JCheckBoxEx[] jCheckBoxes : checkBoxArray) {
                    for (JCheckBoxEx jCheckBox : jCheckBoxes) {
                        if (jCheckBox != null){
                            if (jCheckBox.isSelected()) {
                                if(jCheckBox.getRating()>0) {
                                    balls = balls+jCheckBox.getRating();
                                } else balls = balls - 0.5;
                            }
                        }
                    }
                }

                for (int i = 0; i < trueTextValues.length; i++) {
                    if ((textFields[i] != null )&&(textFields[i].getText().equals(trueTextValues[i]))){
                        balls = balls + textFields[i].getRating();
                    }
                }


                labelPanel.removeAll();

                testBallsLabel.setText("Балов за тест: "+testBalls);
                testBallsLabel.setFont(new Font(testBallsLabel.getFont().getName(), Font.PLAIN, fontSize));
                labelPanel.add(testBallsLabel);

                resBalls.setText("Набранные балы: "+balls);
                resBalls.setFont(new Font(resBalls.getFont().getName(), Font.PLAIN, fontSize));
                labelPanel.add(resBalls);
                String txt = String.format("Процент: %.3g%n", balls/testBalls*100);
                resPer.setText(txt + "%");
                resPer.setFont(new Font(resPer.getFont().getName(), Font.PLAIN, fontSize));
                labelPanel.add(resPer);


                grid.removeAll();
                add(labelPanel,BorderLayout.NORTH);
                grid.add(btnOk);
                gridMain.repaint();
                setVisible(true);


            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelPanel.removeAll();
                generateTestLabels();
                grid.removeAll();
                generateOSE();
                setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel = new EditPanel();
                remove(labelPanel);
                add(editPanel);
                grid.removeAll();
                generateCS();
                repaint();
                setVisible(true);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(editPanel);
                generateTestLabels();
                grid.removeAll();
                generateOSE();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(labelPanel);
                if (fileChooser.getSelectedFile() != null){
                    try {
                        OutXML.write(fileChooser.getSelectedFile().toString());
                    } catch (TransformerException | FileNotFoundException | ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });



        generateOSE();
        add(gridMain, BorderLayout.SOUTH);

        setVisible(true);
    }


    private static void getTestInfo(){
        testName = Parse.test.getTestName();
        testTheme = Parse.test.getTestTheme();
        countOfQuestions = Parse.test.getCountOfQuestions();
        countOfUsedQuestions = Parse.test.getCountOfUsedQuestions();
        testTime = Parse.test.getTestTime()/60;
//        testBalls = Parse.test.getTestBalls();
    }

    private static void getQuestInfo(int i){

//            questionName = questionsArr[i].getQuestionName();
            questionText = questionsArr.get(i).getQuestionText();
            questionMode = questionsArr.get(i).getMode();
//            questionTime = questionsArr[i].getQuestionTime();
//            testDifficulty = questionsArr[i].getTestDifficulty();

    }

    private static void getAnswerInfo(int j){
        answerName = answerArr.get(j).getAnswerName();
        answerText = answerArr.get(j).getAnswerText();
        answerRating = answerArr.get(j).getAnswerRating();
    }

    private static void getCountQuestion(){
        testBalls = 0;
        ArrayList<Question> tmp = new ArrayList<>();
        for (int i = 0; i < countOfUsedQuestions; i++) {
            tmp.add(questionsArr.get(i));
            for (int j = 0; j < questionsArr.get(i).getAnswer().size(); j++) {
                testBalls = testBalls + questionsArr.get(i).getAnswer().get(j).getAnswerRating();
            }
        }

        questionsArr = tmp;
    }

    private void generateTestLabels(){
        Parse.parse(fileName.toString());
        getTestInfo();
        questionsArr = Parse.test.getQuestion();


        Collections.shuffle(questionsArr);
        getCountQuestion();

        testNameLabel.setText("Название теста: "+ testName);

        testNameLabel.setFont(new Font(testNameLabel.getFont().getName(), Font.PLAIN, fontSize));
        labelPanel.add(testNameLabel);
        testThemeLabel.setText("Тема теста: "+testTheme);
        testThemeLabel.setFont(new Font(testThemeLabel.getFont().getName(), Font.PLAIN, fontSize));
        labelPanel.add(testThemeLabel);
        testCountQuestLabel.setText("Количество вопростов: "+countOfQuestions);
        testCountQuestLabel.setFont(new Font(testCountQuestLabel.getFont().getName(), Font.PLAIN, fontSize));
        labelPanel.add(testCountQuestLabel);
        testCountUseQuestLabel.setText("Будет задано вопросов: "+countOfUsedQuestions);
        testCountUseQuestLabel.setFont(new Font(testCountUseQuestLabel.getFont().getName(), Font.PLAIN, fontSize));
        labelPanel.add(testCountUseQuestLabel);
//                        testTimeLabel.setText("Время теста: "+ testTime+ " мин");
//                        testTimeLabel.setFont(new Font(testTimeLabel.getFont().getName(), Font.PLAIN, fontSize));
//                        labelPanel.add(testTimeLabel);
        testBallsLabel.setText("Количество баллов: "+ testBalls);
        testBallsLabel.setFont(new Font(testBallsLabel.getFont().getName(), Font.PLAIN, fontSize));
        labelPanel.add(testBallsLabel);
        btnStart.setEnabled(true);
        btnEdit.setEnabled(true);
        add(labelPanel,BorderLayout.NORTH);
    }

    private void generateOSE(){
        grid.add(btnOpen);
        grid.add(btnStart);
        grid.add(btnEdit);
        gridMain.add(grid);
        repaint();
    }

    static void generateCS(){
        grid.add(btnSave);
        grid.add(btnCancel);
    }
}
