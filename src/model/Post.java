package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    private int id;
    private String message;
    private LocalDateTime createdAt;
    private ArrayList<Integer> likedBy;
    private ArrayList<Comment> comments;


    public Post(int id, String message, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.likedBy=new ArrayList<>();
        //todo instantiem in constructor
    }

    //todo sa face getter si setter cu comments

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Integer> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(ArrayList<Integer> likedBy) {
        this.likedBy = likedBy;
    }

    //todo sa il bag in toString
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", likedBy=" + likedBy +
                '}';
    }
}
