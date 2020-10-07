package Models;

import java.sql.Date;
import java.util.ArrayList;

public class Post {
    private int id;
    private String title;
    private String body;
    private Date date;
    private int visibility;
    private int userID;

    private ArrayList<Comment> comments = new ArrayList<>();

    public Post(int id, String title, String body, Date date, int visibility, int userID) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.visibility = visibility;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
