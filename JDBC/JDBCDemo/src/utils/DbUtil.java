package utils;

import java.sql.*;

public class DbUtil {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root1234";

    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
