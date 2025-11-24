package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.geom.AffineTransform;
import skill.forge.*;

public class ChartFrame extends JFrame {
    private AnalyticsService analyticsService;
    
    public ChartFrame(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Skill Forge - Analytics Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    
    /**
     * Display lesson completion rates as a bar chart
     */
    public void showLessonCompletionChart(String courseId) {
        clearContent();
        
        Map<String, Double> completionRates = analyticsService.getLessonCompletionRates(courseId);
        Course course = getCourseById(courseId);
        String courseTitle = course != null ? course.getTitle() : "Unknown Course";
        
        JPanel chartPanel = new BarChartPanel(completionRates, "Lesson Completion Rates (%)", Color.BLUE);
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        
        JLabel titleLabel = new JLabel("Lesson Completion Rates - " + courseTitle, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Add summary statistics
        JPanel statsPanel = createStatsPanel(completionRates, "Completion Rate");
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
    /**
     * Display quiz performance averages as a bar chart
     */
    public void showQuizPerformanceChart(String courseId) {
        clearContent();
        
        Map<String, Double> quizAverages = analyticsService.getQuizAverages(courseId);
        Course course = getCourseById(courseId);
        String courseTitle = course != null ? course.getTitle() : "Unknown Course";
        
        JPanel chartPanel = new BarChartPanel(quizAverages, "Average Quiz Scores (%)", Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        
        JLabel titleLabel = new JLabel("Quiz Performance - " + courseTitle, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Add summary statistics
        JPanel statsPanel = createStatsPanel(quizAverages, "Quiz Score");
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
    /**
     * Display instructor insights as a comprehensive report
     */
    public void showInstructorInsights(String instructorId) {
        clearContent();
        
        Map<String, Object> insights = analyticsService.getInstructorInsights(instructorId);
        
        JTextArea insightsArea = new JTextArea();
        insightsArea.setEditable(false);
        insightsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        insightsArea.setBackground(new Color(240, 240, 240));
        
        StringBuilder report = new StringBuilder();
        report.append("=== INSTRUCTOR PERFORMANCE INSIGHTS ===\n\n");
        
        // Overall statistics
        report.append("OVERALL STATISTICS:\n");
        report.append("─────────────────────────────────────\n");
        report.append(String.format("• Total Courses: %d\n", insights.get("totalCourses")));
        report.append(String.format("• Total Students: %d\n", insights.get("totalStudents")));
        report.append(String.format("• Overall Completion Rate: %.1f%%\n", insights.get("overallCompletionRate")));
        report.append(String.format("• Overall Quiz Average: %.1f%%\n", insights.get("overallQuizAverage")));
        report.append(String.format("• Lesson Completion Rate: %.1f%%\n", insights.get("overallLessonCompletionRate")));
        report.append(String.format("• Total Quizzes Taken: %d\n\n", insights.get("totalQuizzesTaken")));
        
        // Course-wise completion rates
        Map<String, Double> completionRates = (Map<String, Double>) insights.get("courseCompletionRates");
        if (!completionRates.isEmpty()) {
            report.append("COURSE COMPLETION RATES:\n");
            report.append("─────────────────────────────────────\n");
            for (Map.Entry<String, Double> entry : completionRates.entrySet()) {
                report.append(String.format("• %-30s: %6.1f%%\n", 
                    truncate(entry.getKey(), 28), entry.getValue()));
            }
            report.append("\n");
        }
        
        // Course enrollment
        Map<String, Integer> enrollment = (Map<String, Integer>) insights.get("courseEnrollment");
        if (!enrollment.isEmpty()) {
            report.append("COURSE ENROLLMENT:\n");
            report.append("─────────────────────────────────────\n");
            for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
                report.append(String.format("• %-30s: %6d students\n", 
                    truncate(entry.getKey(), 28), entry.getValue()));
            }
            report.append("\n");
        }
        
        // Course quiz averages
        Map<String, Double> quizAverages = (Map<String, Double>) insights.get("courseQuizAverages");
        if (!quizAverages.isEmpty()) {
            report.append("COURSE QUIZ AVERAGES:\n");
            report.append("─────────────────────────────────────\n");
            for (Map.Entry<String, Double> entry : quizAverages.entrySet()) {
                report.append(String.format("• %-30s: %6.1f%%\n", 
                    truncate(entry.getKey(), 28), entry.getValue()));
            }
        }
        
        insightsArea.setText(report.toString());
        
        JScrollPane scrollPane = new JScrollPane(insightsArea);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        
        JLabel titleLabel = new JLabel("Instructor Insights Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    // ========== HELPER METHODS AND INNER CLASSES ==========
    
    private void clearContent() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    private Course getCourseById(String courseId) {
        // Simple implementation - you might want to inject CourseService
        return null; // This would be implemented with proper dependency injection
    }
    
    private JPanel createStatsPanel(Map<String, Double> data, String metricName) {
        JPanel statsPanel = new JPanel(new FlowLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder("Summary Statistics"));
        
        if (data.isEmpty()) {
            statsPanel.add(new JLabel("No data available"));
            return statsPanel;
        }
        
        double average = data.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double max = data.values().stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
        double min = data.values().stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        
        statsPanel.add(new JLabel(String.format("Average %s: %.1f%%", metricName, average)));
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(new JLabel(String.format("Highest: %.1f%%", max)));
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(new JLabel(String.format("Lowest: %.1f%%", min)));
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(new JLabel(String.format("Total Items: %d", data.size())));
        
        return statsPanel;
    }
    
    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Inner class for drawing bar charts
     */
    private class BarChartPanel extends JPanel {
        private Map<String, Double> data;
        private String title;
        private Color barColor;
        
        public BarChartPanel(Map<String, Double> data, String title, Color barColor) {
            this.data = data;
            this.title = title;
            this.barColor = barColor;
            setPreferredSize(new Dimension(Math.max(800, data.size() * 120), 500));
            setBackground(Color.WHITE);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (data.isEmpty()) {
                drawNoDataMessage(g2d);
                return;
            }
            
            drawTitle(g2d);
            drawBars(g2d);
            drawAxes(g2d);
        }
        
        private void drawNoDataMessage(Graphics2D g2d) {
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.setColor(Color.GRAY);
            String message = "No data available for visualization";
            int messageWidth = g2d.getFontMetrics().stringWidth(message);
            g2d.drawString(message, getWidth() / 2 - messageWidth / 2, getHeight() / 2);
        }
        
        private void drawTitle(Graphics2D g2d) {
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.setColor(Color.BLACK);
            int titleWidth = g2d.getFontMetrics().stringWidth(title);
            g2d.drawString(title, getWidth() / 2 - titleWidth / 2, 30);
        }
        
        private void drawBars(Graphics2D g2d) {
            int width = getWidth();
            int height = getHeight();
            int barWidth = Math.max(40, (width - 100) / data.size());
            int maxBarHeight = height - 150;
            int x = 80;
            int yBase = height - 80;
            
            int index = 0;
            for (Map.Entry<String, Double> entry : data.entrySet()) {
                String label = entry.getKey();
                double value = entry.getValue();
                
                // Calculate bar height (0-100% scale)
                int barHeight = (int) (value / 100.0 * maxBarHeight);
                
                // Draw bar with gradient
                GradientPaint gradient = new GradientPaint(
                    x, yBase - barHeight, barColor.brighter(),
                    x, yBase, barColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRect(x, yBase - barHeight, barWidth - 10, barHeight);
                
                // Draw bar border
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, yBase - barHeight, barWidth - 10, barHeight);
                
                // Draw value on bar
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                String valueText = String.format("%.1f%%", value);
                int valueWidth = g2d.getFontMetrics().stringWidth(valueText);
                g2d.drawString(valueText, x + (barWidth - 10) / 2 - valueWidth / 2, yBase - barHeight - 5);
                
                // Draw label (rotated for better readability)
                drawRotatedLabel(g2d, label, x + (barWidth - 10) / 2, yBase + 20);
                
                x += barWidth;
                index++;
            }
        }
        
        private void drawRotatedLabel(Graphics2D g2d, String label, int x, int y) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            String displayLabel = label.length() > 20 ? label.substring(0, 17) + "..." : label;
            
            // Save current transform
            AffineTransform originalTransform = g2d.getTransform();
            
            // Rotate and draw label
            g2d.rotate(Math.toRadians(-45), x, y);
            g2d.drawString(displayLabel, x - 20, y);
            
            // Restore original transform
            g2d.setTransform(originalTransform);
        }
        
        private void drawAxes(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            
            int height = getHeight();
            
            // Draw Y-axis
            g2d.drawLine(60, 50, 60, height - 80);
            
            // Draw Y-axis labels
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            for (int i = 0; i <= 100; i += 20) {
                int y = height - 80 - (i * (height - 150) / 100);
                g2d.drawString(i + "%", 20, y + 5);
                g2d.drawLine(55, y, 65, y);
            }
            
            // Draw X-axis
            g2d.drawLine(60, height - 80, getWidth() - 40, height - 80);
        }
    }
}