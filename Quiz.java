package lab.pkg78;

// Quiz.java (enhanced with missing methods)
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class Quiz {
    private String quizId;
    private String lessonId;
    private String title;
    private List<Question> questions;
    private int passingScore;
    private int maxAttempts;

    public Quiz(String quizId, String lessonId, int passingScore, int maxAttempts) {
        this.quizId = quizId;
        this.lessonId = lessonId;
        this.title = "Quiz for Lesson";
        this.passingScore = passingScore;
        this.maxAttempts = maxAttempts;
        this.questions = new ArrayList<>();
    }

    // Getters and setters
    public String getQuizId() { return quizId; }
    public String getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public List<Question> getQuestions() { return questions; }
    public int getPassingScore() { return passingScore; }
    public int getMaxAttempts() { return maxAttempts; }

    public void setTitle(String title) { this.title = title; }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public boolean removeQuestion(String questionId) {
        return questions.removeIf(q -> q.getQuestionId().equals(questionId));
    }

    public int calculateScore(List<String> userAnswers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (i < userAnswers.size()) {
                try {
                    int userAnswerIndex = Integer.parseInt(userAnswers.get(i));
                    if (questions.get(i).isCorrectAnswer(userAnswerIndex)) {
                        score++;
                    }
                } catch (NumberFormatException e) {
                    // Invalid answer format, count as wrong
                }
            }
        }
        return score;
    }

    public boolean isPassingScore(int score) {
        double percentage = (double) score / questions.size() * 100;
        return percentage >= passingScore;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("quizId", quizId);
        json.put("lessonId", lessonId);
        json.put("title", title);
        json.put("passingScore", passingScore);
        json.put("maxAttempts", maxAttempts);
        
        JSONArray questionsArray = new JSONArray();
        for (Question question : questions) {
            questionsArray.put(question.toJSONObject());
        }
        json.put("questions", questionsArray);
        
        return json;
    }

    public static Quiz fromJSONObject(JSONObject json) {
        String quizId = json.getString("quizId");
        String lessonId = json.getString("lessonId");
        int passingScore = json.getInt("passingScore");
        int maxAttempts = json.getInt("maxAttempts");
        
        Quiz quiz = new Quiz(quizId, lessonId, passingScore, maxAttempts);
        quiz.setTitle(json.getString("title"));
        
        JSONArray questionsArray = json.getJSONArray("questions");
        for (int i = 0; i < questionsArray.length(); i++) {
            Question question = Question.fromJSONObject(questionsArray.getJSONObject(i));
            quiz.addQuestion(question);
        }
        
        return quiz;
    }
}