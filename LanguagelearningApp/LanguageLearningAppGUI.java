import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LanguageLearningAppGUI {
    private Quiz quiz;
    private User user;
    private JFrame frame;
    private JLabel wordLabel, scoreLabel;
    private JButton[] optionButtons;
    private int currentQuestionIndex;

    public LanguageLearningAppGUI() {
        quiz = new Quiz();
        String userName = JOptionPane.showInputDialog("Enter your name:");
        if (userName == null || userName.isEmpty()) userName = "Student";
        user = new User(userName);

        currentQuestionIndex = 0;
        createGUI();
        showNextQuestion();
    }

    private void createGUI() {
        frame = new JFrame("Language Learning Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wordLabel.setForeground(new Color(25, 25, 112));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            int index = i;
            optionButtons[i].addActionListener(e -> checkAnswer(index));
            buttonPanel.add(optionButtons[i]);
        }

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.DARK_GRAY);

        frame.add(wordLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scoreLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void showNextQuestion() {
        if (currentQuestionIndex >= quiz.getQuestions().size()) {
            JOptionPane.showMessageDialog(frame,
                    "🎯 Quiz Over!\n" + user.getName() + ", your final score: " + user.getScore(),
                    "Result", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            return;
        }

        Question q = quiz.getQuestions().get(currentQuestionIndex);
        wordLabel.setText("Word: " + q.getEnglishWord());
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(q.getOptions().get(i));
        }
    }

    private void checkAnswer(int choice) {
        Question q = quiz.getQuestions().get(currentQuestionIndex);
        if (q.checkAnswer(q.getOptions().get(choice))) {
            user.increaseScore();
            JOptionPane.showMessageDialog(frame, "✅ Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame,
                    "❌ Wrong!\nCorrect meaning: " + q.getMeaning(),
                    "Result", JOptionPane.WARNING_MESSAGE);
        }

        scoreLabel.setText("Score: " + user.getScore());
        currentQuestionIndex++;
        showNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LanguageLearningAppGUI::new);
    }
}

