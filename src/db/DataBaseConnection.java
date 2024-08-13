package com.clinica.db;

import org.apache.log4j.Logger;
import java.sql.*;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class);
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error("Error al cargar el driver de H2", e);
            throw new SQLException("No se pudo cargar el driver de H2");
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            logger.error("Error al cerrar la conexión", e);
        }
    }

    public static void closeConnection(Connection connection, Statement statement) {
        closeConnection(connection, statement, null);
    }
}