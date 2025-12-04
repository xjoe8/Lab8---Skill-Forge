package skill.forge;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class QuizService {
    private JsonDataBaseManager db;
    
    public QuizService(JsonDataBaseManager db) {
        this.db = db;
    }
    
    public Quiz createQuiz(String lessonId, int passingScore, int maxAttempts) {
        String quizId = db.generateId();
        Quiz quiz = new Quiz(quizId, lessonId, passingScore, maxAttempts);
        
        // Save to database
        JSONArray courses = db.loadCourses();
        for (int i = 0; i < courses.length(); i++) {
            JSONObject courseJson = courses.getJSONObject(i);
            JSONArray lessons = courseJson.optJSONArray("lessons");
            if (lessons != null) {
                for (int j = 0; j < lessons.length(); j++) {
                    JSONObject lessonJson = lessons.getJSONObject(j);
                    if (lessonJson.getString("lessonId").equals(lessonId)) {
                        lessonJson.put("quiz", quiz.toJSONObject());
                        db.saveCourses(courses);
                        return quiz;
                    }
                }
            }
        }
        return quiz;
    }
    
    public boolean addQuestionToQuiz(String quizId, Question question) {
        JSONArray courses = db.loadCourses();
        for (int i = 0; i < courses.length(); i++) {
            JSONObject courseJson = courses.getJSONObject(i);
            JSONArray lessons = courseJson.optJSONArray("lessons");
            if (lessons != null) {
                for (int j = 0; j < lessons.length(); j++) {
                    JSONObject lessonJson = lessons.getJSONObject(j);
                    if (lessonJson.has("quiz")) {
                        JSONObject quizJson = lessonJson.getJSONObject("quiz");
                        if (quizJson.getString("quizId").equals(quizId)) {
                            JSONArray questions = quizJson.optJSONArray("questions");
                            if (questions == null) {
                                questions = new JSONArray();
                                quizJson.put("questions", questions);
                            }
                            questions.put(question.toJSONObject());
                            db.saveCourses(courses);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public QuizAttempt submitQuizAttempt(String quizId, String studentId, List<String> answers) {
        // Find the quiz and lesson
        QuizData quizData = findQuizData(quizId);
        if (quizData == null) return null;
        
        // Calculate score
        int score = quizData.quiz.calculateScore(answers);
        boolean passed = quizData.quiz.isPassingScore(score);
        
        // Create attempt
        String attemptId = db.generateId();
        QuizAttempt attempt = new QuizAttempt(attemptId, quizId, studentId, 
                                            quizData.lessonId, quizData.courseId);
        attempt.setAnswers(answers, score);
        
        // Update student progress in database
        updateStudentProgress(studentId, quizData.courseId, quizData.lessonId, attempt);
        
        return attempt;
    }
    
    // Helper class to return quiz data
    private static class QuizData {
        Quiz quiz;
        String lessonId;
        String courseId;
        
        QuizData(Quiz quiz, String lessonId, String courseId) {
            this.quiz = quiz;
            this.lessonId = lessonId;
            this.courseId = courseId;
        }
    }
    
    private QuizData findQuizData(String quizId) {
        JSONArray courses = db.loadCourses();
        for (int i = 0; i < courses.length(); i++) {
            JSONObject courseJson = courses.getJSONObject(i);
            String courseId = courseJson.getString("courseId");
            JSONArray lessons = courseJson.optJSONArray("lessons");
            if (lessons != null) {
                for (int j = 0; j < lessons.length(); j++) {
                    JSONObject lessonJson = lessons.getJSONObject(j);
                    if (lessonJson.has("quiz")) {
                        JSONObject quizJson = lessonJson.getJSONObject("quiz");
                        if (quizJson.getString("quizId").equals(quizId)) {
                            Quiz quiz = db.parseQuiz(quizJson);
                            return new QuizData(quiz, lessonJson.getString("lessonId"), courseId);
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private void updateStudentProgress(String studentId, String courseId, String lessonId, QuizAttempt attempt) {
        JSONArray users = db.loadUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject userJson = users.getJSONObject(i);
            if (userJson.getString("userId").equals(studentId) && "student".equals(userJson.optString("role"))) {
                
                
                // Update quiz attempts
                JSONObject quizAttempts = userJson.optJSONObject("quizAttempts");
                if (quizAttempts == null) {
                    quizAttempts = new JSONObject();
                    userJson.put("quizAttempts", quizAttempts);
                }
                
                String attemptKey = courseId + "_" + lessonId;
                JSONArray attemptsArray = quizAttempts.optJSONArray(attemptKey);
                if (attemptsArray == null) {
                    attemptsArray = new JSONArray();
                    quizAttempts.put(attemptKey, attemptsArray);
                }
                attemptsArray.put(attempt.toJSONObject());
                
                // Mark lesson as completed if passed
                if (attempt.isPassed()) {
                    JSONObject progress = userJson.optJSONObject("progress");
                    if (progress == null) {
                        progress = new JSONObject();
                        userJson.put("progress", progress);
                    }
                    
                    JSONArray completedLessons = progress.optJSONArray(courseId);
                    if (completedLessons == null) {
                        completedLessons = new JSONArray();
                        progress.put(courseId, completedLessons);
                    }
                    
                    // Add lesson if not already completed
                    boolean alreadyCompleted = false;
                    for (int j = 0; j < completedLessons.length(); j++) {
                        if (completedLessons.getString(j).equals(lessonId)) {
                            alreadyCompleted = true;
                            break;
                        }
                    }
                    
                    if (!alreadyCompleted) {
                        completedLessons.put(lessonId);
                    }
                }
                
                users.put(i, userJson);
                break;
            }
        }
        db.saveUsers(users);
    }
    
    public List<QuizAttempt> getQuizAttempts(String studentId, String quizId) {
        List<QuizAttempt> attempts = new ArrayList<>();
        QuizData quizData = findQuizData(quizId);
        if (quizData == null) return attempts;
        
        JSONArray users = db.loadUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject userJson = users.getJSONObject(i);
            if (userJson.getString("userId").equals(studentId) && "student".equals(userJson.optString("role"))) {
                JSONObject quizAttempts = userJson.optJSONObject("quizAttempts");
                if (quizAttempts != null) {
                    String attemptKey = quizData.courseId + "_" + quizData.lessonId;
                    JSONArray attemptsArray = quizAttempts.optJSONArray(attemptKey);
                    if (attemptsArray != null) {
                        for (int j = 0; j < attemptsArray.length(); j++) {
                            JSONObject attemptJson = attemptsArray.getJSONObject(j);
                            attempts.add(QuizAttempt.fromJSONObject(attemptJson));
                        }
                    }
                }
                break;
            }
        }
        return attempts;
    }
    
    public boolean canRetakeQuiz(String studentId, String quizId) {
        List<QuizAttempt> attempts = getQuizAttempts(studentId, quizId);
        QuizData quizData = findQuizData(quizId);
        if (quizData == null) return false;
        
        return attempts.size() < quizData.quiz.getMaxAttempts();
    }
    
    public boolean isQuizPassed(String studentId, String quizId) {
        List<QuizAttempt> attempts = getQuizAttempts(studentId, quizId);
        for (QuizAttempt attempt : attempts) {
            if (attempt.isPassed()) {
                return true;
            }
        }
        return false;
    }
}
