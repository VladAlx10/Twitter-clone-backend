package model;

import java.time.LocalDateTime;

public class Comment {

    int userId;
    String message;
    LocalDateTime updatedAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", message='" + message + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
