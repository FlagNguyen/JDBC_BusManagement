package Util;

import Entity.Driver;

import java.sql.*;

public class DbConnection {

    public static Util util = new Util() ;

    public static Connection open(String driver, String url, String user, String pass) {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (!util.isObjectEmpty(resultSet) && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (!util.isObjectEmpty(statement) && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (!util.isObjectEmpty(connection) && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
