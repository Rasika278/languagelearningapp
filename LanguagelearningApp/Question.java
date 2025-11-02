import java.util.*;

public class Question extends Word {
    private List<String> options;
    private String correctAnswer;

    public Question(String englishWord, String meaning, List<String> options, String correctAnswer) {
        super(englishWord, meaning);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }
}

