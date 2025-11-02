import java.io.*;
import java.util.*;

public class Quiz {
    private List<Word> words;
    private List<Question> questions;
    private Random random = new Random();

    public Quiz() {
        words = new ArrayList<>();
        questions = new ArrayList<>();
        loadWordsFromCSV("words.csv");
        createQuestions();
    }

    private void loadWordsFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    words.add(new Word(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading dataset: " + e.getMessage());
        }
    }

    private void createQuestions() {
        for (Word w : words) {
            List<String> options = new ArrayList<>();
            options.add(w.getMeaning());

            // Add 3 random wrong meanings
            while (options.size() < 4) {
                Word randomWord = words.get(random.nextInt(words.size()));
                if (!options.contains(randomWord.getMeaning())) {
                    options.add(randomWord.getMeaning());
                }
            }

            Collections.shuffle(options);
            questions.add(new Question(w.getEnglishWord(), w.getMeaning(), options, w.getMeaning()));
        }

        Collections.shuffle(questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

