package skill.forge;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private JsonDataBaseManager db;
    
    public AuthService(JsonDataBaseManager db) {
        this.db = db;
    }
    
    public User login(String email, String password) {
        JSONArray usersJson = db.loadUsers();
        String hashedPassword = hashPassword(password);
        
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject savedUser = usersJson.getJSONObject(i);
            String savedEmail = savedUser.getString("email");
            String savedPasswordHash = savedUser.getString("passwordHash");
            
            if (savedEmail.equals(email) && savedPasswordHash.equals(hashedPassword)) {
                // Use parseUser method from JsonDataBaseManager instead of manual parsing
                return db.parseUser(savedUser);
            }
        }
        return null;
    }
    
    public User signup(String username, String email, String password, String role) {
        if (!validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        // Check if email already exists
        if (isEmailExists(email)) {
            JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        String userId = db.generateId();
        String passwordHash = hashPassword(password);
        User user;
        
        if (role.equalsIgnoreCase("student")) {
            user = new Student(userId, username, email, passwordHash);
        } else {
            user = new Instructor(userId, username, email, passwordHash);
        }
        
        // Save the new user to database
        saveUserToDatabase(user);
        return user;
    }
    
    private boolean isEmailExists(String email) {
        JSONArray usersJson = db.loadUsers();
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject savedUser = usersJson.getJSONObject(i);
            String savedEmail = savedUser.getString("email");
            if (savedEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    private void saveUserToDatabase(User user) {
        JSONArray usersArray = db.loadUsers();
        JSONObject userJson = db.toJson(user);
        usersArray.put(userJson);
        db.saveUsers(usersArray);
    }
    
    public User refreshUser(String userId) {
        JSONArray usersArray = db.loadUsers();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("userId").equals(userId)) {
                return db.parseUser(userJson);
            }
        }
        return null;
    }
    
    public boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
    
    public void logout() {
        // In a real application, this would handle session cleanup
        // For this implementation, we'll just clear any session data
        System.out.println("User logged out successfully");
        // You can add session management logic here if needed
    }
    
    public String hashPassword(String password) {
        if (password == null) return null;
        
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error in Hashing Password: " + e.getMessage());
        }
        return hexString.toString();
    }
    
    public JsonDataBaseManager getDb() {
        return db;
    }
    
    public void setDb(JsonDataBaseManager db) {
        this.db = db;
    }
}