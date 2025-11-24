package skill.forge;
import org.json.JSONObject;

public class Admin extends User {
    
    public Admin(String userId , String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "admin");
    }
    
    @Override
    public JSONObject toJSONObject(){
        
        JSONObject json = new JSONObject();
        json.put("userId", this.getUserId());
        json.put("username", this.getUsername());
        json.put("email", this.getEmail());
        json.put("passwordHash", this.getPasswordHash());
        json.put("role", this.getRole());
        
        return json;
    }
}
