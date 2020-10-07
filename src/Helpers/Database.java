package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;
    private static String DBUrl = "jdbc:mysql://localhost:3306/webblog?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    private static String DBUsername = "root";
    private static String DBPassword = "38217495";
    private final static String DBDriver = "com.mysql.cj.jdbc.Driver";

    static Connection conn = null;

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBDriver);
        conn = DriverManager.getConnection(DBUrl,DBUsername,DBPassword);
        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null || connection.isClosed()) {
            connection = openConnection();
        }

        return connection;
    }

    public static Connection getConnection(boolean isTransaction) throws SQLException, ClassNotFoundException {
        getConnection().setAutoCommit(!isTransaction);

        return connection;

    }
}
