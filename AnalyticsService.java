package lab8;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class AnalyticsService {
    private JsonDataBaseManager db;
    private CourseService courseService;
    
    public AnalyticsService(JsonDataBaseManager db, CourseService courseService) {
        this.db = db;
        this.courseService = courseService;
    }
    
    /**
     * Get overall course completion rate (percentage of enrolled students who completed all lessons)
     */
    public double getCourseCompletionRate(String courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) return 0.0;
        
        List<String> students = course.getStudents();
        if (students == null || students.isEmpty()) return 0.0;
        
        int completedCount = 0;
        for (String studentId : students) {
            if (hasCompletedCourse(studentId, courseId)) {
                completedCount++;
            }
        }
        
        double completionRate = (double) completedCount / students.size() * 100;
        return Math.round(completionRate * 100.0) / 100.0;
    }
    
    /**
     * Get completion rate for each lesson in a course
     */
    public Map<String, Double> getLessonCompletionRates(String courseId) {
        Map<String, Double> completionRates = new LinkedHashMap<>();
        Course course = courseService.getCourseById(courseId);
        
        if (course != null) {
            List<Lesson> lessons = course.getLessons();
            List<String> students = course.getStudents();
            
            if (lessons != null) {
                for (Lesson lesson : lessons) {
                    int totalStudents = students != null ? students.size() : 0;
                    if (totalStudents == 0) {
                        completionRates.put(lesson.getTitle(), 0.0);
                        continue;
                    }
                    
                    int completedCount = 0;
                    for (String studentId : students) {
                        Student student = findStudentById(studentId);
                        if (student != null) {
                            List<String> completedLessons = student.getCompletedLessons(courseId);
                            if (completedLessons != null && completedLessons.contains(lesson.getLessonId())) {
                                completedCount++;
                            }
                        }
                    }
                    double rate = (double) completedCount / totalStudents * 100;
                    completionRates.put(lesson.getTitle(), Math.round(rate * 100.0) / 100.0);
                }
            }
        }
        return completionRates;
    }
    
    /**
     * Get average quiz scores for each lesson that has a quiz
     */
    public Map<String, Double> getQuizAverages(String courseId) {
        Map<String, Double> quizAverages = new LinkedHashMap<>();
        Course course = courseService.getCourseById(courseId);
        
        if (course != null) {
            List<Lesson> lessons = course.getLessons();
            List<String> students = course.getStudents();
            
            if (lessons != null && students != null) {
                for (Lesson lesson : lessons) {
                    List<Double> scores = new ArrayList<>();
                    for (String studentId : students) {
                        Student student = findStudentById(studentId);
                        if (student != null) {
                            double score = student.getQuizScore(lesson.getLessonId());
                            if (score > 0) {
                                scores.add(score);
                            }
                        }
                    }
                    
                    if (!scores.isEmpty()) {
                        double average = scores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                        quizAverages.put(lesson.getTitle(), Math.round(average * 100.0) / 100.0);
                    } else {
                        quizAverages.put(lesson.getTitle(), 0.0);
                    }
                }
            }
        }
        return quizAverages;
    }
    
    /**
     * Get detailed performance data for each student in a course
     */
    public Map<String, Map<String, Object>> getStudentPerformance(String courseId) {
        Map<String, Map<String, Object>> performance = new LinkedHashMap<>();
        Course course = courseService.getCourseById(courseId);
        
        if (course != null) {
            List<String> students = course.getStudents();
            List<Lesson> lessons = course.getLessons();
            
            if (students != null) {
                for (String studentId : students) {
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        Map<String, Object> studentData = new HashMap<>();
                        studentData.put("studentName", student.getUsername());
                        studentData.put("completionRate", getStudentCompletionRate(studentId, courseId));
                        studentData.put("averageQuizScore", getStudentAverageQuizScore(studentId, courseId));
                        
                        List<String> completedLessons = student.getCompletedLessons(courseId);
                        int completedCount = completedLessons != null ? completedLessons.size() : 0;
                        int totalLessons = lessons != null ? lessons.size() : 0;
                        
                        studentData.put("completedLessons", completedCount);
                        studentData.put("totalLessons", totalLessons);
                        studentData.put("lastActivity", getLastActivityDate(studentId, courseId));
                        
                        performance.put(studentId, studentData);
                    }
                }
            }
        }
        return performance;
    }
    
    /**
     * Get comprehensive insights for an instructor across all their courses
     */
    public Map<String, Object> getInstructorInsights(String instructorId) {
        Map<String, Object> insights = new HashMap<>();
        List<Course> instructorCourses = courseService.getCoursesByInstructor(instructorId);
        
        int totalStudents = 0;
        double overallCompletionRate = 0.0;
        double overallQuizAverage = 0.0;
        int totalQuizzesTaken = 0;
        int totalLessons = 0;
        int totalCompletedLessons = 0;
        
        Map<String, Double> courseCompletionRates = new LinkedHashMap<>();
        Map<String, Integer> courseEnrollment = new LinkedHashMap<>();
        Map<String, Double> courseQuizAverages = new LinkedHashMap<>();
        
        for (Course course : instructorCourses) {
            double completionRate = getCourseCompletionRate(course.getCourseId());
            double quizAverage = getCourseQuizAverage(course.getCourseId());
            
            List<String> students = course.getStudents();
            List<Lesson> lessons = course.getLessons();
            
            int enrolledStudents = students != null ? students.size() : 0;
            int courseLessons = lessons != null ? lessons.size() : 0;
            int completedLessonsInCourse = getTotalCompletedLessons(course.getCourseId());
            
            courseCompletionRates.put(course.getTitle(), completionRate);
            courseEnrollment.put(course.getTitle(), enrolledStudents);
            courseQuizAverages.put(course.getTitle(), quizAverage);
            
            totalStudents += enrolledStudents;
            overallCompletionRate += completionRate;
            overallQuizAverage += quizAverage;
            totalQuizzesTaken += getTotalQuizzesTaken(course.getCourseId());
            totalLessons += courseLessons;
            totalCompletedLessons += completedLessonsInCourse;
        }
        
        if (!instructorCourses.isEmpty()) {
            overallCompletionRate /= instructorCourses.size();
            overallQuizAverage /= instructorCourses.size();
        }
        
        double overallLessonCompletionRate = totalLessons > 0 ? 
            (double) totalCompletedLessons / totalLessons * 100 : 0.0;
        
        insights.put("totalCourses", instructorCourses.size());
        insights.put("totalStudents", totalStudents);
        insights.put("overallCompletionRate", Math.round(overallCompletionRate * 100.0) / 100.0);
        insights.put("overallQuizAverage", Math.round(overallQuizAverage * 100.0) / 100.0);
        insights.put("overallLessonCompletionRate", Math.round(overallLessonCompletionRate * 100.0) / 100.0);
        insights.put("totalQuizzesTaken", totalQuizzesTaken);
        insights.put("courseCompletionRates", courseCompletionRates);
        insights.put("courseEnrollment", courseEnrollment);
        insights.put("courseQuizAverages", courseQuizAverages);
        
        return insights;
    }
    
    // ========== HELPER METHODS ==========
    
    private Student findStudentById(String studentId) {
        JSONArray usersArray = db.loadUsers();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            String userId = userJson.getString("userId");
            String role = userJson.getString("role");
            
            if (userId.equals(studentId) && "student".equals(role)) {
                return db.parseStudent(userJson);
            }
        }
        return null;
    }
    
    private boolean hasCompletedCourse(String studentId, String courseId) {
        Student student = findStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        if (student == null || course == null) return false;
        
        List<String> completedLessons = student.getCompletedLessons(courseId);
        List<Lesson> courseLessons = course.getLessons();
        
        return completedLessons != null && courseLessons != null && 
               completedLessons.size() == courseLessons.size();
    }
    
    private double getStudentCompletionRate(String studentId, String courseId) {
        Student student = findStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        if (student == null || course == null) return 0.0;
        
        List<String> completedLessons = student.getCompletedLessons(courseId);
        List<Lesson> courseLessons = course.getLessons();
        
        int completed = completedLessons != null ? completedLessons.size() : 0;
        int total = courseLessons != null ? courseLessons.size() : 0;
        double rate = total > 0 ? (double) completed / total * 100 : 0.0;
        return Math.round(rate * 100.0) / 100.0;
    }
    
    private double getStudentAverageQuizScore(String studentId, String courseId) {
        Student student = findStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        if (student == null || course == null) return 0.0;
        
        Map<String, Double> scores = student.getQuizScores();
        List<Lesson> lessons = course.getLessons();
        if (lessons == null) return 0.0;
        
        double total = 0.0;
        int count = 0;
        
        for (Lesson lesson : lessons) {
            if (scores.containsKey(lesson.getLessonId())) {
                total += scores.get(lesson.getLessonId());
                count++;
            }
        }
        
        double average = count > 0 ? total / count : 0.0;
        return Math.round(average * 100.0) / 100.0;
    }
    
    private double getCourseQuizAverage(String courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) return 0.0;
        
        List<String> students = course.getStudents();
        if (students == null) return 0.0;
        
        double total = 0.0;
        int count = 0;
        
        for (String studentId : students) {
            double avg = getStudentAverageQuizScore(studentId, courseId);
            if (avg > 0) {
                total += avg;
                count++;
            }
        }
        
        double average = count > 0 ? total / count : 0.0;
        return Math.round(average * 100.0) / 100.0;
    }
    
    private int getTotalQuizzesTaken(String courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) return 0;
        
        List<String> students = course.getStudents();
        if (students == null) return 0;
        
        int total = 0;
        
        for (String studentId : students) {
            Student student = findStudentById(studentId);
            if (student != null) {
                Map<String, Double> scores = student.getQuizScores();
                List<Lesson> lessons = course.getLessons();
                if (lessons != null) {
                    for (Lesson lesson : lessons) {
                        if (scores.containsKey(lesson.getLessonId())) {
                            total++;
                        }
                    }
                }
            }
        }
        return total;
    }
    
    private int getTotalCompletedLessons(String courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) return 0;
        
        List<String> students = course.getStudents();
        if (students == null) return 0;
        
        int total = 0;
        
        for (String studentId : students) {
            Student student = findStudentById(studentId);
            if (student != null) {
                List<String> completedLessons = student.getCompletedLessons(courseId);
                if (completedLessons != null) {
                    total += completedLessons.size();
                }
            }
        }
        return total;
    }
    
    private String getLastActivityDate(String studentId, String courseId) {
        Student student = findStudentById(studentId);
        if (student == null) return "Unknown";
        
        List<String> completedLessons = student.getCompletedLessons(courseId);
        int completedCount = completedLessons != null ? completedLessons.size() : 0;
        return completedCount > 0 ? "Recent" : "No activity";
    }
}
