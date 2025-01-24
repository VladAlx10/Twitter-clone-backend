package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {

    public ArrayList<User> readAll() {
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);

            Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("SELECT * FROM user_table");

            while (rs.next()) {
                allUsers.add(new User(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getInt("varsta")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //statement
        //resultSet
        return allUsers;
    }

    public User readById(int id) {
        try {
            Connection connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);

            Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("SELECT * FROM user_table WHERE id=" + id);

            while (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getInt("varsta"));
            }

            //rs.next()
            //rs.getInt("id") -> SQL EXCEPTION
            //return that user
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public void create(String nume, String prenume, int varsta) {


        String template = "INSERT INTO user_table (nume,prenume,varsta) VALUES ('%s', '%S', '%d')";

        try {
                int affectedRows = DatabaseConnection.getUpdateStatement()
                        .executeUpdate(String.format(template, nume, prenume, varsta));
                System.out.println("Userul " + (affectedRows == 0 ? "nu" : "") + " a fost salvat.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }






    public void delete(int id) {
        try {
            Connection connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);
            Statement st = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            int affectedRows =  st.executeUpdate("DELETE FROM user_table WHERE id=" + id);

            System.out.println("Userul " + (affectedRows==0? "nu" : "") + " a fost sters.");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNume(int id, String nume) {

        String template = "UPDATE user_table SET nume= '%s' WHERE id= '%d'";

        try {
            DatabaseConnection.getUpdateStatement()
                    .executeUpdate(String.format(template, nume, id));
            System.out.println("Numele a fost modificat.");
        } catch (SQLException e) {
            System.out.println("Numele nu a fost modificat");
        }
    }

    public void updatePrenume(int id, String prenume) {
        String template = "UPDATE user_table SET prenume= '%s' WHERE id= '%d'";

        try {
            DatabaseConnection.getUpdateStatement()
                    .executeUpdate(String.format(template, prenume, id));
            System.out.println("Prenumele a fost modificat.");
        } catch (SQLException e) {
            System.out.println("Prenumele nu a fost modificat");
        }
    }

    public void updateVarsta(int id, int varsta) {
        String template = "UPDATE user_table SET varsta= %d WHERE id= '%d'";

        try {
            DatabaseConnection.getUpdateStatement()
                    .executeUpdate(String.format(template, varsta, id));
            System.out.println("Varsta a fost modificata.");
        } catch (SQLException e) {
            System.out.println("Varsta nu a fost modificata");
        }
    }
}
