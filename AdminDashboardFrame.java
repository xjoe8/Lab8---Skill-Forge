package FrontEnd;


import skill.forge.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.json.*;

public class AdminDashboardFrame extends JFrame {
    private Admin loggedAdmin;
    private JsonDataBaseManager db;
    private JList<String> coursesList;
    private DefaultListModel<String> listModel;

    public AdminDashboardFrame(Admin admin, JsonDataBaseManager db) {
        this.loggedAdmin = admin;
        this.db = db;
        this.listModel = new DefaultListModel<>();
        initComponents();
        setupUI();
        loadPendingCourses();
    }

    private void setupUI() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Welcome, Admin " + loggedAdmin.getUsername());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JLabel subtitleLabel = new JLabel("Pending Courses for Approval");
        subtitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        coursesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(coursesList);

        JButton approveBtn = new JButton("Approve Course");
        JButton rejectBtn = new JButton("Reject Course");
        JButton refreshBtn = new JButton("Refresh");
        JButton logoutBtn = new JButton("Logout");

        approveBtn.addActionListener(e -> approveCourse());
        rejectBtn.addActionListener(e -> rejectCourse());
        refreshBtn.addActionListener(e -> loadPendingCourses());
        logoutBtn.addActionListener(e -> logout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(subtitleLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(approveBtn);
        buttonPanel.add(rejectBtn);
        buttonPanel.add(refreshBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        approveBtn = new javax.swing.JButton();
        rejectBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        approveBtn.setText("Approve Course");
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });

        rejectBtn.setText("Reject Course");
        rejectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBtnActionPerformed(evt);
            }
        });

        refreshBtn.setText("Resfresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(approveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rejectBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(approveBtn)
                            .addComponent(rejectBtn))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshBtn)
                    .addComponent(logoutBtn))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        approveCourse();
    }//GEN-LAST:event_approveBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        logout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void rejectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBtnActionPerformed
        rejectCourse();
    }//GEN-LAST:event_rejectBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        refreshCourses();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void refreshCourses() {
    // Clear the current selection
    coursesList.clearSelection();
    
    // Reload the data
    loadPendingCourses();
    
    // Show confirmation
    JOptionPane.showMessageDialog(this, 
        "Course list refreshed!\nApproved/Rejected courses removed from list.", 
        "Refresh Complete", 
        JOptionPane.INFORMATION_MESSAGE);
}
    
    private void loadPendingCourses() {
        listModel.clear();
        
        try {
            List<Course> pendingCourses = db.getPendingCourses();
            
            if (pendingCourses.isEmpty()) {
                listModel.addElement("No pending courses for approval");
            } else {
                for (Course course : pendingCourses) {
                    JSONObject instructor = db.getInstructorById(course.getInstructorId());
                    String instructorName = "Unknown";
                    if (instructor != null) {
                        instructorName = instructor.optString("username", "Unknown");
                    }
                    
                    listModel.addElement(course.getCourseId() + " - " + course.getTitle() + " (by: " + instructorName + ")");
                }
            }
        } catch (Exception e) {
            listModel.addElement("Error loading courses");
            e.printStackTrace();
        }
    }

    private void approveCourse() {
        String selected = coursesList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course to approve.");
            return;
        }
        
        try {
            String courseId = selected.split(" - ")[0].trim();
            Course course = db.getCourseAsCourse(courseId);
            
            if (course == null) {
                JOptionPane.showMessageDialog(this, "Course not found.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                "Approve course: " + course.getTitle() + "?\n\n" +
                "This will make it visible to students.",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (db.updateCourseStatus(courseId, "APPROVED")) {
                    JOptionPane.showMessageDialog(this, "Course approved successfully!");
                    loadPendingCourses();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to approve course.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error approving course: " + e.getMessage());
        }
    }

    private void rejectCourse() {
        String selected = coursesList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a course to reject.");
            return;
        }
        
        try {
            String courseId = selected.split(" - ")[0].trim();
            Course course = db.getCourseAsCourse(courseId);
            
            if (course == null) {
                JOptionPane.showMessageDialog(this, "Course not found.");
                return;
            }

            String reason = JOptionPane.showInputDialog(this, 
                "Enter rejection reason for: " + course.getTitle());

            if (reason != null && !reason.trim().isEmpty()) {
                if (db.updateCourseStatus(courseId, "REJECTED")) {
                    JOptionPane.showMessageDialog(this, "Course rejected.\nReason: " + reason);
                    loadPendingCourses();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to reject course.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error rejecting course: " + e.getMessage());
        }
    }

    private void logout() {
        this.dispose();
        new login().setVisible(true);
    }
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // Initialize the database manager FIRST
            JsonDataBaseManager db = new JsonDataBaseManager("users.json", "courses.json");

            // Ask the user for admin ID at runtime
            String adminId = JOptionPane.showInputDialog(null, "Enter your Admin ID:");

            if (adminId == null || adminId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ID entered. Exiting.");
                return;
            }

            // Retrieve admin JSON
            JSONObject adminJson = db.getAdminById(adminId.trim());

            if (adminJson == null) {
                JOptionPane.showMessageDialog(null, "Admin not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse into Admin object
            Admin a = db.parseAdmin(adminJson);

            // Finally open the dashboard
            AdminDashboardFrame ADF = new AdminDashboardFrame(a, db);
            ADF.setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton rejectBtn;
    // End of variables declaration//GEN-END:variables
}