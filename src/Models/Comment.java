package Models;

import java.util.Date;

public class Comment {
    private int id;
    private String body;
    private Date date;
    private int userId;
    private int postId;
    private String fullName;

    public Comment(int id, String body, Date date, int userId, int postId) {
        this.id = id;
        this.body = body;
        this.date = date;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment(String body, Date date, int userId, int postId) {
        this.body = body;
        this.date = date;
        this.userId = userId;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
