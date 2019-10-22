package trojan.GUI;

import trojan.GUI.Extended.JButtonEx;
import trojan.Main;
import trojan.XMLParse.Parse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ClassLoadingMXBean;
import java.util.Arrays;

class EditPanel extends JPanel implements ActionListener{


    private JPanel gridMain = new JPanel( new GridLayout(Parse.test.getQuestion().size()+1,1));

    private JButton btnBack = new JButton("Назад");
    private JButton btnОК = new JButton("Ок");

    private JButton btnBackAnswer = new JButton("Назад");
    private JButton btnОКAnswer = new JButton("Ок");

    private JButtonEx btnAnswer;
    private JButtonEx btnQuestion;
    private int tmpIndexQ = 0;
    private int tmpIndexA = 0;
    private String tmpTextAnswer;
    private int tmpTextRating;
    private JTextArea text;
    private JTextArea textR;

     EditPanel() {
         mainPanel();

         btnBack.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 gridMain.removeAll();
                 repaint();
                 mainPanel();
                 gridMain.updateUI();
                 MainWindow.grid.removeAll();
                 repaint();
                 MainWindow.generateCS();
                 MainWindow.grid.updateUI();
             }
         });

         btnОК.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

             }
         });

         btnBackAnswer.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 gridMain.removeAll();
                 repaint();
                 questionPanel(tmpIndexQ);
                 gridMain.updateUI();
             }
         });

    }

    private void mainPanel(){

        for (int i = 0; i < Parse.test.getQuestion().size(); i++) {
            btnQuestion = new JButtonEx();
            btnQuestion.setText(Parse.test.getQuestion().get(i).getQuestionText());
            btnQuestion.setId(i);
            btnQuestion.setFont(new Font(btnQuestion.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
            gridMain.add(btnQuestion);
            btnQuestion.addActionListener(actionListenerQ);
        }
        btnQuestion.setText("Добавить вопрос");
        btnQuestion.setFont(new Font(btnQuestion.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
        btnQuestion.setBackground(Color.BLUE);
        gridMain.add(btnQuestion);
        add(gridMain);
    }

    private ActionListener actionListenerQ = new ActionListener()
    {
        public void actionPerformed(ActionEvent actionEvent) {
            JButtonEx button = (JButtonEx)actionEvent.getSource();
            int index = button.getId();
            remove(gridMain);
            repaint();
            questionPanel(index);
            gridMain.updateUI();
        }
    };

     private void actoinBtn(int indexQ){
         ActionListener actionListenerA = new ActionListener() {
             public void actionPerformed(ActionEvent actionEvent) {
                 JButtonEx button = (JButtonEx) actionEvent.getSource();
                 int index = button.getId();
                 gridMain.removeAll();
                 gridMain.repaint();
                 answerPanel(indexQ, index);
                 gridMain.updateUI();
             }
         };
         btnAnswer.addActionListener(actionListenerA);
     }



    private void questionPanel(int index){
        gridMain = new JPanel( new GridLayout(Parse.test.getQuestion().size()+1,1));
        JLabel questName = new JLabel(Parse.test.getQuestion().get(index).getQuestionText());
        questName.setFont(new Font(questName.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
        gridMain.add(questName);
         for (int i = 0; i < Parse.test.getQuestion().get(index).getAnswer().size(); i++) {
             btnAnswer =new JButtonEx();
             btnAnswer.setText(Parse.test.getQuestion().get(index).getAnswer().get(i).getAnswerText());
             btnAnswer.setId(i);
             btnAnswer.setFont(new Font(btnAnswer.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
             gridMain.add(btnAnswer);
             actoinBtn(index);


         }
         btnAnswer.setText("Добавить вариант ответа");
        btnAnswer.setFont(new Font(btnAnswer.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
        btnAnswer.setBackground(Color.BLUE);
//             if (index == Parse.test.getQuestion().get(index).getAnswer().size()){
//                 actoinBtn(index);
//             } actoinBtn(index+1);

         gridMain.add(btnAnswer);
         add(gridMain);
         MainWindow.grid.removeAll();
         repaint();
         MainWindow.grid.add(btnОК);
         MainWindow.grid.add(btnBack);
         MainWindow.grid.updateUI();
    }



    private void answerPanel(int indexQ, int indexA){
         tmpIndexQ = indexQ;
         tmpIndexA = indexA;
         gridMain = new JPanel( new GridLayout(2,2));
          text = new JTextArea();
         text.setText(Parse.test.getQuestion().get(indexQ).getAnswer().get(indexA).getAnswerText());
         text.setFont(new Font(text.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
         JLabel label = new JLabel("Текст ответа: ");
         label.setFont(new Font(label.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
        textR = new JTextArea();
        textR.setText(String.valueOf((int)Parse.test.getQuestion().get(indexQ).getAnswer().get(indexA).getAnswerRating()));
        textR.setFont(new Font(textR.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
        JLabel labelR = new JLabel("Количество балов: ");
        labelR.setFont(new Font(labelR.getFont().getName(), Font.PLAIN, MainWindow.fontSize));
         gridMain.add(label);
         gridMain.add(text);
        gridMain.add(labelR);
        gridMain.add(textR);
        MainWindow.grid.removeAll();
        repaint();
        MainWindow.grid.add(btnОКAnswer);
        btnОКAnswer.addActionListener(this);
        MainWindow.grid.add(btnBackAnswer);
        MainWindow.grid.updateUI();
         add(gridMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Parse.test.getQuestion().get(tmpIndexQ).getAnswer().get(tmpIndexA).setAnswerText(text.getText());
        Parse.test.getQuestion().get(tmpIndexQ).getAnswer().get(tmpIndexA).setAnswerRating(Integer.parseInt(textR.getText()));
        gridMain.removeAll();
        repaint();
        questionPanel(tmpIndexQ);
        gridMain.updateUI();
    }
}
