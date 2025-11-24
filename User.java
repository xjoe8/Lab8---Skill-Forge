package skill.forge;
import org.json.JSONObject;

public abstract class User {
    private String userId;
    private String username;
    private String email;
    private String passwordHash;
    private String role;

    public User(String userId, String username, String email, String passwordHash, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public abstract JSONObject toJSONObject();

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }

    public void setUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.username = username;
        }
    }

    public void setEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            if (email.contains("@") && email.contains(".")) {
                this.email = email;
            }
        }
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash != null && !passwordHash.trim().isEmpty()) {
            this.passwordHash = passwordHash;
        }
    }

    String getuserId() {
        return this.userId;
    }
}
