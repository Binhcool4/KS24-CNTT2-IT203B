package BTTH;

public class User implements IUserAccount {
    private String username;
    private String email;
    private String role;
    private String status;

    public User() {
    }

    public User(String username, String email, String role, String status) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("User[Name: %s, Email: %s, Role: %s, Status: %s]",
                username, email, role, status);
    }
}
