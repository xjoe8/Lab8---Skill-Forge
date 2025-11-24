package FrontEnd;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.*;
import skill.forge.*;

public class StudentDashboardFrame extends javax.swing.JDialog {
    private Student currentStudent;
    private JsonDataBaseManager dbManager;
    
    public StudentDashboardFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.dbManager = new JsonDataBaseManager("users.json","courses.json");
        initComponents();
    }
    
    public StudentDashboardFrame(java.awt.Frame parent, boolean modal, Student student, JsonDataBaseManager dbManager) {
        super(parent, modal);
        this.currentStudent = student;
        this.dbManager = dbManager;
        initComponents();
        loadStudentData();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Welcome, [Student Name]!");

        jLabel2.setText("Enrolled Courses 5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setText("Completed lessons 12");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setText("Progress 75%");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Recent Activity:\n• Completed \"Java Basics\" - Lesson 3\n• Enrolled in \"Advanced Programming\"\n• Updated profile information");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dashboard", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Course Name", "Progress", "Last Accessed", "Actions"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Enroll in New Course");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        jTabbedPane1.addTab("My Courses", jPanel2);

        jLabel7.setText("Course: ");

        jLabel8.setText("Overall:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Lessons", "Progress"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jLabel9.setText("Certificates:");

        jLabel10.setText("No certificates available yet");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Progress", jPanel3);

        jLabel5.setText("User ID:");

        jLabel6.setText("jLabel6");

        jLabel11.setText("Username:");

        jLabel12.setText("jLabel12");

        jLabel13.setText("Email:");

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Profile", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        showAvailableCourses();
        // Refresh dashboard when returning from course browser
        loadStudentData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Load data when window opens
        loadStudentData();
    }//GEN-LAST:event_formWindowOpened

    private void showAvailableCourses() {
        try {
            List<Course> availableCourses = dbManager.getApprovedCourses();

            if (availableCourses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No courses available yet.");
                return;
            }

            // Let student select a course
            String[] courseOptions = availableCourses.stream()
                .map(c -> c.getCourseId() + " - " + c.getTitle())
                .toArray(String[]::new);

            String selected = (String) JOptionPane.showInputDialog(this,
                "Select a course to view:",
                "Available Courses",
                JOptionPane.QUESTION_MESSAGE,
                null,
                courseOptions,
                courseOptions[0]);

            if (selected != null) {
                String courseId = selected.split(" - ")[0].trim();
                Course course = dbManager.getCourseAsCourse(courseId);

                if (course != null) {
                    // Open CourseDashboardFrame in student mode
                    CourseDashboardFrame courseFrame = new CourseDashboardFrame(course, dbManager, currentStudent);
                    courseFrame.setVisible(true);

                    // Refresh data when student returns from course view
                    loadStudentData();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading courses: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showEnrolledCourses() {
        try {
            List<String> enrolledCourseIds = currentStudent.getEnrolledCourses();

            if (enrolledCourseIds.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You are not enrolled in any courses yet.");
                return;
            }

            String[] courseOptions = enrolledCourseIds.stream()
                .map(courseId -> {
                    Course course = dbManager.getCourseAsCourse(courseId);
                    return course != null ? (course.getCourseId() + " - " + course.getTitle()) : courseId;
                })
                .toArray(String[]::new);

            String selected = (String) JOptionPane.showInputDialog(this,
                "Select a course to continue:",
                "My Courses",
                JOptionPane.QUESTION_MESSAGE,
                null,
                courseOptions,
                courseOptions[0]);

            if (selected != null) {
                String courseId = selected.split(" - ")[0].trim();
                Course course = dbManager.getCourseAsCourse(courseId);

                if (course != null) {
                    // FIXED: Pass dbManager as third parameter
                    LessonList lessonList = new LessonList(course, currentStudent, dbManager);
                    lessonList.setVisible(true);

                    // Refresh data when student returns from lesson view
                    loadStudentData();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading enrolled courses: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentData() {
        if (currentStudent == null) {
            System.out.println("No student data available - using demo data");
            loadDemoData();
            return;
        }
        
        try {
            // Update Dashboard tab
            jLabel1.setText("Welcome, " + getSafeUsername() + "!");
            
            int enrolledCount = currentStudent.getEnrolledCourses().size();
            jLabel2.setText("Enrolled: " + enrolledCount);
            
            int completedLessons = getTotalCompletedLessons();
            jLabel4.setText("Completed: " + completedLessons);
            
            double progress = calculateOverallProgress();
            jLabel3.setText("Progress: " + (int)progress + "%");
            
            // Update Recent Activity
            updateRecentActivity();
            
            // Update Courses tab
            updateCoursesTable();
            
            // Update Progress tab
            updateProgressTab();
            
            // Update Profile tab
            updateProfileTab();
            
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e.getMessage());
            e.printStackTrace();
            loadDemoData(); // Fallback to demo data
        }
    }
    
    private String getSafeUsername() {
        try {
            return currentStudent.getUsername();
        } catch (Exception e) {
            return "Student";
        }
    }

    private String getSafeUserId() {
        try {
            return currentStudent.getUserId();
        } catch (Exception e) {
            return "N/A";
        }
    }

    private String getSafeEmail() {
        try {
            return currentStudent.getEmail();
        } catch (Exception e) {
            return "N/A";
        }
    }

    private int getTotalCompletedLessons() {
        try {
            int total = 0;
            for (String courseId : currentStudent.getEnrolledCourses()) {
                total += currentStudent.getCompletedLessons(courseId).size();
            }
            return total;
        } catch (Exception e) {
            return 0;
        }
    }

    private double calculateOverallProgress() {
        try {
            java.util.List<String> enrolledCourses = currentStudent.getEnrolledCourses();
            if (enrolledCourses.isEmpty()) return 0.0;

            double totalProgress = 0;
            for (String courseId : enrolledCourses) {
                int completed = currentStudent.getCompletedLessons(courseId).size();

                // Retrieve total lessons dynamically
                int totalLessons = dbManager.getCourseTotalLessons(courseId);
                if (totalLessons == 0) totalLessons = 1; // Avoid division by zero

                totalProgress += (completed / (double) totalLessons) * 100;
            }
            return totalProgress / enrolledCourses.size();
        } catch (Exception e) {
            return 0.0;
        }
    }

    private void updateRecentActivity() {
        try {
            StringBuilder activity = new StringBuilder("Recent Activity:\n");

            // Get all enrolled courses from DB
            java.util.List<String> enrolledCourses = dbManager.getStudentEnrolledCourses(currentStudent.getUserId());

            if (enrolledCourses.isEmpty()) {
                activity.append("• No recent activity");
            } else {
                // Show latest enrollments
                String lastEnrolled = enrolledCourses.get(enrolledCourses.size() - 1);
                activity.append("• Enrolled in \"").append(lastEnrolled).append("\"\n");

                boolean hasCompletedLessons = false;

                // Retrieve completed lessons for each course from DB
                for (String courseId : enrolledCourses) {
                    java.util.List<String> completedLessons = dbManager.getStudentCompletedLessons(currentStudent.getUserId(), courseId);

                    if (!completedLessons.isEmpty()) {
                        String lastLesson = completedLessons.get(completedLessons.size() - 1);
                        activity.append("• Completed \"").append(courseId)
                               .append("\" - ").append(lastLesson).append("\n");
                        hasCompletedLessons = true;
                    }
                }

                // If no lessons completed
                if (!hasCompletedLessons) {
                    activity.append("• No lessons completed yet");
                }
            }

            jTextArea1.setText(activity.toString());

        } catch (Exception e) {
            jTextArea1.setText("Recent Activity:\n• Error loading activity data");
            System.out.println("Error updating recent activity: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateCoursesTable() {
        try {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data

            for (String courseId : currentStudent.getEnrolledCourses()) {
                Course course = dbManager.getCourseAsCourse(courseId);
                String courseName = course != null ? course.getTitle() : courseId;

                int completedLessons = currentStudent.getCompletedLessons(courseId).size();
                int totalLessons = dbManager.getCourseTotalLessons(courseId);
                if (totalLessons == 0) totalLessons = 1;

                int progress = (int)((completedLessons / (double)totalLessons) * 100);

                model.addRow(new Object[]{
                    courseName,
                    progress + "%",
                    java.time.LocalDate.now().toString(),
                    "Open Course"
                });
            }

            if (model.getRowCount() == 0) {
                model.addRow(new Object[]{
                    "No courses enrolled",
                    "0%",
                    "N/A",
                    "Enroll Now"
                });
            }

            // Add click listener to the table
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = jTable1.rowAtPoint(evt.getPoint());
                    int col = jTable1.columnAtPoint(evt.getPoint());

                    if (row >= 0 && col == 3) { // Actions column
                        String courseId = currentStudent.getEnrolledCourses().get(row);
                        Course course = dbManager.getCourseAsCourse(courseId);

                        if (course != null) {
                            LessonList lessonList = new LessonList(course, currentStudent, dbManager);
                            lessonList.setVisible(true);
                            loadStudentData(); // Refresh after course interaction
                        }
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Error updating courses table: " + e.getMessage());
        }
    }

    private void updateProgressTab() {
        try {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            for (String courseId : currentStudent.getEnrolledCourses()) {
                java.util.List<String> completedLessons = currentStudent.getCompletedLessons(courseId);
                int completedCount = completedLessons.size();

                int totalLessons = dbManager.getCourseTotalLessons(courseId);
                if (totalLessons == 0) totalLessons = 1;

                int progress = (int)((completedCount / (double)totalLessons) * 100);

                model.addRow(new Object[]{
                    courseId,
                    completedCount + "/" + totalLessons + " lessons (" + progress + "%)"
                });
            }

            // Update course info labels
            if (!currentStudent.getEnrolledCourses().isEmpty()) {
                String firstCourse = currentStudent.getEnrolledCourses().get(0);
                jLabel7.setText("Course: " + firstCourse);
            } else {
                jLabel7.setText("Course: No courses enrolled");
            }

            jLabel8.setText("Overall: " + (int)calculateOverallProgress() + "%");

            updateCertificatesLabel();

        } catch (Exception e) {
            System.out.println("Error updating progress tab: " + e.getMessage());
        }
    }

    private void updateCertificatesLabel() {
        try {
            int completedCourses = 0;
            for (String courseId : currentStudent.getEnrolledCourses()) {
                int completedLessons = currentStudent.getCompletedLessons(courseId).size();
                int totalLessons = dbManager.getCourseTotalLessons(courseId);
                if (completedLessons >= totalLessons) {
                    completedCourses++;
                }
            }

            if (completedCourses > 0) {
                jLabel10.setText("You have " + completedCourses + " certificate(s) ready!");
            } else {
                jLabel10.setText("Complete courses to earn certificates");
            }
        } catch (Exception e) {
            jLabel10.setText("Certificate status unavailable");
        }
    }

    private void updateProfileTab() {
        try {
            jLabel6.setText(getSafeUserId());
            jLabel12.setText(getSafeUsername());
            jLabel14.setText(getSafeEmail());
        } catch (Exception e) {
            System.out.println("Error updating profile tab: " + e.getMessage());
        }
    }

    private void loadDemoData() {
        jLabel1.setText("Welcome, Student!");
        jLabel2.setText("Enrolled: 0");
        jLabel4.setText("Completed: 0");
        jLabel3.setText("Progress: 0%");
        jTextArea1.setText("Recent Activity:\n• No data available");
        
        // Update profile tab with demo data
        jLabel6.setText("N/A");
        jLabel12.setText("N/A");
        jLabel14.setText("N/A");
        
        // Update certificates label
        jLabel10.setText("No certificates available yet");
        
        // Clear tables
        javax.swing.table.DefaultTableModel model1 = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model1.setRowCount(0);
        
        javax.swing.table.DefaultTableModel model2 = (javax.swing.table.DefaultTableModel) jTable2.getModel();
        model2.setRowCount(0);
        
        // Add demo message to tables
        model1.addRow(new Object[]{"No data available", "0%", "N/A", "Enroll Now"});
        model2.addRow(new Object[]{"No data available", "0%"});
    }

    public static void main(String args[]) {
        // Create the database manager
        JsonDataBaseManager dbManager = new JsonDataBaseManager("users.json", "courses.json");

        // Ask the user to enter their student ID
        String studentId = javax.swing.JOptionPane.showInputDialog(
            null, 
            "Enter your Student ID:", 
            "Login", 
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("No ID entered. Exiting...");
            return;
        }

        // Load the student JSONObject
        JSONObject studentJson = dbManager.getStudentById(studentId.trim());

        // Convert to a Student object
        Student currentStudent = null;
        if (studentJson != null) {
            currentStudent = dbManager.parseStudent(studentJson);
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                null, 
                "Student ID not found!", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Launch the StudentDashboardFrame
        Student finalCurrentStudent = currentStudent; // for lambda capture
        java.awt.EventQueue.invokeLater(() -> {
            StudentDashboardFrame dialog =
                new StudentDashboardFrame(
                    new javax.swing.JFrame(),
                    true,
                    finalCurrentStudent,
                    dbManager
                );
            dialog.setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}