import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private Scanner scanner;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        scanner = new Scanner(System.in);
        setupQuestions();
    }

    private void setupQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Rome"}, 0));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"Shakespeare", "Tolstoy", "Hemingway", "Dostoevsky"}, 0));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3));
        questions.add(new Question("What is the powerhouse of the cell?", new String[]{"Nucleus", "Ribosome", "Mitochondria", "Chloroplast"}, 2));
        questions.add(new Question("Which have negative charges?",new String[]{"Electron","Proton","Newtron","All of these"},0));
        questions.add(new Question("Who is the father of computer?",new String[]{"James Gosling","Charles Babbage","Dennis Ritchie","Non of these"},1));
        questions.add(new Question("What is the Full form of CPU?",new String[]{"Computer Processing Unit","Computer Principle Unit","Central Processing Unit","Control Processing Unit"},2));
        questions.add(new Question("What is the Basic Language in programming?",new String[]{"C","C++","C#","Python"},0));
    }

    public void start() {
        for (Question question : questions) {
            displayQuestion(question);
        }
        displayResult();
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                System.out.println("The correct answer was: " + options[question.getCorrectAnswer()]);
                System.out.println();
            }
        };

        timer.schedule(task, 10000);  // 10 seconds timer

        int userAnswer = scanner.nextInt() - 1;
        timer.cancel();

        if (userAnswer == question.getCorrectAnswer()) {
            score++;
            System.out.println("Correct!\n");
        } else {
            System.out.println("Incorrect! The correct answer was: " + options[question.getCorrectAnswer()] + "\n");
        }
    }

    private void displayResult() {
        System.out.println("-------------------");
        System.out.println("Quiz Finished!");
        System.out.println("-------------------");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("-------------------");
        System.out.println("Summary of Answers:");
        System.out.println("-------------------");
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("Correct Answer: " + question.getOptions()[question.getCorrectAnswer()]);
            System.out.println();
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}
