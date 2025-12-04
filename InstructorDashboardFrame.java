package FrontEnd;

import java.util.*;
import skill.forge.*;
import javax.swing.*;
import org.json.*;

public class InstructorDashboardFrame extends javax.swing.JFrame {

    private Instructor loggedInstructor;
    private JsonDataBaseManager db;
    private AnalyticsService analyticsService;
    private CourseService courseService;
    
    public InstructorDashboardFrame(Instructor inst , JsonDataBaseManager db) {
        this.loggedInstructor = inst;
        this.db = db;
        initComponents();
        
        jLabel1.setText("Welcome, " + loggedInstructor.getUsername());
        jButton1.setText("Create Course");
        jButton2.setText("Open Course");
        jButton3.setText("Logout");
        
        jButton1.addActionListener(e -> createCourse());
        jButton2.addActionListener(e -> openCourse());
        jButton3.addActionListener(e -> logout());

        loadInstructorCourses();
        
        initializeAnalytics();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        insightsScrollPane = new javax.swing.JScrollPane();
        insightsTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Welcome ");

        jButton1.setText("Create Course");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("Manage Course");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton2.setText("Open Course");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete Course");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Edit Course");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Logout");
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
                .addGap(238, 238, 238)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap(241, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6});

        jTabbedPane1.addTab("My Courses", jPanel1);

        jLabel2.setText("Course Analytics Dashboard");

        jLabel3.setText("Select Course:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton7.setText("Lesson Completion Chart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Quiz Performance Chart");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Overall Insights");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        insightsTextArea.setColumns(20);
        insightsTextArea.setRows(5);
        insightsTextArea.setText("Select a course and click a button to view analytics...\n");
        insightsScrollPane.setViewportView(insightsTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insightsScrollPane)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jButton9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton7, jButton8, jButton9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insightsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Analytics", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        logout();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        createCourse();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       openCourse();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        deleteCourse();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        manageCourse();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        editCourse();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        showInsights();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        showQuizChart();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        showCompletionChart();
    }//GEN-LAST:event_jButton7ActionPerformed

    //NOT SURE OF THIS METHOD:
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selected = (String) jComboBox1.getSelectedItem();
        if (selected == null || selected.equals("No courses available")) {
            insightsTextArea.setText("No course selected. Please choose a course from the dropdown.");
            return;
        }

        String courseId = selected.split(" - ")[0].trim();
        String courseName = selected.split(" - ")[1].trim();

        try {
            // Get quick course stats
            double completionRate = analyticsService.getCourseCompletionRate(courseId);
            int totalStudents = 0;

            // Get student count
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                totalStudents = course.getStudents().size();
            }

            // Display course overview
            StringBuilder overview = new StringBuilder();
            overview.append("📊 Course Overview: ").append(courseName).append("\n");
            overview.append("─────────────────────────────\n\n");
            overview.append(String.format("• Course ID: %s\n", courseId));
            overview.append(String.format("• Total Students: %d\n", totalStudents));
            overview.append(String.format("• Completion Rate: %.1f%%\n\n", completionRate));
            overview.append("Click the buttons below to view detailed analytics:\n");
            overview.append("• Lesson Completion Chart - Visual progress tracking\n");
            overview.append("• Quiz Performance Chart - Average scores per lesson\n");
            overview.append("• Overall Insights - Comprehensive instructor report");

            insightsTextArea.setText(overview.toString());

        } catch (Exception e) {
            insightsTextArea.setText("Course: " + courseName + 
                                   "\n\nReady for analytics! Click any chart button to view data.");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {

            // Initialize the database manager FIRST
            JsonDataBaseManager db = new JsonDataBaseManager("users.json", "courses.json");

            // Ask the user for instructor ID at runtime
            String instructorId = JOptionPane.showInputDialog(null, "Enter your Instructor ID:");

            if (instructorId == null || instructorId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ID entered. Exiting.");
                return;
            }

            // Retrieve instructor JSON
            JSONObject instructorJson = db.getInstructorById(instructorId.trim());

            if (instructorJson == null) {
                JOptionPane.showMessageDialog(null, "Instructor not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse into Instructor object
            Instructor loggedInstructor = db.parseInstructor(instructorJson);

            // Finally open the dashboard
            InstructorDashboardFrame IDF = new InstructorDashboardFrame(loggedInstructor, db);
            IDF.setVisible(true);
        });
    }
    
    // Call this when Create Course button pressed (wired already in constructor)
    private void createCourse() {
        String title = JOptionPane.showInputDialog(this, "Enter course title:");
        if (title == null) return; // cancelled
        title = title.trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty.");
            return;
        }

        String description = JOptionPane.showInputDialog(this, "Enter course description:");
        if (description == null) description = "";

        // generate a course id using db
        String courseId = db.generateId();

        // Course constructor assumed: (courseId, title, description, instructorId)
        Course c = new Course(courseId, title, description, loggedInstructor.getUserId());
        c.setApprovalStatus("PENDING");

        // Add to DB and update instructor record
        db.addCourse(c);

        // Also add to instructor's createdCourses in users.json
        JSONArray users = db.loadUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject u = users.getJSONObject(i);
            if ("instructor".equals(u.optString("role")) && loggedInstructor.getUserId().equals(u.optString("userId"))) {
                JSONArray created = u.optJSONArray("createdCourses");
                if (created == null) created = new JSONArray();
                created.put(courseId);
                u.put("createdCourses", created);
                users.put(i, u);
                break;
            }
        }
        db.saveUsers(users);

        loadInstructorCourses();
        JOptionPane.showMessageDialog(this, "Course created: " + title);
        
        refreshAnalytics();
    }

    /**
     * Open course details/dashboard. Uses getCourseAsCourse helper to get Course object.
     */
    private void openCourse() {
        String selected = jList1.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course first.");
            return;
        }
        String courseId = selected.split(" - ")[0].trim();
        // Use helper to get Course object
        Course c = db.getCourseAsCourse(courseId);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Course not found in database.");
            return;
        }
        // Open CourseDashboardFrame (assume it expects Course & JsonDataBaseManager)
        new CourseDashboardFrame(c, db).setVisible(true);
    }

    /**
     * Edit the selected course (title & description).
     */
    private void editCourse() {
        String selected = jList1.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.");
            return;
        }
        String courseId = selected.split(" - ")[0].trim();

        Course course = db.getCourseAsCourse(courseId);
        if (course == null) {
            JOptionPane.showMessageDialog(this, "Selected course not found.");
            return;
        }

        String newTitle = JOptionPane.showInputDialog(this, "Edit title:", course.getTitle());
        if (newTitle == null) return; // cancelled
        newTitle = newTitle.trim();
        if (newTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty.");
            return;
        }

        String newDescription = JOptionPane.showInputDialog(this, "Edit description:", course.getDescription());
        if (newDescription == null) newDescription = "";

        // update course object
        course.setTitle(newTitle);
        course.setDescription(newDescription);

        boolean ok = db.updateCourse(course);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Course updated.");
            loadInstructorCourses();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update course.");
        }
        
        refreshAnalytics();
    }

    /**
     * Delete the selected course (with confirmation).
     */
    private void deleteCourse() {
        String selected = jList1.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.");
            return;
        }
        String courseId = selected.split(" - ")[0].trim();

        int resp = JOptionPane.showConfirmDialog(this,
            "Delete course " + courseId + "? This will remove the course and student enrollments.",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (resp != JOptionPane.YES_OPTION) return;

        boolean ok = db.deleteCourse(courseId);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Course deleted.");
            loadInstructorCourses();
        } else {
            JOptionPane.showMessageDialog(this, "Course not found or could not be deleted.");
        }
        
        refreshAnalytics();
    }

    /**
     * Manage course: show a small menu letting instructor Edit / Delete / Open the selected course.
     */
    private void manageCourse() {
        String selected = jList1.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course first.");
            return;
        }

        String[] options = {"Open", "Edit", "Delete", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
            "Choose action for: " + selected,
            "Manage Course",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

        if (choice == 0) openCourse();
        else if (choice == 1) editCourse();
        else if (choice == 2) deleteCourse();
        // else cancel or closed
    }

    /**
     * Load and display courses that belong to the logged instructor.
     * Uses your existing parseCourse/getCourseById logic.
     */
    private void loadInstructorCourses() {
        List<Course> allCourses = new ArrayList<>();
        List<Course> myCourses = new ArrayList<>();
        JSONArray arr = db.loadCourses();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject jobj = arr.getJSONObject(i);
            Course parsed = db.parseCourse(jobj);
            allCourses.add(parsed);
        }
        for (Course c : allCourses) {
            if (c.getInstructorId().equals(loggedInstructor.getUserId())) {
                myCourses.add(c);
            }
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Course c : myCourses) {
            model.addElement(c.getCourseId() + " - " + c.getTitle());
        }
        jList1.setModel(model);
    }

    private void logout() {
        this.dispose();
        new login().setVisible(true);
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------
    //ANALYTICS METHODS:
    private void initializeAnalytics() {
        try {
            this.courseService = new CourseService(db);
            this.analyticsService = new AnalyticsService(db, courseService);
            setupAnalyticsTab();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error initializing analytics: " + e.getMessage());
        }
    }

    private void setupAnalyticsTab() {
        loadAnalyticsCourses();

        jButton7.addActionListener(e -> showCompletionChart());
        jButton8.addActionListener(e -> showQuizChart());
        jButton9.addActionListener(e -> showInsights());
        
        jComboBox1.addActionListener(e -> jComboBox1ActionPerformed(e));

        insightsTextArea.setText("Welcome to Analytics Dashboard!\n\n" +
                               "Select a course and click one of the buttons to view:\n" +
                               "• Lesson Completion Chart - Visual completion rates\n" +
                               "• Quiz Performance Chart - Average quiz scores\n" +
                               "• Overall Insights - Comprehensive instructor report");
    }

    private void loadAnalyticsCourses() {
        jComboBox1.removeAllItems();

        List<Course> instructorCourses = courseService.getCoursesByInstructor(loggedInstructor.getUserId());

        if (instructorCourses.isEmpty()) {
            jComboBox1.addItem("No courses available");
            return;
        }

        for (Course course : instructorCourses) {
            jComboBox1.addItem(course.getCourseId() + " - " + course.getTitle());
        }
    }

    private void showCompletionChart() {
        String selected = (String) jComboBox1.getSelectedItem();
        if (selected == null || selected.equals("No courses available")) {
            JOptionPane.showMessageDialog(this, "Please select a valid course first.");
            return;
        }

        String courseId = selected.split(" - ")[0].trim();

        try {
            ChartFrame chartFrame = new ChartFrame(analyticsService);
            chartFrame.showLessonCompletionChart(courseId);
            chartFrame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying chart: " + e.getMessage());
        }
    }

    private void showQuizChart() {
        String selected = (String) jComboBox1.getSelectedItem();
        if (selected == null || selected.equals("No courses available")) {
            JOptionPane.showMessageDialog(this, "Please select a valid course first.");
            return;
        }

        String courseId = selected.split(" - ")[0].trim();

        try {
            ChartFrame chartFrame = new ChartFrame(analyticsService);
            chartFrame.showQuizPerformanceChart(courseId);
            chartFrame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying quiz chart: " + e.getMessage());
        }
    }

    private void showInsights() {
        try {
            ChartFrame chartFrame = new ChartFrame(analyticsService);
            chartFrame.showInstructorInsights(loggedInstructor.getUserId());
            chartFrame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error generating insights: " + e.getMessage());
        }
    }

    private void refreshAnalytics() {
        loadAnalyticsCourses();
        insightsTextArea.setText("Analytics refreshed. Select a course to view updated data.");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane insightsScrollPane;
    private javax.swing.JTextArea insightsTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
