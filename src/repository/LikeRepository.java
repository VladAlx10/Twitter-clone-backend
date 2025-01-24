package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LikeRepository {

    public static ArrayList<Integer> getPostLikes(int postId) {
        ArrayList<Integer> likedBy = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnection.getReadStatement //todo din cursul de marti
                    .executeQuery("SELECT user_id FROM like_table WHERE post_id=" + postId);

            while (rs.next()){
                likedBy.add(rs.getInt("user_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return likedBy;
    }

    public void createLike(int userId, int postId) {
        String template = "INSERT INTO like_table (user_id, post_id) VALUES (%d, %d)";
        try {
            DatabaseConnection.getUpdateStatement().executeUpdate(String.format(template, userId, postId));
            System.out.println("Ai dat like cu succes");
        } catch (SQLException e) {
            System.out.println("Nu ai dat like. A aparut o eroare.");
        }

    }

    public void deleteLike(int userId, int postId) {

        String template = "DELETE FROM like_table WHERE user_id = %d AND post_id = %d";

        try {
            int affectedRows = DatabaseConnection.getUpdateStatement().executeUpdate(String.format(template, userId, postId));
            System.out.println("Likeul " + (affectedRows>=0 ? "": "nu") + " a fost sters");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
