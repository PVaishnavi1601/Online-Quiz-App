import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizAppGUI extends JFrame implements ActionListener {

    String[] questions = {
        "What is the capital of France?",
        "Which language is used for Android development?",
        "What is 2 + 2?",
        "Which is the largest planet?",
        "What is the output of 3 * 3?",
        "Who created Java?",
        "What does HTML stand for?",
        "What was the first computer virus?",
        "What is the file extension for Java files?",
        "Which is the most used programming language in 2024?"
    };

    String[][] options = {
        {"Berlin", "London", "Paris", "Rome"},
        {"Python", "Java", "C", "Swift"},
        {"3", "4", "5", "6"},
        {"Earth", "Jupiter", "Mars", "Venus"},
        {"6", "9", "12", "3"},
        {"Bill Gates", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"},
        {"HyperText Markup Language", "HighText Machine Language", "Hyper Tool Markup Language", "None"},
        {"ILOVEYOU", "Creeper", "MyDoom", "Stuxnet"},
        {".class", ".java", ".js", ".jv"},
        {"JavaScript", "Python", "Java", "C++"}
    };

    char[] answers = {'C','B','B','B','B','B','A','B','B','B'};
    int index = 0;
    int correct = 0;

    JLabel questionLabel = new JLabel();
    JLabel scoreLabel = new JLabel("Score: 0");
    JRadioButton[] optionsButtons = new JRadioButton[4];
    ButtonGroup group = new ButtonGroup();
    JButton nextButton = new JButton("Next");
    JButton submitButton = new JButton("Submit");

    public QuizAppGUI() {
        setTitle("Online Quiz App");
        setSize(500, 350);
        setLayout(new GridLayout(7, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        for (int i = 0; i < 4; i++) {
            optionsButtons[i] = new JRadioButton();
            group.add(optionsButtons[i]);
            add(optionsButtons[i]);
        }

        nextButton.addActionListener(this);
        submitButton.addActionListener(this);
        add(nextButton);

        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(scoreLabel);

        loadQuestion(index);
        setVisible(true);
    }

    public void loadQuestion(int i) {
        group.clearSelection();
        questionLabel.setText("Q" + (i + 1) + ": " + questions[i]);
        for (int j = 0; j < 4; j++) {
            optionsButtons[j].setText(options[i][j]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isOptionSelected()) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        if (e.getSource() == nextButton) {
            checkAnswer();
            index++;
            if (index < questions.length) {
                loadQuestion(index);
                if (index == questions.length - 1) {
                    nextButton.setVisible(false);
                    add(submitButton);
                    validate();
                }
            }
        } else if (e.getSource() == submitButton) {
            checkAnswer();
            JOptionPane.showMessageDialog(this, "Quiz completed! Your final score is " + correct + "/" + questions.length);
            System.exit(0);
        }
    }

    private boolean isOptionSelected() {
        for (JRadioButton btn : optionsButtons) {
            if (btn.isSelected()) return true;
        }
        return false;
    }

    public void checkAnswer() {
    for (int i = 0; i < 4; i++) {
        if (optionsButtons[i].isSelected()) {
            // Check if the selected answer matches the correct one
            String selected = optionsButtons[i].getText();
            String correctOptionText = options[index][answers[index] - 'A'];

            if (selected.equals(correctOptionText)) {
                correct++;
            }
            break;
        }
    }
    scoreLabel.setText("Score: " + correct);
}


    public static void main(String[] args) {
        new QuizAppGUI();
    }
}
