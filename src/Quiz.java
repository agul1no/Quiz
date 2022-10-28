import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Quiz implements ActionListener {

    String[] questions =    {      // Array with the questions
                                "Von Welchem Unternehmen wurde Java entwickelt?",
                                "In welchem Jahr erschien die erste Version von Java?",
                                "Wie wurde Java ursprünglich benannt?",
                                "Wer gilt als der Hauptentwickler von Java?",
                            };
    String[][] options =    {     //this is an array of arrays
                                {"Apple","Sun Microsystems","Alphabet","Microsoft"},
                                {"1492","1992","1996","1985"},
                                {"Oak","Cappuccino","Bytecode","01001101"},
                                {"Bill Gates","Mark Zuckerberg","Scooby-Doo","James Gosling"},
                            };
    char[] answers =        {
                                'B',
                                'C',
                                'A',
                                'D',
                            };
    char guess;  //will hold whatever we guess
    char answer; //will hold the answer
    int index;
    int correctGuesses = 0;
    int totalQuestions = questions.length; //this will adjust the number to number of arrays we have in the array questions
    double result;
    int seconds = 30;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerLabelA = new JLabel();
    JLabel answerLabelB = new JLabel();
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentage = new JTextField();

    //Timer for answering the questions

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            secondsLeft.setText(String.valueOf(seconds));
            if(seconds<=0){
                displayAnswer();
            }
        }
    });

    public Quiz(){        //Constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        // ursprünglich war frame.setVisible(true) hier aber der textField wurde nicht angezeigt
        frame.setLocationRelativeTo(null);
        frame.setTitle("Java Quiz");
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(50,50,50)); //Background Color of the frame
        frame.setLayout(null); //for setting everything by hand

        textField.setBounds(0,0,650,76); // Position and Size
        textField.setBackground(new Color(25,25,25)); //Background color
        textField.setForeground(new Color(67, 246, 57, 161)); //Font Color
        textField.setFont(new Font("Ink Free",Font.BOLD,50)); //Font.Plain / Italic / Bold
        textField.setBorder(BorderFactory.createBevelBorder(1));  //
        textField.setHorizontalAlignment(JTextField.CENTER);  // Center the text in the Box
        textField.setEditable(false);                           // not editable

        textarea.setBounds(0,76,650,90);
        textarea.setLineWrap(true);   //in case the text goes out of the textarea is going to wrap around to the next line
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(67, 246, 57, 161));
        textarea.setFont(new Font("MV Boil", Font.BOLD,31));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0,166,100,100);
        buttonA.setFont(new Font("MV Boil", Font.BOLD,40));
        buttonA.setFocusable(false); //false -> button is not highlighted
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,266,100,100);
        buttonB.setFont(new Font("MV Boil", Font.BOLD,40));
        buttonB.setFocusable(false); //false -> button is not highlighted
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,366,100,100);
        buttonC.setFont(new Font("MV Boil", Font.BOLD,40));
        buttonC.setFocusable(false); //false -> button is not highlighted
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,466,100,100);
        buttonD.setFont(new Font("MV Boil", Font.BOLD,40));
        buttonD.setFocusable(false); //false -> button is not highlighted
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answerLabelA.setBounds(125,165,500,100);
        answerLabelA.setBackground(new Color(50,50,50));
        answerLabelA.setForeground(new Color(25,255,0, 190));
        answerLabelA.setFont(new Font("MV Boil", Font.PLAIN,40));

        answerLabelB.setBounds(125,265,500,100);
        answerLabelB.setBackground(new Color(50,50,50));
        answerLabelB.setForeground(new Color(25,255,0, 190));
        answerLabelB.setFont(new Font("MV Boil", Font.PLAIN,40));

        answerLabelC.setBounds(125,365,500,100);
        answerLabelC.setBackground(new Color(50,50,50));
        answerLabelC.setForeground(new Color(25,255,0, 191));
        answerLabelC.setFont(new Font("MV Boil", Font.PLAIN,40));

        answerLabelD.setBounds(125,465,500,100);
        answerLabelD.setBackground(new Color(50,50,50));
        answerLabelD.setForeground(new Color(25,255,0, 191));
        answerLabelD.setFont(new Font("MV Boil", Font.PLAIN,40));

        secondsLeft.setBounds(535,510,100,100);
        secondsLeft.setBackground(new Color(25,25,25));
        secondsLeft.setForeground(new Color(194, 10, 10, 216));
        secondsLeft.setFont(new Font("MV Boil", Font.BOLD,60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));  //String.valueOf(seconds) converts the int seconds into a String

        timeLabel.setBounds(535,480,100,25);
        timeLabel.setBackground(new Color(50,50,50));
        timeLabel.setForeground(new Color(255,0,0, 174));
        timeLabel.setFont(new Font("MV Boil", Font.PLAIN,20));
        //timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("Timer");

        numberRight.setBounds(225,225,200,100);
        numberRight.setBackground(new Color(25,25,25));
        numberRight.setForeground(new Color(25,255,0, 203));
        numberRight.setFont(new Font("Ink Free",Font.BOLD,50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0, 203));
        percentage.setFont(new Font("Ink Free",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();        //we call the nextQuestion method to begin our Quiz
    }
    public void nextQuestion() {
        if (index >= totalQuestions) {    //checks if the quiz its over or it should continue with the next question
            results();
        } else {
            textField.setText("Frage "+(index+1));
            textarea.setText(questions[index]);
            answerLabelA.setText(options[index][0]);
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {   //anything related to button clicking will be included in this method

        buttonA.setEnabled(false);           //all buttons are disabled at the beginning
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){                //check the input from user and compares it with the array answers
            answer= 'A';                          // if the answer is correct correctGuesses will be incremented by one
            if(answer== answers[index]){
                correctGuesses++;
            }
        }

        if(e.getSource()==buttonB){
            answer= 'B';
            if(answer== answers[index]){
                correctGuesses++;
            }
        }

        if(e.getSource()==buttonC){
            answer= 'C';
            if(answer== answers[index]){
                correctGuesses++;
            }
        }

        if(e.getSource()==buttonD){
            answer= 'D';
            if(answer== answers[index]){
                correctGuesses++;
            }
        }
            displayAnswer();     //at the end we are going to display the answer
    }

    public void displayAnswer(){

        timer.stop();
        buttonA.setEnabled(false);           //all buttons are disabled at the beginning
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')         // check if the answer is correct and if it isn't correct changes the color to red
            answerLabelA.setForeground(new Color(255,0,0, 204));
        if(answers[index] != 'B')
            answerLabelB.setForeground(new Color(255,0,0, 204));
        if(answers[index] != 'C')
            answerLabelC.setForeground(new Color(255,0,0, 204));
        if(answers[index] != 'D')
            answerLabelD.setForeground(new Color(255,0,0, 204));

        //creating a timer after the user have chosen an answer for setting the color of the answers to green again

        Timer pause = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                answerLabelA.setForeground(new Color(25,255,0, 204));
                answerLabelB.setForeground(new Color(25,255,0, 204));
                answerLabelC.setForeground(new Color(25,255,0, 204));
                answerLabelD.setForeground(new Color(25,255,0, 204));

                answer = ' '; //reset the answer
                seconds = 30;
                secondsLeft.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);  //our timer pause will get only executed once and not every 3 seconds
        pause.start();
    }

    public void results(){
            //at first we have to disable all buttons so the user can not continue clicking on the buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = ((double) correctGuesses/(double) totalQuestions)*100;

        textField.setText("Danke für die Teilnahme!");
        textarea.setText("Hier ist dein Ergebnis :)");
        answerLabelA.setText("");
        answerLabelB.setText("");
        answerLabelC.setText("");
        answerLabelD.setText("");

        numberRight.setText("("+correctGuesses+"/"+totalQuestions+")");  //we placed the labels in the constructor
        percentage.setText(result+"%");                                      //but we didn't added to the frame

        frame.add(numberRight);
        frame.add(percentage);

    }

}
