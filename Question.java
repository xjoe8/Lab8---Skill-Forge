package skill.forge;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class Question {

    private String questionId;
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionId, String questionText, List<String> options, int correctAnswerIndex) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = new ArrayList<>(options);
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getters
    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }

    public String getCorrectAnswerText() {
        if (correctAnswerIndex >= 0 && correctAnswerIndex < options.size()) {
            return options.get(correctAnswerIndex);
        }
        return "Invalid correct answer index";
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("questionId", questionId);
        json.put("questionText", questionText);
        json.put("options", new JSONArray(options));
        json.put("correctAnswerIndex", correctAnswerIndex);
        return json;
    }

    public static Question fromJSONObject(JSONObject json) {
        String questionId = json.getString("questionId");
        String questionText = json.getString("questionText");
        int correctAnswerIndex = json.getInt("correctAnswerIndex");

        JSONArray optionsArray = json.getJSONArray("options");
        List<String> options = new ArrayList<>();
        for (int i = 0; i < optionsArray.length(); i++) {
            options.add(optionsArray.getString(i));
        }

        return new Question(questionId, questionText, options, correctAnswerIndex);
    }

    @Override
    public String toString() {
        return String.format("Question{id='%s', text='%s', correctIndex=%d}",
                questionId, questionText, correctAnswerIndex);
    }
}
