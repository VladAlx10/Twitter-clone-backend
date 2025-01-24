package repository;

import java.sql.*;

public class DatabaseConnection {

    static Connection connection;
    static Statement statement;

    public static Statement getUpdateStatement(){
        try {
            connection = DriverManager.getConnection(DatabaseCredentials.URL,
                    DatabaseCredentials.USER,
                    DatabaseCredentials.PASSWORD);

            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;

    }


}
