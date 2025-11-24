package skill.forge;

import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class QuizAttempt {

    private String attemptId;
    private String quizId;
    private String studentId;
    private String lessonId;
    private String courseId;
    private List<String> answers;
    private int score;
    private int totalQuestions;
    private Date attemptDate;
    private boolean passed;

    public QuizAttempt(String attemptId, String quizId, String studentId, String lessonId, String courseId) {
        this.attemptId = attemptId;
        this.quizId = quizId;
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.courseId = courseId;
        this.answers = new ArrayList<>();
        this.attemptDate = new Date();
        this.passed = false;
    }

    // Set answers and calculate if passed (should be called after scoring)
    public void setAnswers(List<String> answers, int score) {
        this.answers = new ArrayList<>(answers);
        this.score = score;
        this.totalQuestions = answers.size();

        // Determine if passed (you might want to get passing threshold from quiz)
        // For now, using 70% as passing
        this.passed = (getPercentage() >= 70.0);
    }

    // Getters
    public String getAttemptId() {
        return attemptId;
    }

    public String getQuizId() {
        return quizId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<String> getAnswers() {
        return new ArrayList<>(answers);
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public Date getAttemptDate() {
        return attemptDate;
    }

    public boolean isPassed() {
        return passed;
    }

    public double getPercentage() {
        if (totalQuestions == 0) {
            return 0.0;
        }
        return (double) score / totalQuestions * 100;
    }

    public String getFormattedDate() {
        return attemptDate.toString();
    }

    // JSON serialization for database persistence
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("attemptId", attemptId);
        json.put("quizId", quizId);
        json.put("studentId", studentId);
        json.put("lessonId", lessonId);
        json.put("courseId", courseId);
        json.put("answers", new JSONArray(answers));
        json.put("score", score);
        json.put("totalQuestions", totalQuestions);
        json.put("attemptDate", attemptDate.getTime()); // Store as timestamp
        json.put("passed", passed);
        return json;
    }

    // Static method to create QuizAttempt from JSON (for database loading)
    public static QuizAttempt fromJSONObject(JSONObject json) {
        String attemptId = json.getString("attemptId");
        String quizId = json.getString("quizId");
        String studentId = json.getString("studentId");
        String lessonId = json.getString("lessonId");
        String courseId = json.getString("courseId");

        QuizAttempt attempt = new QuizAttempt(attemptId, quizId, studentId, lessonId, courseId);

        // Load answers
        JSONArray answersArray = json.getJSONArray("answers");
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < answersArray.length(); i++) {
            answers.add(answersArray.getString(i));
        }

        int score = json.getInt("score");
        attempt.setAnswers(answers, score);

        // Set date
        long timestamp = json.getLong("attemptDate");
        attempt.attemptDate = new Date(timestamp);

        return attempt;
    }

    @Override
    public String toString() {
        return String.format("QuizAttempt{attemptId='%s', score=%d/%d, passed=%s, date=%s}",
                attemptId, score, totalQuestions, passed, getFormattedDate());
    }
}