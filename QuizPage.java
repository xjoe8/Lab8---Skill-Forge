package lab.pkg78;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class QuizPage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(QuizPage.class.getName());
    private QuizService quizService;
    private Student student;
    private Course course;
    private JsonDataBaseManager db;
    private Quiz quiz;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private List<Integer> userAnswers; // Storing options selected by the student.
    private ButtonGroup optionsGroup;
    private LessonViewerFrame parentFrame;
    private String lessonId;

    public QuizPage(Quiz quiz, Student student, QuizService quizService, JsonDataBaseManager db, LessonViewerFrame parentFrame, String lessonId) {
        initComponents();
        this.quiz = quiz;
        this.student = student;
        this.quizService = quizService;
        this.db = db;
        this.lessonId = lessonId;
        this.parentFrame = parentFrame;
        this.questions = quiz.getQuestions();
        this.userAnswers = new ArrayList<>(Collections.nCopies(questions.size(), -1));
        this.optionsGroup = new ButtonGroup();
        
        testFileConnection();
        initializeQuiz();
    }

    private void initializeQuiz() {

        optionsGroup.add(jRadioButton1);
        optionsGroup.add(jRadioButton2);
        optionsGroup.add(jRadioButton3);
        optionsGroup.add(jRadioButton4);

        jButton2.setEnabled(false);
        jButton3.setEnabled(false);

        loadQuestion(0);
        updateNavigation();
    }

    private void loadQuestion(int questionIndex) {
        if (questionIndex < 0 || questionIndex >= questions.size()) {
            return;
        }

        Question question = questions.get(questionIndex);

        // Set question text with question number
        jLabel1.setText("<html><body style='width: 400px'>" + (questionIndex + 1) + ". " + question.getQuestionText() + "</body></html>");

        // Set option texts
        List<String> options = question.getOptions();
        jRadioButton1.setText(options.size() > 0 ? "A. " + options.get(0) : "");
        jRadioButton2.setText(options.size() > 1 ? "B. " + options.get(1) : "");
        jRadioButton3.setText(options.size() > 2 ? "C. " + options.get(2) : "");
        jRadioButton4.setText(options.size() > 3 ? "D. " + options.get(3) : "");

        // Clear selection
        optionsGroup.clearSelection();

        // Restore previous answer if exists
        int previousAnswer = userAnswers.get(questionIndex);
        if (previousAnswer != -1) {
            switch (previousAnswer) {
                case 0:
                    jRadioButton1.setSelected(true);
                    break;
                case 1:
                    jRadioButton2.setSelected(true);
                    break;
                case 2:
                    jRadioButton3.setSelected(true);
                    break;
                case 3:
                    jRadioButton4.setSelected(true);
                    break;
            }
        }

        currentQuestionIndex = questionIndex;
        updateNavigation();
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (userAnswers.get(i) == questions.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }
        return score;
    }

    private void showResults(int score, int totalQuestions, boolean passed) {
        StringBuilder result = new StringBuilder();
        result.append("<html><h2>Quiz Results</h2>");
        result.append("<b>Score: ").append(score).append("/").append(totalQuestions).append("</b><br>");
        result.append("<b>Percentage: ").append(String.format("%.1f", (double) score / totalQuestions * 100)).append("%</b><br>");
        result.append("<b>Status: ").append(passed ? "<font color='green'>PASSED</font>" : "<font color='red'>FAILED</font>").append("</b>");

        result.append("<br><br><h3>Question Review:</h3>");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int userAnswer = userAnswers.get(i);
            boolean correct = (userAnswer == q.getCorrectAnswerIndex());

            result.append("<b>Q").append(i + 1).append(": ").append(q.getQuestionText()).append("</b><br>");
            result.append("Your answer: ").append(getOptionLetter(userAnswer));
            result.append(correct ? " <font color='green'>✓</font>" : " <font color='red'>✗</font>");
            result.append("<br>");
            result.append("Correct answer: <font color='green'>").append(getOptionLetter(q.getCorrectAnswerIndex())).append("</font><br><br>");
        }

        result.append("</html>");

        JOptionPane.showMessageDialog(this, result.toString(), "Quiz Results", JOptionPane.INFORMATION_MESSAGE);

        jButton1.setEnabled(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setEnabled(false);
        jRadioButton4.setEnabled(false);
        disableQuizAfterSubmission();
    }

    private String getOptionLetter(int index) {
        return switch (index) {
            case 0 ->
                "A";
            case 1 ->
                "B";
            case 2 ->
                "C";
            case 3 ->
                "D";
            default ->
                "Not answered";
        };
    }

    private void saveQuizResults(int score, boolean passed) {
    try {
        // Convert answers to strings for JSON storage
        List<String> answerStrings = new ArrayList<>();
        for (Integer answer : userAnswers) {
            answerStrings.add(String.valueOf(answer));
        }
        
        // Save quiz attempt
        QuizAttempt attempt = quizService.submitQuizAttempt(
            quiz.getQuizId(), 
            student.getUserId(), 
            answerStrings
        );
        
        if (attempt != null) {
            // Mark lesson as completed if passed using Student class method
            if (passed) {
                String courseId = findCourseIdForLesson(lessonId);
                if (courseId != null) {
                    student.markLessonCompleted(courseId, lessonId);
                    // Save the updated student to database
                    saveStudentProgress();
                }
            }
            
            JOptionPane.showMessageDialog(this, 
                "Quiz results saved successfully!\nScore: " + score + "/" + questions.size() + 
                (passed ? "\nLesson marked as completed!" : "\nTry again to complete the lesson."), 
                "Quiz Submitted", 
                JOptionPane.INFORMATION_MESSAGE);
                
            // Notify parent frame
            if (parentFrame != null) {
                parentFrame.onQuizCompleted(passed, lessonId);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error: Could not save quiz results", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (Exception e) {
        logger.log(java.util.logging.Level.SEVERE, "Error saving quiz results", e);
        JOptionPane.showMessageDialog(this, 
            "Error saving results: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

private void saveStudentProgress() {
    try {
        JSONArray users = db.loadUsers();
        
        for (int i = 0; i < users.length(); i++) {
            JSONObject userJson = users.getJSONObject(i);
            if (userJson.getString("userId").equals(student.getUserId())) {
                // Convert the updated student back to JSON and replace
                JSONObject updatedStudentJson = db.toJson(student);
                users.put(i, updatedStudentJson);
                break;
            }
        }
 
        db.saveUsers(users);
        System.out.println("Student progress saved to database");
        
    } catch (Exception e) {
        System.err.println("Error saving student progress: " + e.getMessage());
        e.printStackTrace();
    }
}

private void markLessonAsCompleted(String lessonId) {
    try {
        String courseId = findCourseIdForLesson(lessonId);
        if (courseId == null) {
            System.err.println("Could not find course for lesson: " + lessonId);
            return;
        }
     
        JSONArray users = db.loadUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject userJson = users.getJSONObject(i);
            if (userJson.getString("userId").equals(student.getUserId()) && 
                "student".equals(userJson.optString("role"))) {
                
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
                boolean alreadyCompleted = false;
                for (int j = 0; j < completedLessons.length(); j++) {
                    if (completedLessons.getString(j).equals(lessonId)) {
                        alreadyCompleted = true;
                        break;
                    }
                }
                
                if (!alreadyCompleted) {
                    completedLessons.put(lessonId);
                    System.out.println("Marked lesson " + lessonId + " as completed for student " + student.getUsername());
                }
                
                users.put(i, userJson);
                break;
            }
        }
   
        db.saveUsers(users);
        System.out.println("Lesson completion saved to database");
        
    } catch (Exception e) {
        System.err.println("Error marking lesson as completed: " + e.getMessage());
        e.printStackTrace();
    }
}

private String findCourseIdForLesson(String lessonId) {
    try {
        JSONArray courses = db.loadCourses();
        for (int i = 0; i < courses.length(); i++) {
            JSONObject course = courses.getJSONObject(i);
            JSONArray lessons = course.optJSONArray("lessons");
            if (lessons != null) {
                for (int j = 0; j < lessons.length(); j++) {
                    JSONObject lesson = lessons.getJSONObject(j);
                    if (lesson.getString("lessonId").equals(lessonId)) {
                        return course.getString("courseId");
                    }
                }
            }
        }
    } catch (Exception e) {
        System.err.println("Error finding course for lesson: " + e.getMessage());
    }
    return null;
}

    private void disableQuizAfterSubmission() {
        // Disable all interactive elements
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setEnabled(false);
        jRadioButton4.setEnabled(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        // Only jButton3 (View Score) remains enabled
    }

    private void saveCurrentAnswer() {        // Save the current question's answer before navigating

        int selectedIndex = -1;
        if (jRadioButton1.isSelected()) {
            selectedIndex = 0;
        } else if (jRadioButton2.isSelected()) {
            selectedIndex = 1;
        } else if (jRadioButton3.isSelected()) {
            selectedIndex = 2;
        } else if (jRadioButton4.isSelected()) {
            selectedIndex = 3;
        }

        userAnswers.set(currentQuestionIndex, selectedIndex);
    }

    private void updateNavigation() {
        // Enable/disable buttons based on current position
        jButton1.setEnabled(currentQuestionIndex < questions.size() - 1); // Next button
        jButton2.setEnabled(isAllQuestionsAnswered()); // submit button
    }

    private boolean isAllQuestionsAnswered() {
        for (Integer answer : userAnswers) {
            if (answer == -1) {
                return false;
            }
        }
        return true;
    }
    private void testFileConnection() {
    try {
        // Test if we can read courses
        JSONArray courses = db.loadCourses();
        System.out.println("Loaded " + courses.length() + " courses");
        
        // Test if we can read users
        JSONArray users = db.loadUsers();
        System.out.println("Loaded " + users.length() + " users");
        
        // Test if quiz exists
        boolean quizFound = false;
        for (int i = 0; i < courses.length(); i++) {
            JSONObject course = courses.getJSONObject(i);
            JSONArray lessons = course.optJSONArray("lessons");
            if (lessons != null) {
                for (int j = 0; j < lessons.length(); j++) {
                    JSONObject lesson = lessons.getJSONObject(j);
                    if (lesson.has("quiz")) {
                        JSONObject quizJson = lesson.getJSONObject("quiz");
                        if (quizJson.getString("quizId").equals(quiz.getQuizId())) {
                            quizFound = true;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Quiz found in database: " + quizFound);
        
    } catch (Exception e) {
        System.out.println("File connection error: " + e.getMessage());
    }
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        jRadioButton2.setText("jRadioButton1");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("jRadioButton1");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("jRadioButton1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Next Question");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Submit ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Score");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addGap(16, 16, 16)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(195, Short.MAX_VALUE)
                    .addComponent(jRadioButton1)
                    .addGap(181, 181, 181)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: //next button it is submit for now
        saveCurrentAnswer();
        loadQuestion(currentQuestionIndex + 1);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        userAnswers.set(currentQuestionIndex, 0);
        updateNavigation();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        userAnswers.set(currentQuestionIndex, 1);
        updateNavigation();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        userAnswers.set(currentQuestionIndex, 2);
        updateNavigation();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        userAnswers.set(currentQuestionIndex, 3);
        updateNavigation();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: submit button
        saveCurrentAnswer();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to submit the quiz?\nYou cannot change answers after submission.",
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        int score = calculateScore();
        int totalQuestions = questions.size();
        boolean passed = quiz.isPassingScore(score);

        showResults(score, totalQuestions, passed);

        saveQuizResults(score, passed);

        jButton3.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: view score button
        int score = calculateScore();
        int totalQuestions = questions.size();
        boolean passed = quiz.isPassingScore(score);
        showResults(score, totalQuestions, passed);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new QuizPage().setVisible(true));
        // Simple test - just show the empty form first
    

    java.awt.EventQueue.invokeLater(() -> {
       
    });
}
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
