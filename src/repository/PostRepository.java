package repository;

import model.Post;
import model.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostRepository {
    public ArrayList<Post> readUserPosts(int id) {

        ArrayList<Post> postari = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);

            Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("SELECT * FROM post_table WHERE user_id = " + id);

            while (rs.next()){
                postari.add(new Post(
                        rs.getInt("id"),
                        rs.getString("message"),
                        LocalDateTime.of(
                                rs.getInt("p_year"),
                                rs.getInt("p_month"),
                                rs.getInt("p_day"),
                                rs.getInt("p_hour"),
                                rs.getInt("p_minute")
                        )
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postari;


    }

    public ArrayList<Post> readAll(){
        ArrayList<Post> allPosts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);

            Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("SELECT * FROM post_table");

            while (rs.next()) {
                allPosts.add(new Post(
                        rs.getInt("id"),
                        rs.getString("message"),
                        LocalDateTime.of(
                                rs.getInt("p_year"),
                                rs.getInt("p_month"),
                                rs.getInt("p_day"),
                                rs.getInt("p_hour"),
                                rs.getInt("p_minute")
                        )
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPosts;
    }

    public Post readById(int id) {

        return null;

    }

    public void create(String message, int userId, LocalDateTime now) {

        String template = "INSERT INTO post_table (message, user_id, p_year,p_month,p_day,p_hour,p_minute) VALUES ('%s', '%d', '%d', '%d', '%d', '%d', '%d')";

        try {
            DatabaseConnection.getUpdateStatement()
                    .executeUpdate(String.format(template,
                            message,userId,now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute()));
            System.out.println("Postarea a fost salvata");
        } catch (SQLException e) {
            System.out.println("Postarea nu a fost salvata");
        }
    }

    public void updatePostMessage(int id, String mesajNou) {
        String template = "UPDATE post_table SET message= '%s' WHERE id= %d";
        try {
           int affectedRows = DatabaseConnection.getUpdateStatement().executeUpdate(String.format(template, mesajNou, id));
            System.out.println("Mesajul postarii " + (affectedRows==0 ? "nu" : "") + " a fost modificat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //sa ma uit la penultimul curs ca sa fac asta
    }

    public void delete(int postId) {
        try {
            int affectedRows = DatabaseConnection.getUpdateStatement().executeUpdate("DELETE FROM post_table WHERE id=" + postId);
            System.out.println("Postarea " + (affectedRows==0 ? "nu" : "") + " a fost stearsa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
