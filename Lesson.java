package lab.pkg78;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Lesson {
    private String lessonId;
    private String title;
    private String content;
    private List<String> resources;
    private Quiz quiz;
    //private JsonDataBaseManager db;
    //private QuizService quizService;

    // Constructor WITH resources
    public Lesson(String lessonId, String title, String content, List<String> resources) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>(resources); 
        this.quiz = null;
    }

    // Constructor WITHOUT resources
    public Lesson(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>(); 
    }

    // Getters & Setters
    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) { // FIXED
        this.lessonId = lessonId;
    }

    public String getTitle() { 
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        if (content == null || content.trim().isEmpty()) {
            return "No content available for this lesson.";
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getResources() {
        return new ArrayList<>(resources); // return copy
    }


    // Add / Remove resources
    public void addResource(String url) {
        if (isValidUrl(url) && !resources.contains(url)) {
            resources.add(url);
        }
    }

    public boolean removeResource(String url) {
        return resources.remove(url);
    }

    // URL validation
    private boolean isValidUrl(String url) {
        return url != null && !url.trim().isEmpty() &&
               (url.startsWith("http://") || url.startsWith("https://"));
    }

    // json method 
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("lessonId", lessonId);
        json.put("title", title);
        json.put("content", content != null ? content : JSONObject.NULL);

        JSONArray resourceArray = new JSONArray();
        for (String r : resources) {
            resourceArray.put(r);
        }
        json.put("resources", resourceArray);
         if (quiz != null) {                        // lab 8 edit
            json.put("quiz", quiz.toJSONObject());
        }

        return json;
    }
    ///////////////////////// lab 8 methods,, getters and setters
    ///
    
    public boolean isQuizPassed(String studentId, JsonDataBaseManager db) {
        if (quiz == null) 
            return false;
        
        // Use QuizService to check if student passed this lesson's quiz
        QuizService quizService = new QuizService(db);
        return quizService.isQuizPassed(studentId, quiz.getQuizId());
    }
    
    public boolean canTakeQuiz(String studentId, JsonDataBaseManager db) {
        if (quiz == null) return false;
        
        QuizService quizService = new QuizService(db);
        return quizService.canRetakeQuiz(studentId, quiz.getQuizId());
    }
    
     public static Lesson fromJSONObject(JSONObject json, JsonDataBaseManager db) {
        String lessonId = json.getString("lessonId");
        String title = json.getString("title");
        String content = json.getString("content");
        
        Lesson lesson = new Lesson(lessonId, title, content);
        
        // Parse resources
        JSONArray resArr = json.optJSONArray("resources");
        if (resArr != null) {
            for (int i = 0; i < resArr.length(); i++) {
                lesson.addResource(resArr.getString(i));
            }
        }
        
        // Parse quiz if exists
        if (json.has("quiz")) {
            JSONObject quizJson = json.getJSONObject("quiz");
            Quiz quiz = db.parseQuiz(quizJson);
            lesson.setQuiz(quiz);
        }
        
        return lesson;
    }
    
    
     public Quiz getQuiz() { 
         return quiz; 
     }
     public void setQuiz(Quiz quiz) { 
         this.quiz = quiz; 
     }
     
    
}

