package Models;

public class User{
    private int id;
    private String fullName;
    private String username;
    private String email;
    private String homeTown;
    private int age;
    private String description;
    private boolean isAdmin;

    public User(int id, String fullName, String username, String email, String homeTown, int age, String description, boolean isAdmin) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.homeTown = homeTown;
        this.age = age;
        this.description = description;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}